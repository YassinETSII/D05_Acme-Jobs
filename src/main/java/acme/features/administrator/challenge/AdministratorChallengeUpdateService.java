
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	// AbstractUpdateService<Administrator, Challenge> interface --------------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goldGoal", "silverGoal", "bronzeGoal", "goldReward", "silverReward", "bronzeReward");
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean acceptedBronzeCurrency, acceptedSilverCurrency, acceptedGoldCurrency, sequentialRangeAmountBronze, sequentialRangeAmountSilver, activeDeadline;
		String bronzeCurrency, silverCurrency, goldCurrency;
		Double bronzeAmount, silverAmount, goldAmount;
		Calendar calendar;
		Date deadlineMoment, currentMoment;

		if (!errors.hasErrors("deadline")) { //Check if deadline has no errors
			deadlineMoment = entity.getDeadline();
			calendar = new GregorianCalendar();
			currentMoment = calendar.getTime();
			activeDeadline = deadlineMoment.after(currentMoment);
			errors.state(request, activeDeadline, "deadline", "administrator.challenge.error.deadline");
		}

		if (!errors.hasErrors("goldReward")) { //Check if goldReward has no errors
			goldCurrency = entity.getGoldReward().getCurrency();
			acceptedGoldCurrency = goldCurrency.equals("EUR") || goldCurrency.equals("€");
			errors.state(request, acceptedGoldCurrency, "goldReward", "administrator.challenge.error.goldCurrency");
		}

		if (!errors.hasErrors("silverReward")) { //Check if silverReward has no errors
			silverCurrency = entity.getSilverReward().getCurrency();
			acceptedSilverCurrency = silverCurrency.equals("EUR") || silverCurrency.equals("€");
			errors.state(request, acceptedSilverCurrency, "silverReward", "administrator.challenge.error.silverCurrency");
		}

		if (!errors.hasErrors("bronzeReward")) { //Check if bronzeReward has no errors
			bronzeCurrency = entity.getBronzeReward().getCurrency();
			acceptedBronzeCurrency = bronzeCurrency.equals("EUR") || bronzeCurrency.equals("€");
			errors.state(request, acceptedBronzeCurrency, "bronzeReward", "administrator.challenge.error.bronzeCurrency");
		}

		//Check if ranges of rewards are sequential
		if (!errors.hasErrors("bronzeReward") && !errors.hasErrors("silverReward") && !errors.hasErrors("goldReward")) { //Check if rewards have no errors
			bronzeAmount = entity.getBronzeReward().getAmount();
			silverAmount = entity.getSilverReward().getAmount();
			goldAmount = entity.getSilverReward().getAmount();
			sequentialRangeAmountBronze = bronzeAmount < silverAmount && bronzeAmount < goldAmount;
			errors.state(request, sequentialRangeAmountBronze, "bronzeReward", "administrator.challenge.error.rangeAmountBronze");
		}

		if (!errors.hasErrors("silverReward") && !errors.hasErrors("goldReward")) { //Check if rewards have no errors
			silverAmount = entity.getSilverReward().getAmount();
			goldAmount = entity.getGoldReward().getAmount();
			sequentialRangeAmountSilver = silverAmount < goldAmount;
			errors.state(request, sequentialRangeAmountSilver, "silverReward", "administrator.challenge.error.rangeAmountSilver");
		}
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;
		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
