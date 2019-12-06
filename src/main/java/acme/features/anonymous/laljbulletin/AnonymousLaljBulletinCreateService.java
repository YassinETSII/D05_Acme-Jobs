
package acme.features.anonymous.laljbulletin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.LaljBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousLaljBulletinCreateService implements AbstractCreateService<Anonymous, LaljBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousLaljBulletinRepository repository;


	// AbstractCreateService<Anonymous, LaljBulletin> interface --------------

	@Override
	public boolean authorise(final Request<LaljBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public LaljBulletin instantiate(final Request<LaljBulletin> request) {
		assert request != null;

		LaljBulletin result;
		Date moment = new Date(120, 6, 21, 12, 30);
		Money money = new Money();
		money.setAmount(12.);
		money.setCurrency("EUR");
		result = new LaljBulletin();

		result.setEvent("Visit Giralda");
		result.setMomentOfEvent(moment);
		result.setLocation("Puerta Jerez");
		result.setCost(money);
		return result;
	}

	@Override
	public void unbind(final Request<LaljBulletin> request, final LaljBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "event", "momentOfEvent", "location", "cost");
	}

	@Override
	public void bind(final Request<LaljBulletin> request, final LaljBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<LaljBulletin> request, final LaljBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean eventInFuture, acceptedCurrency;
		Calendar calendar;
		Date momentEv, currentMoment;
		String currency;

		if (!errors.hasErrors("momentOfEvent")) { //Check if momentOfEvent has no errors
			momentEv = entity.getMomentOfEvent();
			calendar = new GregorianCalendar();
			currentMoment = calendar.getTime();
			eventInFuture = momentEv.after(currentMoment);
			errors.state(request, eventInFuture, "momentOfEvent", "anonymous.laljbulletin.error.momentOfEvent");
		}

		if (!errors.hasErrors("cost")) { //Check if cost has no errors
			currency = entity.getCost().getCurrency();
			acceptedCurrency = currency.equals("EUR");
			errors.state(request, acceptedCurrency, "cost", "anonymous.laljbulletin.error.currency");
		}
	}

	@Override
	public void create(final Request<LaljBulletin> request, final LaljBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
