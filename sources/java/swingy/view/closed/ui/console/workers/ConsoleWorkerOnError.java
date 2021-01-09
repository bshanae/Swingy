package swingy.view.closed.ui.console.workers;

import swingy.model.open.Requests;
import swingy.view.closed.ui.console.ConsoleWorker;
import swingy.view.closed.ui.console.utils.ConsoleTemplate;
import swingy.view.open.Context;

public class			ConsoleWorkerOnError extends ConsoleWorker
{
	@Override
	public void			execute(Requests.Abstract request)
	{
		clean();
		write(getText((Requests.Error)request));
		promptInput(Context.parse(request));
	}

	private String		getText(Requests.Error request)
	{
		ConsoleTemplate	template;

		template = new ConsoleTemplate("view/console/Template-Error.txt");
		template.put("TITLE", "Error : ", ConsoleTemplate.Style.BOLD);
		template.put("MESSAGE", request.getMessage());

		return template.toString();
	}
}
