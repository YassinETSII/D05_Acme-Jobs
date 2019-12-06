
package acme.features.anonymous.laljbulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.LaljBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/lalj-bulletin/")
public class AnonymousLaljBulletinController extends AbstractController<Anonymous, LaljBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousLaljBulletinListService	listService;
	@Autowired
	private AnonymousLaljBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
