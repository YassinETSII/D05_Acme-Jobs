
package acme.features.anonymous.rodriguezbulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RodriguezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousRodriguezBulletinCreateService implements AbstractCreateService<Anonymous, RodriguezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousRodriguezBulletinRepository repository;


	// AbstractCreateService<Anonymous, RodriguezBulletin> interface --------------

	@Override
	public boolean authorise(final Request<RodriguezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public RodriguezBulletin instantiate(final Request<RodriguezBulletin> request) {
		assert request != null;

		RodriguezBulletin result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new RodriguezBulletin();
		result.setInformer("John Doe");
		result.setType("Attack Type Example");
		result.setBulletinMoment(moment);
		return result;
	}

	@Override
	public void unbind(final Request<RodriguezBulletin> request, final RodriguezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "informer", "type");
	}

	@Override
	public void bind(final Request<RodriguezBulletin> request, final RodriguezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<RodriguezBulletin> request, final RodriguezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<RodriguezBulletin> request, final RodriguezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date bulletinMoment;
		bulletinMoment = new Date(System.currentTimeMillis() - 1);
		entity.setBulletinMoment(bulletinMoment);
		this.repository.save(entity);
	}
}
