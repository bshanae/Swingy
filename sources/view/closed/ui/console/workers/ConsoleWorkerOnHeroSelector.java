package view.closed.ui.console.workers;

import model.open.Pockets;
import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.open.Context;

import java.util.List;

public class					ConsoleWorkerOnHeroSelector extends ConsoleWorker
{
// ---------------------------> Constants

	private static final String	PATH_TO_TEMPLATE = "view/console/Template-HeroSelector.txt";

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
		Requests.HeroSelector	heroSelectorRequest;
		ConsoleTemplate			template;
		List<Pockets.Hero>		heroes;

		heroSelectorRequest = (Requests.HeroSelector)request;
		template = new ConsoleTemplate(PATH_TO_TEMPLATE);

		template.put("TITLE", "Choose hero : ", ConsoleTemplate.Style.BOLD);
		template.put("COMMANDS", "Commands : ", ConsoleTemplate.Style.BOLD);

		heroes = heroSelectorRequest.getHeroes();
		for (int i = 0; i < 4; i++)
			template.put("HERO" + i, getHeroName(heroes, i));

		return template.toString();
	}

	private String				getHeroName(List<Pockets.Hero> heroes, int index)
	{
		return index < heroes.size() ? heroes.get(index).getName() : "Empty";
	}
}
