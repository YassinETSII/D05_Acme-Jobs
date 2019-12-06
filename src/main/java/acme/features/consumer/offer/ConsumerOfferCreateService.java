
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


	// AbstractCreateService<Consumer, Offer> interface --------------

	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "minReward", "maxReward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		assert request != null;
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean tickerDuplicated, isAccepted, acceptedMinCurrency, acceptedMaxCurrency, sequentialRangeAmount, activeDeadline;
		String minCurrency, maxCurrency;
		Double minAmount, maxAmount;
		Calendar calendar;
		Date deadlineMoment, currentMoment;

		if (!errors.hasErrors("deadline")) { //Check if deadline has no errors
			deadlineMoment = entity.getDeadline();
			calendar = new GregorianCalendar();
			currentMoment = calendar.getTime();
			activeDeadline = deadlineMoment.after(currentMoment);
			errors.state(request, activeDeadline, "deadline", "consumer.offer.error.deadline");
		}

		if (!errors.hasErrors("ticker")) { //Check if ticker has no errors
			tickerDuplicated = this.repository.findOneOfferByTicker(entity.getTicker()) != null;
			errors.state(request, !tickerDuplicated, "ticker", "consumer.offer.error.tickerDuplicated");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "consumer.offer.error.must-accept");

		if (!errors.hasErrors("minReward")) { //Check if minReward has no errors
			minCurrency = entity.getMinReward().getCurrency();
			acceptedMinCurrency = minCurrency.equals("EUR") || minCurrency.equals("€");
			errors.state(request, acceptedMinCurrency, "minReward", "consumer.offer.error.minCurrency");
		}

		if (!errors.hasErrors("maxReward")) { //Check if maxReward has no errors
			maxCurrency = entity.getMaxReward().getCurrency();
			acceptedMaxCurrency = maxCurrency.equals("EUR") || maxCurrency.equals("€");
			errors.state(request, acceptedMaxCurrency, "maxReward", "consumer.offer.error.maxCurrency");
		}

		//Check if range of rewards is sequential
		if (!errors.hasErrors("minReward") && !errors.hasErrors("maxReward")) { //Check if rewards have no errors
			minAmount = entity.getMinReward().getAmount();
			maxAmount = entity.getMaxReward().getAmount();
			sequentialRangeAmount = minAmount < maxAmount;
			errors.state(request, sequentialRangeAmount, "minReward", "consumer.offer.error.rangeAmount");
		}
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
