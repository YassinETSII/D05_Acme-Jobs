
package acme.features.employer.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerApplicationRepository repository;


	// AbstractUpdateService<Employer, Application> interface --------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "justification", "job.reference", "worker.identity.fullName");

		if (entity.getStatus() == "accepted") {
			model.setAttribute("status", "accepted");
		} else {
			model.setAttribute("status", "rejected");
		}

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "worker", "job");
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {

		/*
		 * //Validation of reference
		 * boolean referenceDuplicated;
		 * if (!errors.hasErrors("reference")) { //Check if reference has no errors
		 * referenceDuplicated = this.repository.findOneApplicationByReference(entity.getReference()) != null;
		 * errors.state(request, !referenceDuplicated, "reference", "employer.application.error.referenceDuplicated");
		 * }
		 */

		//Validation of justification
		boolean justification;
		if (!errors.hasErrors("justification")) {
			justification = entity.getStatus().equals("rejected") && !entity.getJustification().isEmpty() || entity.getStatus().equals("accepted") && (entity.getJustification().isEmpty() || !entity.getJustification().isEmpty());
			errors.state(request, justification, "justification", "employer.application.error.justification");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
