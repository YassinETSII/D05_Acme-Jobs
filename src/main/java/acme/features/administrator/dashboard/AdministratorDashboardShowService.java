
package acme.features.administrator.dashboard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.dashboard.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;


	// AbstractShowService<Administrator, Dashboard> interface --------------

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberAnnouncements", "numberCompanyRecords", "numberInvestorRecords", "minimumNumberOfRewardsOfActiveRequests", "maximumNumberOfRewardsOfActiveRequests", "averageNumberOfRewardsOfActiveRequests",
			"standardDesviationNumberOfRewardsOfActiveRequests", "minimumNumberOfRewardsOfActiveOffers", "maximumNumberOfRewardsOfActiveOffers", "averageNumberOfRewardsOfActiveOffers", "standardDesviationNumberOfRewardsOfActiveOffers",
			"averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		Dashboard result = new Dashboard();

		Calendar c = new GregorianCalendar();
		Date d = c.getTime();

		result.setNumberAnnouncements(this.repository.numberAnnouncements());
		result.setNumberCompanyRecords(this.repository.numberCompanyRecords());
		result.setNumberInvestorRecords(this.repository.numberInvestorRecords());

		result.setMinimumNumberOfRewardsOfActiveRequests(this.repository.minimumNumberOfRewardsOfActiveRequests(d));
		result.setMaximumNumberOfRewardsOfActiveRequests(this.repository.maximumNumberOfRewardsOfActiveRequests(d));
		result.setAverageNumberOfRewardsOfActiveRequests(this.repository.averageNumberOfRewardsOfActiveRequests(d));
		result.setStandardDesviationNumberOfRewardsOfActiveRequests(this.repository.standardDesviationNumberOfRewardsOfActiveRequests(d));

		result.setMinimumNumberOfRewardsOfActiveOffers(this.repository.minimumNumberOfRewardsOfActiveOffers(d));
		result.setMaximumNumberOfRewardsOfActiveOffers(this.repository.maximumNumberOfRewardsOfActiveOffers(d));
		result.setAverageNumberOfRewardsOfActiveOffers(this.repository.averageNumberOfRewardsOfActiveOffers(d));
		result.setStandardDesviationNumberOfRewardsOfActiveOffers(this.repository.standardDesviationNumberOfRewardsOfActiveOffers(d));

		result.setAverageNumberOfJobsPerEmployer(this.repository.averageNumberOfJobsPerEmployer());
		result.setAverageNumberOfApplicationsPerEmployer(this.repository.averageNumberOfApplicationsPerEmployer());
		result.setAverageNumberOfApplicationsPerWorker(this.repository.averageNumberOfApplicationsPerWorker());

		return result;
	}

}
