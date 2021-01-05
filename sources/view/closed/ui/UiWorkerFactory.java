package view.closed.ui;

import application.service.Exceptions;
import view.closed.WorkerFactory;
import view.closed.ui.console.ConsoleWorkerFactory;
import view.closed.ui.gui.GuiWorkerFactory;

public abstract class				UiWorkerFactory extends WorkerFactory
{
	public static UiWorkerFactory	getInstance()
	{
		switch (UiMode.getCurrentMode())
		{
			case CONSOLE:
				return ConsoleWorkerFactory.getInstance();

			case GUI:
				return GuiWorkerFactory.getInstance();
		}

		throw new Exceptions.UnexpectedCodeBranch();
	}
}
