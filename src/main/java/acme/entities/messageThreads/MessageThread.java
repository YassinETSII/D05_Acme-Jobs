
package acme.entities.messageThreads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class MessageThread extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	// Relationships ------------------------------------------------------------

	@NotNull
	@Valid
	@OneToMany
	Collection<UserRole>		usersInvolved;


	public void addUser(final UserRole user) {
		assert user != null;
		assert !this.usersInvolved.contains(user);

		if (this.usersInvolved == null) {
			this.usersInvolved = new ArrayList<UserRole>();
		}

		this.usersInvolved.add(user);
	}

	public void removeRole(final UserRole user) {
		assert user != null;
		assert this.usersInvolved.contains(user);

		this.usersInvolved.remove(user);
	}

}
