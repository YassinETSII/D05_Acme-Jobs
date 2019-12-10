
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadListService implements AbstractListService<Authenticated, MessageThread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	// AbstractListService<Authenticated, MessageThread> interface --------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<MessageThread> findMany(final Request<MessageThread> request) {
		assert request != null;

		Collection<MessageThread> result;

		int id = request.getPrincipal().getActiveRoleId();
		result = this.repository.findManyMessageThreadsByAuthenticatedId(id);
		return result;

	}

}
