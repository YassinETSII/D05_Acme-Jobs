
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipationListService implements AbstractListService<Authenticated, Participation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipationRepository repository;


	// AbstractListService<Authenticated, Participation> interface --------------

	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		boolean result;
		Principal principal;
		principal = request.getPrincipal();
		Authenticated creator = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId")).getCreator();
		result = principal.getAccountId() == creator.getUserAccount().getId();
		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "participant.identity.fullName");
	}

	@Override
	public Collection<Participation> findMany(final Request<Participation> request) {
		assert request != null;

		Collection<Participation> result;

		int id = request.getModel().getInteger("messageThreadId");
		result = this.repository.findManyParticipationByMessageThreadId(id);
		return result;

	}

}
