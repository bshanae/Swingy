package view.closed.ui.console;

import application.patterns.SingletonMap;
import model.open.Requests;
import view.closed.ui.UiWorkerFactory;
import view.closed.ui.console.workers.*;
import view.closed.ui.gui.GuiWorkerFactory;

public class							ConsoleWorkerFactory extends UiWorkerFactory
{
	public static ConsoleWorkerFactory	getInstance()
	{
		return SingletonMap.getInstanceOf(ConsoleWorkerFactory.class);
	}

	@Override
	public ConsoleWorker				build(Requests.Abstract request)
	{
		if (request instanceof Requests.HeroSelector)
			return new ConsoleWorkerOnHeroSelector();
		if (request instanceof Requests.NameEntry)
			return new ConsoleWorkerOnNameEntry();
		if (request instanceof Requests.ClassSelector)
			return new ConsoleWorkerOnClassSelector();
		if (request instanceof Requests.Map)
			return new ConsoleWorkerOnMap();
		if (request instanceof Requests.Battle)
			return new ConsoleWorkerOnBattle();

		throw new UnrecognizedRequestException(request);
	}
}