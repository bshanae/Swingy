package view.closed.ui.console;

import application.ApplicationOptions;
import application.patterns.SingletonMap;
import application.patterns.server.Server;
import view.open.Signals;
import view.open.View;

import java.util.Scanner;

public class					ConsoleServer extends Server<ConsoleTasks.Abstract>
{
// ---------------------------> Public methods

	public static ConsoleServer	getInstance()
	{
		return SingletonMap.getInstanceOf(ConsoleServer.class);
	}

// ---------------------------> Private methods

	private void				execute(ConsoleTasks.Clean task)
	{
		if (ApplicationOptions.get(ApplicationOptions.IDE))
			return ;

		System.out.print("\033\143");
		System.out.flush();
	}

	private void				execute(ConsoleTasks.Write task)
	{
		System.out.print(task.text);
		System.out.flush();
	}

	private void				execute(ConsoleTasks.WriteLast task)
	{
		// TODO
	}

	private void				execute(ConsoleTasks.PromptInput task)
	{
		String					input;

		input = new Scanner(System.in).nextLine();
		View.getInstance().sendSignal(new Signals.Console(task.context, input));
	}
}
