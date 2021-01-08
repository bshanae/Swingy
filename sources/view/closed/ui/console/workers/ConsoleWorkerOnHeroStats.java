package view.closed.ui.console.workers;

import model.open.Pockets;
import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.open.Context;

public class					ConsoleWorkerOnHeroStats extends ConsoleWorker
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
		Requests.HeroStats		heroInfoRequest;
		ConsoleTemplate			template;

		heroInfoRequest = (Requests.HeroStats)request;
		template = new ConsoleTemplate("view/console/Template-HeroStats.txt");

		template.put("TITLE", "Hero info", ConsoleTemplate.Style.BOLD);

		template.put("CLASS", heroInfoRequest.getHero().getHeroClass());
		template.put("LEVEL", String.valueOf(heroInfoRequest.getHero().getLevel()));
		template.put("EXPERIENCE", String.valueOf(heroInfoRequest.getHero().getExperience()));

		return template.toString();
	}
}
