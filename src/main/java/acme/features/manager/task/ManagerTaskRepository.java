package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {
	@Query("select t from Task t where t.manager=?1")
	Collection<Task> findMany(Manager m);
	
	@Query("select t from Task t where t.id=?1")
	Task findById(int id);

}
