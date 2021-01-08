package view.closed.ui.console;

import view.closed.ui.UiWorker;
import view.open.Context;

public abstract class		ConsoleWorker extends UiWorker
{
	protected static void	clean()
	{
		ConsoleServer.getInstance().execute(new ConsoleTasks.Clean());
	}

	protected static void	write(String text)
	{
		ConsoleServer.getInstance().execute(new ConsoleTasks.Write(text));
	}

	protected static void	promptInput(Context context)
	{
		ConsoleServer.getInstance().execute(new ConsoleTasks.PromptInput(context));
	}

	protected static void	promptExpectedInput(Context context, String commandA, String commandB)
	{
		ConsoleServer.getInstance().execute(new ConsoleTasks.PromptExpectedInput(context, commandA, commandB));
	}
}
