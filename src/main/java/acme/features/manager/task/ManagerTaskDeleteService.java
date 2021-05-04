package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.services.AbstractDeleteService;

public class ManagerTaskDeleteService implements AbstractDeleteService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractDeleteService<Employer, Job> interface -------------------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int taskId;

		Task task;
		

		taskId = request.getModel().getInteger("id");
		task = this.repository.findById(taskId);
		final Collection<Task> collection = this.repository.findTasksByManager(request.getPrincipal().getActiveRoleId());

		result = collection.contains(task);
		System.out.println(result);
		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionPeriodInit", "executionPeriodEnd", "description", "optionalLink", "isPublic");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		System.out.println("llega");
		this.repository.delete(entity);
		System.out.println("llega2");
	}

}
