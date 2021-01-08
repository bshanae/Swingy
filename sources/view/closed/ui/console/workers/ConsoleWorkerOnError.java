package view.closed.ui.console.workers;

import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.open.Context;

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
