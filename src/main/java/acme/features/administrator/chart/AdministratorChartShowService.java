
package acme.features.administrator.chart;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.chart.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorChartRepository repository;


	// AbstractShowService<Administrator, Chart> interface --------------

	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "companySector", "companyNumber", "investorSector", "investorNumber", "finalMode", "ratioOfJobs", "ApplicationStatus", "ratioOfApplications");

	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		assert request != null;
		Chart result = new Chart();

		List<Long> companyNumber = new LinkedList<>();
		List<String> companySector = new LinkedList<>();
		List<Long> investorNumber = new LinkedList<>();
		List<String> investorSector = new LinkedList<>();
		List<Double> ratioOfJobs = new LinkedList<>();
		List<String> finalMode = new LinkedList<>();
		List<String> ApplicationStatus = new LinkedList<>();
		List<Double> ratioOfApplications = new LinkedList<>();
		//--------------------------------------------------companies
		Collection<Object[]> companies = this.repository.numCompaniesBySector();

		companies.stream().forEach(c -> companyNumber.add((Long) c[0]));
		companies.stream().forEach(c -> companySector.add((String) c[1]));

		result.setCompanyNumber(companyNumber);
		result.setCompanySector(companySector);
		//--------------------------------------------------investors
		Collection<Object[]> investors = this.repository.numInvestorsBySector();

		investors.stream().forEach(i -> investorNumber.add((Long) i[0]));
		investors.stream().forEach(i -> investorSector.add((String) i[1]));

		result.setInvestorNumber(investorNumber);
		result.setInvestorSector(investorSector);
		//--------------------------------------------------jobs
		Collection<Object[]> jobs = this.repository.ratioOfJobsGroupedByStatus();

		jobs.stream().forEach(j -> ratioOfJobs.add((Double) j[0]));

		for (Object[] j : jobs) {
			if ((Boolean) j[1] == true) {
				finalMode.add("Final mode");
			} else {
				finalMode.add("Draft mode");
			}
		}

		result.setRatioOfJobs(ratioOfJobs);
		result.setFinalMode(finalMode);
		//--------------------------------------------------applications
		Collection<Object[]> applications = this.repository.ratioOfApplicationsGroupedByStatus();

		applications.stream().forEach(a -> ratioOfApplications.add((Double) a[0]));
		applications.stream().forEach(a -> ApplicationStatus.add((String) a[1]));

		result.setRatioOfApplications(ratioOfApplications);
		result.setApplicationStatus(ApplicationStatus);

		return result;
	}

}
