
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

	//@Query("select m.messageThread from Message m where m.user.id = ?1")
	@Query("select mt from MessageThread mt where (select ur from UserRole ur where ur.id = ?1) member of mt.usersInvolved")
	Collection<MessageThread> findManyMessageThreadsByAuthenticatedId(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyMessagesByMessageThreadId(int id);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneById(int id);

	//@Query("select ur from UserRole ur where ur not member of (select mt.usersInvolved from MessageThread mt where mt.id = ?1)")
	//Collection<UserRole> findManyUsersToInvolve(int id);
}
