
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.participations.Participation;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select p.participant from Participation p where p.thread.id = ?1")
	Collection<Authenticated> findManyAuthenticatedByMessageThreadId(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select p from Participation p where p.thread.id = ?1 and p.participant.id = ?2")
	Participation findParticipationByMessageThreadIdAndParticipantId(int threadId, int participantId);

	@Query("select a from Authenticated a where a not in (select p.participant from Participation p where p.thread.id = ?1)")
	Collection<Authenticated> findManyNotParticipantByMessageThreadId(int id);

}
