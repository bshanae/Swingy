package swingy.view.closed.ui.console.workers;

import swingy.model.open.Requests;
import swingy.view.closed.ui.console.ConsoleWorker;
import swingy.view.closed.ui.console.utils.ConsoleTemplate;
import swingy.view.open.Context;

public class			ConsoleWorkerOnNameEntry extends ConsoleWorker
{
	@Override
	public void			execute(Requests.Abstract request)
	{
		clean();
		write(getText());
		promptInput(Context.parse(request));
	}

	private String		getText()
	{
		ConsoleTemplate	template;

		template = new ConsoleTemplate("view/console/Template-HeroNameEntry.txt");
		template.put("TITLE", "Enter name : ", ConsoleTemplate.Style.BOLD);

		return template.toString();
	}
}
