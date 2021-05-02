package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;

@Controller
@RequestMapping("/anonymous/task/")
public class ManagerTaskController extends AbstractController<Manager, Task> { 
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskListService	listService;
	
	
	@Autowired
	protected ManagerTaskShowService	showService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}