package swingy.view.closed.ui.console;

import swingy.application.patterns.SingletonMap;
import swingy.model.open.Requests;
import swingy.view.closed.WorkerFactory;
import swingy.view.closed.ui.UiWorkerFactory;
import swingy.view.closed.ui.console.workers.*;

public class							ConsoleWorkerFactory extends UiWorkerFactory
{
	public static ConsoleWorkerFactory	getInstance()
	{
		return SingletonMap.getInstanceOf(ConsoleWorkerFactory.class);
	}

	@Override
	public ConsoleWorker				build(Requests.Abstract request)
	{
		// Common
		if (request instanceof Requests.Info)
			return new ConsoleWorkerOnInfo();
		if (request instanceof Requests.Error)
			return new ConsoleWorkerOnError();
		if (request instanceof Requests.Question)
			return new ConsoleWorkerOnQuestion();

		// Hero selector
		if (request instanceof Requests.HeroSelector)
			return new ConsoleWorkerOnHeroSelector();
		if (request instanceof Requests.HeroInfo)
			return new ConsoleWorkerOnHeroInfo();
		if (request instanceof Requests.NameEntry)
			return new ConsoleWorkerOnNameEntry();
		if (request instanceof Requests.ClassSelector)
			return new ConsoleWorkerOnClassSelector();

		// Game
		if (request instanceof Requests.Map)
			return new ConsoleWorkerOnMap();
		if (request instanceof Requests.HeroStats)
			return new ConsoleWorkerOnHeroStats();
		if (request instanceof Requests.HeroInventory)
			return new ConsoleWorkerOnHeroInventory();
		if (request instanceof Requests.Battle)
			return new ConsoleWorkerOnBattle();

		throw new WorkerFactory.UnrecognizedRequestException(request);
	}
}
