
package acme.features.authenticated.messageThread;

import java.util.Collection;

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
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedMessageThreadDeleteService implements AbstractDeleteService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	// AbstractDeleteService<Authenticated, MessageThread> interface --------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		boolean result;
		Principal principal;

		principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		MessageThread mt = this.repository.findOneById(id);

		result = principal.getAccountId() == mt.getCreator().getUserAccount().getId();
		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creator.identity.fullName");
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;
		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void delete(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		Collection<Message> messages = this.repository.findManyMessagesByMessageThreadId(entity.getId());
		this.repository.deleteAll(messages);

		Collection<Participation> participants = this.repository.findManyParticipationByThreadId(entity.getId());
		this.repository.deleteAll(participants);

		this.repository.delete(entity);

	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

}
