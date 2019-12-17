
package acme.features.authenticated.participation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipationCreateService implements AbstractCreateService<Authenticated, Participation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedParticipationRepository repository;


	// AbstractCreateService<Authenticated, Participation> interface --------------

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

		Collection<Authenticated> users = this.repository.findManyNotParticipantByMessageThreadId(request.getModel().getInteger("messageThreadId"));
		request.unbind(entity, model, "thread.title", "participant");
		model.setAttribute("users", users);
		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));

	}

	@Override
	public void bind(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Authenticated participant = this.repository.findOneAuthenticatedById(request.getModel().getInteger("participant"));
		entity.setParticipant(participant);
		request.bind(entity, errors, "participant");
	}

	@Override
	public void validate(final Request<Participation> request, final Participation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public Participation instantiate(final Request<Participation> request) {
		assert request != null;
		Participation result;
		result = new Participation();
		result.setThread(this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId")));
		return result;
	}

	@Override
	public void create(final Request<Participation> request, final Participation entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
