
package acme.features.authenticated.messageThread;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participations.Participation;
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

		boolean result;
		Principal principal;
		List<Authenticated> listAuth = new LinkedList<>();

		principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		MessageThread mt = this.repository.findOneById(id);
		Collection<Participation> parts = this.repository.findManyParticipationByThreadId(id);
		listAuth.addAll(parts.stream().map(p -> p.getParticipant()).collect(Collectors.toList()));

		//		Collection<Message> mess = this.repository.findManyMessagesByMessageThreadId(id);
		//		listAuth.addAll(mess.stream().map(m -> m.getUser()).collect(Collectors.toList()));
		//
		//		result = listAuth.stream().anyMatch(a -> a.getUserAccount().getId() == principal.getAccountId());

		result = principal.getAccountId() == mt.getCreator().getUserAccount().getId() || listAuth.stream().anyMatch(a -> a.getUserAccount().getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "creator.identity.fullName");

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
