package view.closed.ui.console.workers;

import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.open.Context;

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
