package view.closed.system;

import application.patterns.SingletonMap;
import model.open.Requests;
import view.closed.WorkerFactory;
import view.closed.system.workers.SystemWorkerOnSwitchToConsole;
import view.closed.system.workers.SystemWorkerOnSwitchToGui;

public class							SystemWorkerFactory extends WorkerFactory
{
	public static SystemWorkerFactory	getInstance()
	{
		return SingletonMap.getInstanceOf(SystemWorkerFactory.class);
	}

	@Override
	public SystemWorker					build(Requests.Abstract request)
	{
		if (request instanceof Requests.SwitchToConsole)
			return new SystemWorkerOnSwitchToConsole();
		if (request instanceof Requests.SwitchToGui)
			return new SystemWorkerOnSwitchToGui();

		throw new UnrecognizedRequestException(request);
	}
}
