package swingy.controller.open;

import swingy.application.service.Debug;
import swingy.application.patterns.SingletonMap;
import swingy.application.patterns.UniqueListener;
import swingy.application.patterns.UniqueNotifier;
import swingy.application.service.LogGroup;
import swingy.controller.closed.SignalTranslator;
import swingy.view.open.Signals;

public class					Controller
									extends UniqueNotifier<Commands.Abstract>
									implements UniqueListener<Signals.Abstract>
{
	public static Controller	getInstance()
	{
		return SingletonMap.getInstanceOf(Controller.class);
	}

	@Override
	public void					listen(Signals.Abstract signal)
	{
		Commands.Abstract		command = SignalTranslator.translate(signal);

		Debug.logFormat(LogGroup.MVC, "[Controller/Controller] Received signal of type '%s'", signal.getClass());
		Debug.logFormat(LogGroup.MVC, "[Controller/Controller] Sending command of type '%s'", command.getClass());

		notifyListener(command);
	}
}
