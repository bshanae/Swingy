package controller.closed;

import application.patterns.SingletonMap;
import controller.closed.console.ConsoleSignalTranslator;
import controller.closed.gui.GuiSignalTranslator;
import controller.open.Commands;
import view.open.Signals;

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
			return new Commands.Null();
	}

	protected abstract Commands.Abstract	translateImplementation(Signals.Abstract signal);
}
