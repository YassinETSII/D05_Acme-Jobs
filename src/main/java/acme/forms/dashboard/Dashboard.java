
package acme.forms.dashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberAnnouncements;
	Integer						numberCompanyRecords;
	Integer						numberInvestorRecords;

	Double						minimumNumberOfRewardsOfActiveRequests;
	Double						maximumNumberOfRewardsOfActiveRequests;
	Double						averageNumberOfRewardsOfActiveRequests;
	Double						standardDesviationNumberOfRewardsOfActiveRequests;

	Double						minimumNumberOfRewardsOfActiveOffers;
	Double						maximumNumberOfRewardsOfActiveOffers;
	Double						averageNumberOfRewardsOfActiveOffers;
	Double						standardDesviationNumberOfRewardsOfActiveOffers;

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;

}
