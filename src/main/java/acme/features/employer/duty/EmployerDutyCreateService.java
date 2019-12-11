
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerDutyRepository repository;


	// AbstractCreateService<Administrator, Duty> interface --------------

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

		int idJob = request.getModel().getInteger("idJob");
		model.setAttribute("idJob", idJob);
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;
		Duty result;
		result = new Duty();
		int idJob = request.getModel().getInteger("idJob");
		Job job = this.repository.findOneJobById(idJob);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("timePercentage")) {
			int idJob = request.getModel().getInteger("idJob");
			Collection<Duty> duties = this.repository.findManyDutiesByJobId(idJob);
			int sum = duties.stream().mapToInt(t -> t.getTimePercentage()).sum() + entity.getTimePercentage();
			System.out.println(sum);
			boolean weeklyWorkload = sum <= 100;
			errors.state(request, weeklyWorkload, "timePercentage", "employer.duty.error.weeklyWorkload");
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
