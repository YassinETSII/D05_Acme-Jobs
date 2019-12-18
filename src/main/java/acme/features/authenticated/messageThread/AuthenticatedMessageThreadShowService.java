
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;

	// AbstractShowService<Authenticated, MessageThread> interface --------------


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		MessageThread mt = this.repository.findOneById(id);
		Collection<Authenticated> parts = this.repository.findManyAuthenticatedByThreadId(id);

		result = principal.getAccountId() == mt.getCreator().getUserAccount().getId() || parts.stream().anyMatch(a -> a.getUserAccount().getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "creator.identity.fullName");
		boolean isCreator = entity.getCreator().getId() == request.getPrincipal().getActiveRoleId();
		model.setAttribute("isCreator", isCreator);

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageThreadById(id);
		return result;
	}

}
