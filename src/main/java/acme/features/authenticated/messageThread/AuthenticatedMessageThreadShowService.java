
package acme.features.authenticated.messageThread;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserRole;
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

		Collection<Message> mess = this.repository.findManyMessagesByMessageThreadId(id);
		listAuth.addAll(mess.stream().map(m -> m.getUser()).collect(Collectors.toList()));

		result = listAuth.stream().anyMatch(a -> a.getUserAccount().getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");

		StringBuilder buffer;
		//		Collection<Message> userNames = this.repository.findManyMessagesByMessageThreadId(entity.getId());
		//		buffer = new StringBuilder();
		//		for (Message userName : userNames) {
		//			buffer.append("[" + userName.getUser().getIdentity().getFullName() + "]");
		//			buffer.append(" ");
		//		}
		Collection<UserRole> userNames = entity.getUsersInvolved();
		buffer = new StringBuilder();
		for (UserRole userName : userNames) {
			buffer.append("[" + userName.getIdentity().getFullName() + "]");
			buffer.append(" ");
		}
		model.setAttribute("userNameList", buffer.toString());
		//		Collection<UserRole> userNamesToInvolve = this.repository.findManyUsersToInvolve(entity.getId());
		//		ArrayList<String> usersToInvolve = new ArrayList<>();
		//		for (UserRole userNameToInvolve : userNamesToInvolve) {
		//			usersToInvolve.add(userNameToInvolve.getIdentity().getFullName());
		//		}
		//		model.setAttribute("usersToInvolve", usersToInvolve);

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageThreadById(id);
		result.getUsersInvolved().size();

		return result;
	}

}
