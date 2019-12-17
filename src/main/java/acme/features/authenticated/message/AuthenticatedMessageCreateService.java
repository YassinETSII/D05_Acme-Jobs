
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;
import acme.utilities.CheckSpamWords;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository repository;

	// AbstractCreateService<Authenticated, Message> interface --------------


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;

		Principal principal;

		int id = request.getModel().getInteger("messageThreadId");
		principal = request.getPrincipal();
		Collection<Participation> auth = this.repository.findManyParticipationByThreadId(id);

		result = auth.stream().anyMatch(p -> p.getParticipant().getUserAccount().getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");
		model.setAttribute("accept", false);
		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;
		Message result;
		result = new Message();
		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();
		Authenticated user = this.repository.findAuthenticatedById(id);
		result.setUser(user);
		MessageThread thread;
		thread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId"));
		result.setMessageThread(thread);
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");

		//Validation of spam
		String body;
		boolean checkSpam;
		if (!errors.hasErrors("body")) {
			body = entity.getBody();
			checkSpam = CheckSpamWords.isSpam(body, this.repository.findConfiguration());
			errors.state(request, !checkSpam, "body", "authenticated.message.error.spam");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
