/*
 * AuthenticatedAuditorCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;

	// AbstractCreateService<Authenticated, AuditorRequest> ---------------------------


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement");
	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Principal principal;
		int userId;
		Authenticated user;

		principal = request.getPrincipal();
		userId = principal.getActiveRoleId();
		user = this.repository.findOneUserById(userId);

		result = new AuditorRequest();
		result.setUser(user);
		result.setStatus("pending");

		return result;
	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
