
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;


	// AbstractListService<Authenticated, Message> interface --------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		Principal principal;
		principal = request.getPrincipal();
		Collection<Message> messages = this.repository.findManyMessagesByMessageThreadId(request.getModel().getInteger("messageThreadId"));
		Collection<Authenticated> auth = messages.stream().map(m -> m.getUser()).collect(Collectors.toList());
		result = auth.stream().anyMatch(u -> u.getUserAccount().getId() == principal.getAccountId());
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;

		int id = request.getModel().getInteger("messageThreadId");
		result = this.repository.findManyMessagesByMessageThreadId(id);
		return result;

	}

}
