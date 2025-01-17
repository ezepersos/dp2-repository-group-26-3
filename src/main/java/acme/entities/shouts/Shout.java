
package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.wuster.Wuster;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				moment;
	@Length(min=5,max=25)
	@NotBlank
	protected String			author;
	@Length(max=100)
	@NotBlank
	protected String			text;
	@URL
	protected String			info;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@OneToOne
	@JoinColumn(name = "informationSheetId", referencedColumnName = "id")
	protected Wuster informationSheet;

}
