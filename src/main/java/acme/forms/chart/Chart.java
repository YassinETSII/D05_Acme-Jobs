
package acme.forms.chart;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	List<String>				companySector;
	List<Long>					companyNumber;

	List<String>				investorSector;
	List<Long>					investorNumber;

	List<String>				finalMode;
	List<Double>				ratioOfJobs;

	List<String>				applicationStatus;
	List<Double>				ratioOfApplications;

	List<String>				momentPendingApplications;
	List<String>				momentAcceptedApplications;
	List<String>				momentRejectedApplications;
	List<Long>					countPendingApplications;
	List<Long>					countAcceptedApplications;
	List<Long>					countRejectedApplications;

}
