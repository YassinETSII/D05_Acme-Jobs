
package acme.features.administrator.commercialBanner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCommercialBannerUpdateService implements AbstractUpdateService<Administrator, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCommercialBannerRepository repository;


	// AbstractUpdateService<Administrator, CommercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "URL", "holder", "expirationMonth", "expirationYear", "creditCardNumber", "brand", "CVV");
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
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
			errors.state(request, currentYear, "expirationYear", "administrator.commercialBanner.error.expirationYear");
		}
		//Check if the entered month is equal or superior than the current month if it is in the current year
		if (!errors.hasErrors("expirationMonth") && !errors.hasErrors("expirationYear")) { //Check if expirationMonth and expirationYear have no errors
			if (entity.getExpirationYear() == d.getYear() - 100) {
				activeMonth = entity.getExpirationMonth() >= d.getMonth() + 1;
				errors.state(request, activeMonth, "expirationMonth", "administrator.commercialBanner.error.expirationMonth");
			}
		}

	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;
		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
