
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestUpdateService implements AbstractUpdateService<Administrator, AuditorRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorAuditorRequestRepository repository;


	// AbstractUpdateService<Administrator, AuditorRequest> interface --------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		boolean result;
		int auditorRequestId;
		AuditorRequest auditorRequest;

		auditorRequestId = request.getModel().getInteger("id");
		auditorRequest = this.repository.findOneById(auditorRequestId);
		result = auditorRequest.getStatus().equals("pending");

		return result;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement", "user.identity.fullName", "status");

		if (entity.getStatus() == "accepted") {
			model.setAttribute("status", "accepted");
		} else {
			model.setAttribute("status", "rejected");
		}
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;
		AuditorRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals("accepted")) {
			Auditor a = new Auditor();
			a.setFirm(entity.getFirm());
			a.setResponsibilityStatement(entity.getResponsibilityStatement());
			a.setUserAccount(entity.getUser().getUserAccount());
			this.repository.save(a);
		}
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<AuditorRequest> request, final Response<AuditorRequest> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
