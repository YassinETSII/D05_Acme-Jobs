
package acme.entities.companyRecords;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars")
})
public class CompanyRecord extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				company;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEO;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotBlank
	@URL
	private String				web;

	@NotBlank
	@Pattern(regexp = "^(([+][1-9]{1}[0-9]{0,2}[ ])?)(([(][0-9]{1,4}[)][ ])?)[0-9]{6,10}$", message = "{administrator.companyRecord.error.administratorPhoneFormat}")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	private Boolean				incorporated;

	@Min(0)
	@Max(5)
	private Integer				stars;

	// Derived Attributes -------------------------------------------------------

	// Relationships ------------------------------------------------------------

}
