
package acme.features.administrator.chart;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	@Query("select count(c), c.sector from CompanyRecord c group by c.sector")
	Collection<Object[]> numCompaniesBySector();

	@Query("select count(i), i.sector from InvestorRecord i group by i.sector")
	Collection<Object[]> numInvestorsBySector();

	@Query("select 1.0 * count(a) / (select count(b) from Job b), a.finalMode from Job a group by a.finalMode")
	Collection<Object[]> ratioOfJobsGroupedByStatus();

	@Query("select 1.0 * count(a) / (select count(b) from Application b), a.status from Application a group by a.status")
	Collection<Object[]> ratioOfApplicationsGroupedByStatus();

	@Query("select count(a), cast(a.moment as date) from Application a where a.status='pending' and a.moment >= ?1 group by cast(a.moment as date)")
	Collection<Object[]> numPendingApplicationsPerDays(Date fourWeeks);

	@Query("select count(a), cast(a.moment as date) from Application a where a.status='accepted' and a.moment >= ?1 group by cast(a.moment as date)")
	Collection<Object[]> numAcceptedApplicationsPerDays(Date fourWeeks);

	@Query("select count(a), cast(a.moment as date) from Application a where a.status='rejected' and a.moment >= ?1 group by cast(a.moment as date)")
	Collection<Object[]> numRejectedApplicationsPerDays(Date fourWeeks);

}
