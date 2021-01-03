package model.closed.managers.delegates.game;

import controller.open.Commands;
import model.closed.managers.delegates.Delegate;
import model.open.Requests;

/**
 * Shows player, that hero ran away from enemy
 */
public class					BattleDelegateB1 extends Delegate
{
	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			sendRequest(new Requests.Info("You run away from enemy!"));
	}

	@Override
	protected void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.Ok)
			requestResolution();
		else
			throw new UnrecognizedCommandException(command);
	}

	@Override
	protected void			whenChildResolved(ResolutionMessage message)
	{
		requestResolution(message);
	}
}
