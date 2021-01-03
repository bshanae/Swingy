package view.closed;

import application.patterns.SingletonMap;
import application.service.Exceptions;
import model.open.Requests;
import view.closed.system.SystemWorkerFactory;
import view.closed.ui.UiWorkerFactory;

public class					WorkerFactory
{
	public static class			UnrecognizedRequestException extends RuntimeException
	{
		public 					UnrecognizedRequestException(Requests.Abstract request)
		{
			super("Can't recognize request of type'" + request.getClass() + "'");
		}
	}

	public static WorkerFactory	getInstance()
	{
		return SingletonMap.getInstanceOf(WorkerFactory.class);
	}

	public Worker				build(Requests.Abstract request)
	{
		if (request instanceof Requests.System)
			return SystemWorkerFactory.getInstance().build(request);
		else if (request instanceof Requests.Ui)
			return UiWorkerFactory.getInstance().build(request);

		throw new Exceptions.UnexpectedCodeBranch();
	}
}
