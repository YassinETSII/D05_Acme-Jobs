
package acme.entities.participations;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Participation extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	// Relationships ------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	Authenticated				participant;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	MessageThread				thread;

}
