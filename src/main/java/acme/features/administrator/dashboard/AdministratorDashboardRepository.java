
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer numberAnnouncements();

	@Query("select count(c) from CompanyRecord c")
	Integer numberCompanyRecords();

	@Query("select count(i) from InvestorRecord i")
	Integer numberInvestorRecords();

	@Query("select min(r.reward.amount) from Request r where r.deadline > ?1")
	Double minimumNumberOfRewardsOfActiveRequests(Date d);

	@Query("select max(r.reward.amount) from Request r where r.deadline > ?1")
	Double maximumNumberOfRewardsOfActiveRequests(Date d);

	@Query("select avg(r.reward.amount) from Request r where r.deadline > ?1")
	Double averageNumberOfRewardsOfActiveRequests(Date d);

	@Query("select stddev(r.reward.amount) from Request r where r.deadline > ?1")
	Double standardDesviationNumberOfRewardsOfActiveRequests(Date d);

	@Query("select min(o.minReward.amount) from Offer o where o.deadline > ?1")
	Double minimumNumberOfRewardsOfActiveOffers(Date d);

	@Query("select max(o.maxReward.amount) from Offer o where o.deadline > ?1")
	Double maximumNumberOfRewardsOfActiveOffers(Date d);

	@Query("select avg((o.minReward.amount+o.maxReward.amount)/2) from Offer o where o.deadline > ?1")
	Double averageNumberOfRewardsOfActiveOffers(Date d);

	@Query("select stddev((o.minReward.amount+o.maxReward.amount)/2) from Offer o where o.deadline > ?1")
	Double standardDesviationNumberOfRewardsOfActiveOffers(Date d);

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

}
