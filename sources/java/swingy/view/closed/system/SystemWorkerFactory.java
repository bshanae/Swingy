package swingy.view.closed.system;

import swingy.application.patterns.SingletonMap;
import swingy.model.open.Requests;
import swingy.view.closed.WorkerFactory;
import swingy.view.closed.system.workers.SystemWorkerOnSwitchToConsole;
import swingy.view.closed.system.workers.SystemWorkerOnSwitchToGui;
import swingy.view.closed.system.workers.SystemWorkerOnTermination;

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
		if (request instanceof Requests.Terminate)
			return new SystemWorkerOnTermination();

		throw new UnrecognizedRequestException(request);
	}
}
