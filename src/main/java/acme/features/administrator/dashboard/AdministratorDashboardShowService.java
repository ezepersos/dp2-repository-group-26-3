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

		request.unbind(entity, model, "totalNumberOfPublicTasks", "totalNumberOfPrivateTasks", "totalNumberOfFinishedTasks", "totalNumberOfNonFinishedTasks", "averageTaskExecutionPeriods", "deviationTaskExecutionPeriods", "minimumTaskExecutionPeriods",
			"maximumTaskExecutionPeriods", "averageTaskWorloads", "averageTaskWorloads", "deviationTaskWorloads", "minimumTaskWorloads", "maximumTaskWorloads", "ratioShoutsImportant", "ratioShoutsBudgetZero", "averageEUR", "averageDOLAR", "deviationEUR",
			"deviationDOLAR");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Integer totalNumberOfPublicTasks;
		Integer totalNumberOfPrivateTasks;
		Integer totalNumberOfFinishedTasks;
		Integer totalNumberOfNonFinishedTasks;
		Double averageTaskExecutionPeriods;
		Double deviationTaskExecutionPeriods;
		Double minimumTaskExecutionPeriods;
		Double maximumTaskExecutionPeriods;
		Double averageTaskWorloads;
		Double deviationTaskWorloads;
		Double minimumTaskWorloads;
		Double maximumTaskWorloads;
		Double ratioShoutsImportant;
		Double ratioShoutsBudgetZero;
		Double averageEUR;
		Double averageDOLAR;
		Double deviationEUR;
		Double deviationDOLAR;

		final List<Task> totalTasks = this.repository.allTasks();
		averageTaskWorloads = this.checkValue(this.calculateWorkloadAverage(totalTasks));
		deviationTaskWorloads = this.checkValue(this.calculateWorkloadDeviation(totalTasks));
		totalNumberOfPublicTasks = this.repository.totalNumberOfPublicTasks();
		totalNumberOfPrivateTasks = this.repository.totalNumberOfPrivateTasks();
		totalNumberOfFinishedTasks = this.repository.totalNumberOfFinishedTasks();
		totalNumberOfNonFinishedTasks = this.repository.totalNumberOfNonFinishedTasks();
		averageTaskExecutionPeriods = this.checkValue(this.repository.averageTaskExecutionPeriods());
		deviationTaskExecutionPeriods = this.checkValue(this.repository.deviationTaskExecutionPeriods());
		minimumTaskExecutionPeriods = this.checkValue(this.repository.minimumTaskExecutionPeriods());
		maximumTaskExecutionPeriods = this.checkValue(this.repository.maximumTaskExecutionPeriods());
		minimumTaskWorloads = this.checkValue(this.takeMinimum(totalTasks));
		maximumTaskWorloads = this.checkValue(this.takeMaximum(totalTasks));

		final Integer totalShouts = this.repository.totalShouts();
		ratioShoutsImportant = this.checkValue( 100 - (double) this.repository.ratioShoutsImportant() / totalShouts * 100);
		ratioShoutsBudgetZero = this.checkValue( 100 - (double) this.repository.ratioShoutsBudgetZero() / totalShouts * 100);
		averageEUR = this.checkValue(this.repository.averageEUR());
		averageDOLAR = this.checkValue(this.repository.averageDOLAR());
		deviationEUR = this.checkValue(this.repository.deviationEUR());
		deviationDOLAR = this.checkValue(this.repository.deviationDOLAR());
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
		result.setRatioShoutsImportant(ratioShoutsImportant);
		result.setRatioShoutsBudgetZero(ratioShoutsBudgetZero);
		result.setAverageEUR(averageEUR);
		result.setAverageDOLAR(averageDOLAR);
		result.setDeviationEUR(deviationEUR);
		result.setDeviationDOLAR(deviationDOLAR);
		return result;
	}

	private Double checkValue(final Double value) {
		Double res = value;
		if (value == null || value.isNaN()) {
			res = .0;
		}
		return res;
	}

	private Double takeMaximum(final List<Task> totalTasks) {
		if (totalTasks.isEmpty()) {
			return .0;
		} else {

			Double res = Double.MAX_VALUE;
			for (final Task t : totalTasks) {
				if (res > t.workload()) {
					res = t.workload();
				}
			}
			return res;
		}
	}

	private Double takeMinimum(final List<Task> totalTasks) {
		if (totalTasks.isEmpty()) {
			return .0;
		} else {

			Double res = Double.MIN_VALUE;
			for (final Task t : totalTasks) {
				if (res < t.workload()) {
					res = t.workload();
				}
			}
			return res;
		}
	}

	private Double calculateWorkloadDeviation(final List<Task> totalTasks) {
		Double totalWorkload = .0;
		Double deviation = .0;
		final Integer numberOfTasks = totalTasks.size();
		for (final Task task : totalTasks) {
			totalWorkload += task.workload();
		}
		final Double mean = totalWorkload / numberOfTasks;
		for (final Task task : totalTasks) {
			deviation += Math.pow(task.workload() - mean, 2);

		}
		return Math.sqrt(deviation / numberOfTasks);
	}

	private Double calculateWorkloadAverage(final List<Task> totalTasks) {
		Double average = .0;
		for (int i = 0; i < totalTasks.size(); i++) {
			average += totalTasks.get(i).workload();
		}
		average /= totalTasks.size();
		return average;
	}

}
