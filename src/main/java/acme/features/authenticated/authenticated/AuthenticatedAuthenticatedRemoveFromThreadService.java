
package acme.features.authenticated.authenticated;

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
public class AuthenticatedAuthenticatedRemoveFromThreadService implements AbstractDeleteService<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


	// AbstractCreateService<Authenticated, Authenticated> interface --------------

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
	public void bind(final Request<Authenticated> request, final Authenticated entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Authenticated> request, final Authenticated entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
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

	@Override
	public void delete(final Request<Authenticated> request, final Authenticated entity) {
		assert request != null;
		assert entity != null;

		Participation p = this.repository.findParticipationByMessageThreadIdAndParticipantId(request.getModel().getInteger("messageThreadId"), entity.getId());

		this.repository.delete(p);

	}

}
