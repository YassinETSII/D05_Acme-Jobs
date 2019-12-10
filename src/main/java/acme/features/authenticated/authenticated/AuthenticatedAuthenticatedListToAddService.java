
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedListToAddService implements AbstractListService<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


	// AbstractListService<Authenticated, Authenticated> interface --------------

	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;

		boolean result;
		Principal principal;
		principal = request.getPrincipal();
		Authenticated creator = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId")).getCreator();
		result = principal.getAccountId() == creator.getUserAccount().getId();
		return result;
	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "identity.fullName");
	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;

		Collection<Authenticated> result;

		int id = request.getModel().getInteger("messageThreadId");
		result = this.repository.findManyNotParticipantByMessageThreadId(id);
		return result;

	}

}
