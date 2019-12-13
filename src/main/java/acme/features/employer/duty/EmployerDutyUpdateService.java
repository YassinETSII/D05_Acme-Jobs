
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerDutyRepository repository;


	// AbstractUpdateService<Employer, Duty> interface --------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "job");
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "timePercentage");
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Validation of timePercentage

		if (!errors.hasErrors("timePercentage")) {
			Collection<Duty> duties = this.repository.findManyDutiesByDutyId(request.getModel().getInteger("id"));
			int oldDuty = this.repository.findOneDutyById(request.getModel().getInteger("id")).getTimePercentage();

			int sumOldDuties = duties.stream().mapToInt(t -> t.getTimePercentage()).sum();

			int newDuty = entity.getTimePercentage();

			int sum = sumOldDuties + newDuty - oldDuty;

			boolean weeklyWorkload = sum <= 100;
			errors.state(request, weeklyWorkload, "timePercentage", "employer.duty.error.weeklyWorkload");
		}

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
