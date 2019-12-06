
package acme.features.provider.request;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, acme.entities.requests.Request> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ProviderRequestRepository repository;


	// AbstractCreateService<Provider, acme.entities.requests.Request> interface --------------

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

		request.unbind(entity, model, "title", "deadline", "text", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public void bind(final Request<acme.entities.requests.Request> request, final acme.entities.requests.Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public acme.entities.requests.Request instantiate(final Request<acme.entities.requests.Request> request) {
		assert request != null;
		acme.entities.requests.Request result;
		result = new acme.entities.requests.Request();
		return result;
	}

	@Override
	public void validate(final Request<acme.entities.requests.Request> request, final acme.entities.requests.Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean tickerDuplicated, isAccepted, acceptedCurrency, activeDeadline;
		String currency;
		Calendar calendar;
		Date deadlineMoment, currentMoment;

		if (!errors.hasErrors("deadline")) { //Check if deadline has no errors
			deadlineMoment = entity.getDeadline();
			calendar = new GregorianCalendar();
			currentMoment = calendar.getTime();
			activeDeadline = deadlineMoment.after(currentMoment);
			errors.state(request, activeDeadline, "deadline", "provider.request.error.deadline");
		}

		if (!errors.hasErrors("ticker")) { //Check if ticker has no errors
			tickerDuplicated = this.repository.findOneRequestByTicker(entity.getTicker()) != null;
			errors.state(request, !tickerDuplicated, "ticker", "provider.request.error.tickerDuplicated");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "provider.request.error.must-accept");

		if (!errors.hasErrors("reward")) { //Check if reward has no errors
			currency = entity.getReward().getCurrency();
			acceptedCurrency = currency.equals("EUR") || currency.equals("€");
			errors.state(request, acceptedCurrency, "reward", "provider.request.error.currency");
		}

	}

	@Override
	public void create(final Request<acme.entities.requests.Request> request, final acme.entities.requests.Request entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
