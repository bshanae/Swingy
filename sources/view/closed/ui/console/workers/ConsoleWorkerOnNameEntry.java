package view.closed.ui.console.workers;

import model.open.Requests;
import view.closed.ui.console.utils.ResourceManager;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

public class					ConsoleWorkerOnNameEntry extends ConsoleWorker
{
	@Override
	public void					execute(Requests.Abstract request)
	{
		clean();
		write(ResourceManager.getText("/console/templates/HeroNameEntry.txt"));
		promptInput(Context.parse(request));
	}
}
