package view.closed.ui.console.workers;

import model.open.Requests;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

public class				ConsoleWorkerOnBattle extends ConsoleWorker
{
	private static String	writtenText;

	private Requests.Battle	request;

	@Override
	public void				execute(Requests.Abstract request)
	{
		parseRequest(request);

		if (writtenText == null)
			clean();

		write(getText());

		if (this.request.isBattleFinished())
		{
			write("Press enter to continue...");
			promptInput(Context.parse(request));
		}
	}

	private void			parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Battle)request;
	}

	private String			getText()
	{
		StringBuilder		stringBuilder;
		String				logText;
		String				newText;

		stringBuilder = new StringBuilder();
		for (String line : request.getLog().lines)
			stringBuilder.append(line).append("\n");

		logText = stringBuilder.toString();

		if (writtenText != null)
			newText = logText.replace(writtenText, "");
		else
			newText = logText;

		writtenText = logText;

		return newText;
	}

}
