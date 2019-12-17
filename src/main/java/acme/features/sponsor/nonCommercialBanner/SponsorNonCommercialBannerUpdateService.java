
package acme.features.sponsor.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.utilities.CheckSpamWords;

@Service
public class SponsorNonCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, NonCommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	// AbstractUpdateService<Sponsor, NonCommercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Sponsor sponsor;
		Principal principal;
		NonCommercialBanner banner;

		principal = request.getPrincipal();
		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneNonCommercialBannerById(bannerId);
		sponsor = banner.getSponsor();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "URL", "jingle");
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Validation of spam
		String jingle;
		boolean checkSpam;
		if (!errors.hasErrors("jingle")) {
			jingle = entity.getJingle();
			checkSpam = CheckSpamWords.isSpam(jingle, this.repository.findConfiguration());
			errors.state(request, !checkSpam, "jingle", "sponsor.nonCommercialBanner.error.spam");
		}

	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;
		NonCommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneNonCommercialBannerById(id);

		return result;
	}

	@Override
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
