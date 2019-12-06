
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select m.messageThread from Message m where m.user.id = ?1")
	Collection<MessageThread> findManyMessageThreadsByAuthenticatedId(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyMessagesByMessageThreadId(int id);
}
