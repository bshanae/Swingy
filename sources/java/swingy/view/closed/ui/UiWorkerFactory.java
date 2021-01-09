package swingy.view.closed.ui;

import swingy.application.service.Exceptions;
import swingy.view.closed.WorkerFactory;
import swingy.view.closed.ui.console.ConsoleWorkerFactory;
import swingy.view.closed.ui.gui.GuiWorkerFactory;

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
