
package acme.features.authenticated.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participations.Participation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedAuthenticatedAddToThreadService implements AbstractUpdateService<Authenticated, Authenticated> {

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
	public void update(final Request<Authenticated> request, final Authenticated entity) {
		assert request != null;
		assert entity != null;

		MessageThread mt = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThreadId"));
		Participation p = new Participation();
		p.setParticipant(entity);
		p.setThread(mt);

		this.repository.save(p);

	}

}
