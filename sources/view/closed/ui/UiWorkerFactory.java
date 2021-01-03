package view.closed.ui;

import application.patterns.SingletonMap;
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
				return SingletonMap.getInstanceOf(ConsoleWorkerFactory.class);

			case GUI:
				return SingletonMap.getInstanceOf(GuiWorkerFactory.class);
		}

		throw new Exceptions.UnexpectedCodeBranch();
	}
}
