package swingy.controller.closed;

import swingy.application.patterns.SingletonMap;
import swingy.application.service.Exceptions;
import swingy.controller.closed.console.ConsoleSignalTranslator;
import swingy.controller.closed.gui.GuiSignalTranslator;
import swingy.controller.open.Commands;
import swingy.view.open.Signals;

public abstract class						SignalTranslator
{
	public static Commands.Abstract			translate(Signals.Abstract signal)
	{
		SignalTranslator					instance;

		if (signal instanceof Signals.Console)
		{
			instance = SingletonMap.getInstanceOf(ConsoleSignalTranslator.class);
			return instance.translateImplementation(signal);
		}
		else if (signal instanceof Signals.Gui)
		{
			instance = SingletonMap.getInstanceOf(GuiSignalTranslator.class);
			return instance.translateImplementation(signal);
		}
		else
			throw new Exceptions.UnexpectedCodeBranch();
	}

	protected abstract Commands.Abstract	translateImplementation(Signals.Abstract signal);
}
