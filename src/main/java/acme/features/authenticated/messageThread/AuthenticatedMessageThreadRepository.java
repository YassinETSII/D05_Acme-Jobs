
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.participations.Participation;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	//@Query("select m.messageThread from Message m where m.user.id = ?1")
	@Query("select p.thread from Participation p where p.participant.id = ?1")
	Collection<MessageThread> findManyMessageThreadsByAuthenticatedId(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyMessagesByMessageThreadId(int id);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select p from Participation p where p.thread.id = ?1")
	Collection<Participation> findManyParticipationByThreadId(int id);

	@Query("select p.participant from Participation p where p.thread.id = ?1")
	Collection<Authenticated> findManyAuthenticatedByThreadId(int id);

}
