
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select a.job from AuditRecord a where a.job.finalMode = true and a.auditor.id = ?1")
	Collection<Job> findManyByAuditorId(int employerId);

	@Query("select j from Job j where j.finalMode = true and j.id not in(select a.job.id from AuditRecord a where a.job.finalMode = true and a.auditor.id = ?1)")
	Collection<Job> findManyByNotAuditorId(int employerId);

}
