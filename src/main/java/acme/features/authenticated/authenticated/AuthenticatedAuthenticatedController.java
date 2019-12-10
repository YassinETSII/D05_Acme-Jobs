
package acme.features.authenticated.authenticated;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated/")
public class AuthenticatedAuthenticatedController extends AbstractController<Authenticated, Authenticated> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuthenticatedListService				listService;
	@Autowired
	private AuthenticatedAuthenticatedShowService				showService;
	@Autowired
	private AuthenticatedAuthenticatedAddToThreadService		addToThreadService;
	@Autowired
	private AuthenticatedAuthenticatedRemoveFromThreadService	removeFromThreadService;
	@Autowired
	private AuthenticatedAuthenticatedListToAddService			listToAddService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.ADD_TO_THREAD, BasicCommand.UPDATE, this.addToThreadService);
		super.addCustomCommand(CustomCommand.REMOVE_FROM_THREAD, BasicCommand.DELETE, this.removeFromThreadService);
		super.addCustomCommand(CustomCommand.LIST_TO_ADD, BasicCommand.LIST, this.listToAddService);
	}
}
