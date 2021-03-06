package swingy.view.closed.ui.console.workers;

import swingy.model.open.Pockets;
import swingy.model.open.Requests;
import swingy.view.closed.ui.console.ConsoleWorker;
import swingy.view.closed.ui.console.utils.ConsoleTemplate;
import swingy.view.open.Context;

public class					ConsoleWorkerOnHeroInventory extends ConsoleWorker
{
// ---------------------------> Implementations

	@Override
	public void					execute(Requests.Abstract request)
	{
		clean();
		write(getText(request));
		promptInput(Context.parse(request));
	}

// ---------------------------> Private methods

	private String				getText(Requests.Abstract request)
	{
		Requests.HeroInventory	heroInfoRequest;
		ConsoleTemplate			template;

		heroInfoRequest = (Requests.HeroInventory)request;
		template = new ConsoleTemplate("view/console/Template-HeroInventory.txt");

		template.put("TITLE", "Hero info", ConsoleTemplate.Style.BOLD);

		template.put("HELM", getArtefactName(heroInfoRequest.getInventory().getHelm()));
		template.put("ARMOR", getArtefactName(heroInfoRequest.getInventory().getArmor()));
		template.put("WEAPON", getArtefactName(heroInfoRequest.getInventory().getWeapon()));

		return template.toString();
	}

	private String					getArtefactName(Pockets.Artefact artefact)
	{
		return artefact != null ? artefact.getName() : "none";
	}
}
