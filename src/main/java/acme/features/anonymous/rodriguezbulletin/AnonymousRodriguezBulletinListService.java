
package acme.features.anonymous.rodriguezbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RodriguezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRodriguezBulletinListService implements AbstractListService<Anonymous, RodriguezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousRodriguezBulletinRepository repository;


	// AbstractListService<Anonymous, RodrigueBulletin> interface --------------

	@Override
	public boolean authorise(final Request<RodriguezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<RodriguezBulletin> request, final RodriguezBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "informer", "type", "bulletinMoment");
	}

	@Override
	public Collection<RodriguezBulletin> findMany(final Request<RodriguezBulletin> request) {
		assert request != null;

		Collection<RodriguezBulletin> result;
		result = this.repository.findMany();

		return result;
	}
}
