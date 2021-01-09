package swingy.model.open;

import swingy.application.service.Debug;
import swingy.application.patterns.SingletonMap;
import swingy.application.patterns.UniqueListener;
import swingy.application.patterns.UniqueNotifier;
import swingy.application.service.LogGroup;
import swingy.controller.open.Commands;
import swingy.model.closed.Game;

import java.util.LinkedList;
import java.util.Queue;

public class								Model
												extends UniqueNotifier<Requests.Abstract>
												implements UniqueListener<Commands.Abstract>
{
	private final Queue<Requests.Abstract>	requestQueue;

	public static Model						getInstance()
	{
		return SingletonMap.getInstanceOf(Model.class);
	}

	public boolean							isTerminated()
	{
		return Game.getInstance().isTerminated();
	}

	public									Model()
	{
		requestQueue = new LinkedList<>();
	}

	public void								run()
	{
		Game.getInstance().run();
	}

	public void								update()
	{
		Game.getInstance().update();

		if (!requestQueue.isEmpty())
			super.notifyListener(requestQueue.poll());
	}

	public void								notifyListener(Requests.Abstract request)
	{
		Debug.logFormat(LogGroup.MVC, "[Model/Model] Queuing request of type '%s'", request.getClass());
		requestQueue.add(request);
	}

	@Override
	public void								listen(Commands.Abstract command)
	{
		Debug.logFormat(LogGroup.MVC, "[Model/Model] Received command of type '%s'", command.getClass());
		Game.getInstance().executeCommand(command);
	}
}
