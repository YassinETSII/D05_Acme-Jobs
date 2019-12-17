
package acme.features.sponsor.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCreditCardShowService implements AbstractShowService<Sponsor, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCreditCardRepository repository;


	// AbstractShowService<Sponsor, CreditCard> interface --------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean result;
		Sponsor sponsor;
		Principal principal;
		principal = request.getPrincipal();
		sponsor = this.repository.findOneSponsorById(request.getModel().getInteger("idSponsor"));
		result = sponsor.getCreditCard() != null && sponsor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "expirationMonth", "expirationYear", "creditCardNumber", "brand", "CVV");

		int idSponsor = request.getModel().getInteger("idSponsor");
		model.setAttribute("idSponsor", idSponsor);

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

}
