
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractCreateService<Administrator, Job> interface --------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "employer");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;
		Job result;
		result = new Job();
		Employer employer = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		result.setEmployer(employer);

		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Validation of reference
		boolean referenceDuplicated;
		if (!errors.hasErrors("reference")) { //Check if reference has no errors
			referenceDuplicated = this.repository.findOneJobByReference(entity.getReference()) != null;
			errors.state(request, !referenceDuplicated, "reference", "employer.job.error.referenceDuplicated");
		}

		//Validation of deadline
		Calendar calendar;
		Date deadlineMoment, currentMoment;
		boolean activeDeadline;
		if (!errors.hasErrors("deadline")) { //Check if deadline has no errors
			deadlineMoment = entity.getDeadline();
			calendar = new GregorianCalendar();
			currentMoment = calendar.getTime();
			activeDeadline = deadlineMoment.after(currentMoment);
			errors.state(request, activeDeadline, "deadline", "employer.job.error.deadline");
		}

		//Validation of salary
		String salaryCurrency;
		boolean acceptedSalaryCurrency;
		if (!errors.hasErrors("salary")) { //Check if salary has no errors
			salaryCurrency = entity.getSalary().getCurrency();
			acceptedSalaryCurrency = salaryCurrency.equals("EUR") || salaryCurrency.equals("€");
			errors.state(request, acceptedSalaryCurrency, "salary", "employer.job.error.salary");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
