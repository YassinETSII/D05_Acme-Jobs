
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;

	// AbstractShowService<Authenticated, Message> interface --------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;

		Principal principal;

		int id = request.getModel().getInteger("id");
		Message mess = this.repository.findOneMessageById(id);
		int idThread = mess.getMessageThread().getId();
		principal = request.getPrincipal();
		Collection<Authenticated> auth = this.repository.findManyUsersByMessageThreadId(idThread);

		result = auth.stream().anyMatch(m -> m.getUserAccount().getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "user.identity.fullName");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageById(id);

		return result;
	}

}
