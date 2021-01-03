package view.closed.ui.console.workers;

import model.open.Requests;
import view.closed.ui.console.utils.ResourceManager;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

public class		ConsoleWorkerOnClassSelector extends ConsoleWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		clean();
		write(getText(request));
		promptInput(Context.parse(request));
	}

	private String	getText(Requests.Abstract request)
	{
		return ResourceManager.getText("/console/templates/ClassSelector.txt");
	}
}
