
package acme.features.employer.job;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerJobRepository repository;


	// AbstractDeleteService<Administrator, Job> interface --------------

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
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<Application> applications;
		int idJob;
		boolean deleteApplicatedJob;

		idJob = entity.getId();
		applications = this.repository.findManyApplicationsByJobId(idJob);
		deleteApplicatedJob = applications.stream().map(w -> w.getWorker()).collect(Collectors.toList()).isEmpty();

		errors.state(request, deleteApplicatedJob, "*", "employer.job.error.deleteJobWithApplication");
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
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Collection<Duty> duties = this.repository.findManyDutiesByJobId(entity.getId());
		if (!duties.isEmpty()) {
			duties.stream().forEach(d -> this.repository.delete(d));
		}

		this.repository.delete(entity);
	}

}
