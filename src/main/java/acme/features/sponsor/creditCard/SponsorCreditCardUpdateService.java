
package acme.features.sponsor.creditCard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCreditCardUpdateService implements AbstractUpdateService<Sponsor, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCreditCardRepository repository;


	// AbstractUpdateService<Sponsor, CreditCard> interface --------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "expirationMonth", "expirationYear", "creditCardNumber", "brand", "CVV");
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar c = new GregorianCalendar();
		Date d = c.getTime();
		Boolean activeMonth;
		Boolean currentYear;

		//Check if the entered year is equal or superior than the current year
		if (!errors.hasErrors("expirationYear")) { //Check if expirationYear has no errors
			currentYear = entity.getExpirationYear() >= d.getYear() - 100;
			errors.state(request, currentYear, "expirationYear", "sponsor.CreditCard.error.expirationYear");
		}
		//Check if the entered month is equal or superior than the current month if it is in the current year
		if (!errors.hasErrors("expirationMonth") && !errors.hasErrors("expirationYear")) { //Check if expirationMonth and expirationYear have no errors
			if (entity.getExpirationYear() == d.getYear() - 100) {
				activeMonth = entity.getExpirationMonth() >= d.getMonth() + 1;
				errors.state(request, activeMonth, "expirationMonth", "sponsor.CreditCard.error.expirationMonth");
			}
		}

	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;
		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
