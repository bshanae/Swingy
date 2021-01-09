package swingy.view.open;

import swingy.application.service.Debug;
import swingy.application.patterns.SingletonMap;
import swingy.application.patterns.UniqueListener;
import swingy.application.patterns.UniqueNotifier;
import swingy.application.service.LogGroup;
import swingy.model.open.Requests;
import swingy.view.closed.Worker;
import swingy.view.closed.WorkerFactory;

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
