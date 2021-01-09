package swingy.view.closed.ui.console.workers;

import swingy.model.open.Requests;
import swingy.view.closed.ui.console.ConsoleWorker;
import swingy.view.closed.ui.console.utils.ConsoleTemplate;
import swingy.view.open.Context;

public class			ConsoleWorkerOnInfo extends ConsoleWorker
{
	@Override
	public void			execute(Requests.Abstract request)
	{
		clean();
		write(getText((Requests.Info)request));
		promptInput(Context.parse(request));
	}

	private String		getText(Requests.Info request)
	{
		ConsoleTemplate	template;

		template = new ConsoleTemplate("view/console/Template-Info.txt");
		template.put("TITLE", "Info : ", ConsoleTemplate.Style.BOLD);
		template.put("MESSAGE", request.getMessage());

		return template.toString();
	}
}
