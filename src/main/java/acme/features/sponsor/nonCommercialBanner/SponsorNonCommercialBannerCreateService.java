
package acme.features.sponsor.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;
import acme.utilities.CheckSpamWords;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	// AbstractCreateService<Sponsor, NonCommercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
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
	public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
		assert request != null;
		NonCommercialBanner result;
		result = new NonCommercialBanner();
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findOneSponsorByUserAccountId(principal.getAccountId());
		result.setSponsor(sponsor);
		return result;
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
	public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
