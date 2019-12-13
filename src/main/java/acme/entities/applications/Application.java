
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status"), @Index(columnList = "reference asc, status asc, moment desc")
})
public class Application extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	private String				reference;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	@Pattern(regexp = "^pending|accepted|rejected$")
	private String				status;

	@NotBlank
	@Column(length = 1024)
	private String				statement;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	@Column(length = 1024)
	private String				justification;
	// Relationships ------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;
}
