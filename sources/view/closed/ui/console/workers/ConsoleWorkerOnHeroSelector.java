package view.closed.ui.console.workers;

import model.open.Pockets;
import model.open.Requests;
import application.utils.ResourceManager;
import view.closed.ui.console.utils.Template;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

import java.util.List;

public class					ConsoleWorkerOnHeroSelector extends ConsoleWorker
{
	@Override
	public void					execute(Requests.Abstract request)
	{
		clean();
		write(getText(request));
		promptInput(Context.parse(request));
	}

	private String				getText(Requests.Abstract request)
	{
		Requests.HeroSelector	heroSelectorRequest;
		String					rawTemplate;
		Template				template;

		heroSelectorRequest = (Requests.HeroSelector)request;
		rawTemplate = ResourceManager.getText("/view/console/templates/HeroSelector.txt");
		template = new Template(rawTemplate);

		List<Pockets.Hero>		heroes = heroSelectorRequest.getHeroes();
		int						numberOfHeroes = heroes.size();

		for (int i = 0; i < 4; i++)
		{
			template.replace
			(
				"HERO" + i,
				i < numberOfHeroes ? heroes.get(i).getName() : "Empty"
			);
		}

		return template.toString();
	}
}
