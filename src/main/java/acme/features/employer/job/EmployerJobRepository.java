
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findOneEmployerById(int id);

	@Query("select d.timePercentage from Duty d where d.job.id = ?1")
	Collection<Integer> findManyDutiesTimePercentageByJobId(int id);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyDutiesByJobId(int id);

	@Query("select count(a.worker) from Application a where a.job.id = ?1")
	Long numWorkersByJobId(int id);

	@Query("select j from Job j where j.reference = ?1")
	Job findOneJobByReference(String reference);

}
