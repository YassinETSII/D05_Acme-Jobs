
package acme.features.authenticated.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthenticatedShowService implements AbstractShowService<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedAuthenticatedRepository repository;

	// AbstractShowService<Authenticated, Authenticated> interface --------------


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

		boolean toAdd = true;
		int id = entity.getId();
		Participation p = this.repository.findParticipationByMessageThreadIdAndParticipantId(request.getModel().getInteger("messageThreadId"), id);
		if (p != null) {
			toAdd = false;
		}
		request.unbind(entity, model, "identity.fullName");
		model.setAttribute("toAdd", toAdd);
	}

	@Override
	public Authenticated findOne(final Request<Authenticated> request) {
		assert request != null;

		Authenticated result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuthenticatedById(id);

		return result;
	}

}
