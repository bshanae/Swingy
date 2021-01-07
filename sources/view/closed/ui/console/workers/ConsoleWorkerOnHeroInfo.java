package view.closed.ui.console.workers;

import model.open.Pockets;
import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.open.Context;

public class					ConsoleWorkerOnHeroInfo extends ConsoleWorker
{
// ---------------------------> Constants

	private static final String	PATH_TO_TEMPLATE = "view/console/templates/HeroInfo.txt";

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
		Requests.HeroInfo		heroInfoRequest;
		ConsoleTemplate			template;

		heroInfoRequest = (Requests.HeroInfo)request;
		template = new ConsoleTemplate(PATH_TO_TEMPLATE);

		template.put("TITLE", "Hero info", ConsoleTemplate.Style.BOLD);

		template.put("CLASS", heroInfoRequest.getHero().getHeroClass());
		template.put("LEVEL", String.valueOf(heroInfoRequest.getHero().getLevel()));
		template.put("EXPERIENCE", String.valueOf(heroInfoRequest.getHero().getExperience()));

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
