
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
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractUpdateService<Employer, Job> interface --------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();
		return result;
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

		request.unbind(entity, model, "title", "deadline", "salary", "description", "moreInfo", "finalMode");

	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Validation of deadline
		Calendar calendar;
		Date deadlineMoment, nextWeek;
		boolean activeDeadline;
		if (!errors.hasErrors("deadline")) { //Check if deadline has no errors
			deadlineMoment = entity.getDeadline();
			calendar = new GregorianCalendar();
			calendar.add(Calendar.WEEK_OF_MONTH, 1);
			nextWeek = calendar.getTime();
			activeDeadline = deadlineMoment.after(nextWeek);
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

		//Validation of 100% percentage and finalMode Job
		int sumDutiesWorkload = this.repository.findManyDutiesTimePercentageByJobId(entity.getId()).stream().mapToInt(t -> t).sum();
		boolean sum100, finalModeJobWith100Workload;
		sum100 = sumDutiesWorkload == 100;
		finalModeJobWith100Workload = entity.isFinalMode() == true && sum100 || entity.isFinalMode() == false && (sum100 || !sum100);
		errors.state(request, finalModeJobWith100Workload, "*", "employer.job.error.dutiesPercentage");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Employer employer = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		entity.setEmployer(employer);
		this.repository.save(entity);

	}

}
