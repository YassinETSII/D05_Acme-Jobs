
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.utilities.CheckSpamWords;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCommercialBannerRepository repository;


	// AbstractUpdateService<Sponsor, CommercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Sponsor sponsor;
		Principal principal;
		CommercialBanner banner;

		principal = request.getPrincipal();
		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneCommercialBannerById(bannerId);
		sponsor = banner.getSponsor();
		result = sponsor.getCreditCard() != null && sponsor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "URL", "creditCard.holder", "creditCard.expirationMonth", "creditCard.expirationYear", "creditCard.creditCardNumber", "creditCard.brand", "creditCard.CVV");

		boolean noCreditCard = entity.getSponsor().getCreditCard() == null;
		model.setAttribute("noCreditCard", noCreditCard);
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Validation of spam
		String slogan;
		boolean checkSloganSpam;
		if (!errors.hasErrors("slogan")) {
			slogan = entity.getSlogan();
			checkSloganSpam = CheckSpamWords.isSpam(slogan, this.repository.findConfiguration());
			errors.state(request, !checkSloganSpam, "slogan", "sponsor.commercialBanner.error.spam");
		}

	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;
		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialBannerById(id);

		return result;
	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
