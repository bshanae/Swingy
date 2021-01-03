package view.open;

import application.service.Debug;
import application.patterns.SingletonMap;
import application.patterns.uniqueNotifier.UniqueListener;
import application.patterns.uniqueNotifier.UniqueNotifier;
import application.service.LogGroup;
import model.open.Requests;
import view.closed.Worker;
import view.closed.WorkerFactory;

public class					View
								extends UniqueNotifier<Signals.Abstract>
								implements UniqueListener<Requests.Abstract>
{
	public static View			getInstance()
	{
		return SingletonMap.getInstanceOf(View.class);
	}

	@Override
	public void					listen(Requests.Abstract request)
	{
		Worker					worker;

		Debug.logFormat(LogGroup.MVC, "[View/View] Received request of type '%s'", request.getClass());

		worker = WorkerFactory.getInstance().build(request);
		worker.execute(request);
	}

	public void 				sendSignal(Signals.Abstract signal)
	{
		Debug.logFormat(LogGroup.MVC, "[View/View] Sending signal of type '%s'", signal.getClass());
		notifyListener(signal);
	}
}
