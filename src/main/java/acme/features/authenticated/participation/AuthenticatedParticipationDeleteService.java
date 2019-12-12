
package acme.features.authenticated.participation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipationDeleteService implements AbstractDeleteService<Authenticated, Participation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipationRepository repository;


	// AbstractDeleteService<Authenticated, Participation> interface --------------

	@Override
	public boolean authorise(final Request<Participation> request) {
		assert request != null;

		boolean result;
		Principal principal;
		principal = request.getPrincipal();
		Authenticated creator = this.repository.findOneParticipationById(request.getModel().getInteger("id")).getThread().getCreator();
		result = principal.getAccountId() == creator.getUserAccount().getId();
		return result;
	}

	@Override
	public void unbind(final Request<Participation> request, final Participation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "thread.title", "participant.identity.fullName");

	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public Participation findOne(final Request<Participation> request) {
		assert request != null;

		Participation result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneParticipationById(id);

		return result;
	}

	@Override
	public void delete(final Request<Participation> request, final Participation entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
