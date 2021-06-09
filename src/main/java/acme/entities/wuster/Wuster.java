package acme.entities.wuster;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wuster extends DomainEntity{

	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	protected String identifier;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Future
	protected LocalDate deadline;
	@NotNull
	protected Double budget;
	@NotNull
	protected Budge currencyType;
	@NotNull
	protected Boolean important;
}
