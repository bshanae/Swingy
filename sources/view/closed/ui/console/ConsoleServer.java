package view.closed.ui.console;

import application.applicationOptions.ApplicationOption;
import application.patterns.SingletonMap;
import application.patterns.server.Server;
import view.closed.utils.UiTerminator;
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

	private void				execute(ConsoleTasks.Terminate task)
	{
		if (!ApplicationOption.IDE.isDefined())
		{
			System.out.print("\033\143");
			System.out.flush();
		}

		UiTerminator.getInstance().markConsoleTerminated();
	}

	private void				execute(ConsoleTasks.Clean task)
	{
		if (ApplicationOption.IDE.isDefined())
			return ;

		System.out.print("\033\143");
		System.out.flush();
	}

	private void				execute(ConsoleTasks.Write task)
	{
		System.out.print(task.getText());
		System.out.flush();
	}

	private void				execute(ConsoleTasks.PromptInput task)
	{
		String					input;

		input = new Scanner(System.in).nextLine();
		View.getInstance().sendSignal(new Signals.Console(task.getContext(), input));
	}

	private void				execute(ConsoleTasks.PromptExpectedInput task)
	{
		String					input;
		Signals.Console			signal;

		input = new Scanner(System.in).nextLine();
		signal = new Signals.Console
		(
			task.getContext(),
			input,
			new Signals.Console.ExpectedCommands(task.getCommandA(), task.getCommandB())
		);

		View.getInstance().sendSignal(signal);
	}
}
