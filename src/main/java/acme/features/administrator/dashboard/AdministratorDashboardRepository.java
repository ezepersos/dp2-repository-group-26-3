/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	@Query ("select count(t) from Task t where t.isPublic = true")
	Integer totalNumberOfPublicTasks();
	@Query ("select count(t) from Task t where t.isPublic = false")
	Integer totalNumberOfPrivateTasks();
	@Query ("select count(t) from Task t where t.executionPeriodEnd < CURRENT_TIMESTAMP")
	Integer totalNumberOfFinishedTasks();
	@Query ("select count(t) from Task t where t.executionPeriodEnd > CURRENT_TIMESTAMP")
	Integer totalNumberOfNonFinishedTasks();
	@Query("select avg(datediff(t.executionPeriodEnd,t.executionPeriodInit)) from Task t")
	Double averageTaskExecutionPeriods();
	@Query ("select stddev(datediff(t.executionPeriodInit, t.executionPeriodEnd)) from Task t")
	Double deviationTaskExecutionPeriods();

	@Query ("select min(datediff(t.executionPeriodEnd,t.executionPeriodInit)) from Task t")
	Double minimumTaskExecutionPeriods();

	@Query ("select max(datediff(t.executionPeriodEnd,t.executionPeriodInit)) from Task t")
	Double maximumTaskExecutionPeriods();
	@Query ("select t from Task t")
	List<Task> allTasks();
	/*
	@Query ("select count(t) from Task t")
	Double deviationTaskWorloads();
	@Query ("select count(t) from Task t")
	Double minimumTaskWorloads();
	@Query ("select count(t) from Task t")
	Double maximumTaskWorloads();
	
*/
}
