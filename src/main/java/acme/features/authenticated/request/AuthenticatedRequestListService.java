
package acme.features.authenticated.request;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated, acme.entities.requests.Request> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedRequestRepository repository;


	// AbstractListService<Authenticated, acme.entities.requests.Request> interface --------------

	@Override
	public boolean authorise(final Request<acme.entities.requests.Request> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<acme.entities.requests.Request> request, final acme.entities.requests.Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<acme.entities.requests.Request> findMany(final Request<acme.entities.requests.Request> request) {
		assert request != null;

		Calendar c = new GregorianCalendar();
		Date d = c.getTime();

		Collection<acme.entities.requests.Request> result;
		result = this.repository.findManyAll(d);

		return result;

	}

}
