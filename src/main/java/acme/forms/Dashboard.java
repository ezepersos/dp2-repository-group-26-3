/*
 * Dashboard.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer 					totalNumberOfPublicTasks;
	Integer						totalNumberOfPrivateTasks;
	Integer						totalNumberOfFinishedTasks;
	Integer						totalNumberOfNonFinishedTasks;
	Double						averageTaskExecutionPeriods;
	Double						deviationTaskExecutionPeriods;
	Double						minimumTaskExecutionPeriods;
	Double						maximumTaskExecutionPeriods;
	Double						averageTaskWorloads;
	Double						deviationTaskWorloads;
	Double						minimumTaskWorloads;
	Double						maximumTaskWorloads;
	Double						ratioShoutsImportant;
	Double						ratioShoutsBudgetZero;
	Double 						averageEUR;
	Double						averageDOLAR;
	Double						deviationEUR;
	Double						deviationDOLAR;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
