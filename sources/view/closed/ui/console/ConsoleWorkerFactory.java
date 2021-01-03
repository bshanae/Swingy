package view.closed.ui.console;

import model.open.Requests;
import view.closed.ui.UiWorkerFactory;
import view.closed.ui.console.workers.*;

public class				ConsoleWorkerFactory extends UiWorkerFactory
{
	@Override
	public ConsoleWorker	build(Requests.Abstract request)
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
