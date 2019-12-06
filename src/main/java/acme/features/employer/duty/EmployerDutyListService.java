
package acme.features.employer.duty;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerDutyListService implements AbstractListService<Employer, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerDutyRepository repository;

	// AbstractListService<Employer, Duty> interface --------------


	//An employer principal can not list the duties of a not finalMode job from another employer
	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result;
		Principal principal = request.getPrincipal();
		Duty duty;
		int idEmployer;
		LinkedList<Duty> dut = new LinkedList<>();

		//Here we obtain all the duties of a job by his id that we call idJob.
		int idJob = request.getModel().getInteger("idJob");
		Collection<Duty> duties = this.repository.findManyDutiesByJobId(idJob);

		//Now we add all elements of the collection of duties on a list to obtain a single duty
		//so we can obtain the employer and his respective id.
		dut.addAll(duties);
		duty = dut.get(0);
		idEmployer = duty.getJob().getEmployer().getUserAccount().getId();

		Predicate<Duty> condition1 = d -> d.getJob().isFinalMode() == true;
		Predicate<Duty> condition2 = d -> d.getJob().isFinalMode() == false;
		boolean condition3 = idEmployer == principal.getAccountId();

		result = duties.stream().allMatch(condition1) || duties.stream().anyMatch(condition2) && condition3;
		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> result;

		int id = request.getModel().getInteger("idJob");
		result = this.repository.findManyDutiesByJobId(id);
		return result;

	}

}
