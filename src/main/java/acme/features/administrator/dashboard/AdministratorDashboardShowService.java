/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfPublicTasks", "totalNumberOfPrivateTasks", "totalNumberOfFinishedTasks", "totalNumberOfNonFinishedTasks", "averageTaskExecutionPeriods", "deviationTaskExecutionPeriods", "minimumTaskExecutionPeriods", "maximumTaskExecutionPeriods","averageTaskWorloads", "averageTaskWorloads", "deviationTaskWorloads", "minimumTaskWorloads", "maximumTaskWorloads");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
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

		List<Task> totalTasks = this.repository.allTasks();
		averageTaskWorloads = calculateWorkloadAverage(totalTasks);
		deviationTaskWorloads = calculateWorkloadDeviation(totalTasks);
		totalNumberOfPublicTasks = this.repository.totalNumberOfPublicTasks();
		totalNumberOfPrivateTasks = this.repository.totalNumberOfPrivateTasks();
		totalNumberOfFinishedTasks = this.repository.totalNumberOfFinishedTasks();
		totalNumberOfNonFinishedTasks = this.repository.totalNumberOfNonFinishedTasks();
		averageTaskExecutionPeriods= this.repository.averageTaskExecutionPeriods();
		deviationTaskExecutionPeriods = this.repository.deviationTaskExecutionPeriods();
		minimumTaskExecutionPeriods = this.repository.minimumTaskExecutionPeriods();
		maximumTaskExecutionPeriods = this.repository.maximumTaskExecutionPeriods();
		minimumTaskWorloads = totalTasks.stream().mapToDouble(x->x.workload()).min().orElse(0.0);
		maximumTaskWorloads = totalTasks.stream().mapToDouble(x->x.workload()).max().orElse(0.0);

		result = new Dashboard();
		result.setTotalNumberOfPublicTasks(totalNumberOfPublicTasks);
		result.setTotalNumberOfPrivateTasks(totalNumberOfPrivateTasks);
		result.setTotalNumberOfFinishedTasks(totalNumberOfFinishedTasks);
		result.setTotalNumberOfNonFinishedTasks(totalNumberOfNonFinishedTasks);
		result.setAverageTaskExecutionPeriods(averageTaskExecutionPeriods);
		result.setDeviationTaskExecutionPeriods(deviationTaskExecutionPeriods);
		result.setMaximumTaskExecutionPeriods(maximumTaskExecutionPeriods);
		result.setMinimumTaskExecutionPeriods(minimumTaskExecutionPeriods);
		result.setAverageTaskWorloads(averageTaskWorloads);
		result.setDeviationTaskWorloads(deviationTaskWorloads);
		result.setMinimumTaskWorloads(minimumTaskWorloads);
		result.setMaximumTaskWorloads(maximumTaskWorloads);
		return result;
	}

	private Double calculateWorkloadDeviation(List<Task> totalTasks) {
		Double totalWorkload = .0;
		Double deviation = .0;
		Integer numberOfTasks = totalTasks.size();
		for(Task task: totalTasks){
			totalWorkload += task.workload();
		}
		Double mean = totalWorkload/numberOfTasks;
		for(Task task: totalTasks){
			deviation += Math.pow(task.workload()-mean, 2);

		}
		return Math.sqrt(deviation/numberOfTasks);
	}

	private Double calculateWorkloadAverage(List<Task> totalTasks) {
		Double average = .0;
		for (int i = 0; i < totalTasks.size(); i++) {
			average += totalTasks.get(i).workload();
		}
		average /= totalTasks.size();
		return average;
	}
	

}
