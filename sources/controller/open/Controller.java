package controller.open;

import application.service.Debug;
import application.patterns.SingletonMap;
import application.patterns.uniqueNotifier.UniqueListener;
import application.patterns.uniqueNotifier.UniqueNotifier;
import application.service.LogGroup;
import controller.closed.SignalTranslator;
import view.open.Signals;

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
