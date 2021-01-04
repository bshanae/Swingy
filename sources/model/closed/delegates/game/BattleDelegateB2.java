package model.closed.delegates.game;

import controller.open.Commands;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.Delegate;
import model.open.Requests;

/**
 * Shows player, that hero couldn't run away and starts battle after receiving 'Ok'
 */
public class					BattleDelegateB2 extends Delegate
{
// ---------------------------> Attributes

	private final Enemy			opponent;

// ---------------------------> Constructor

	public						BattleDelegateB2(Enemy opponent)
	{
		this.opponent = opponent;
	}

// ---------------------------> Protected methods

	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			sendRequest(new Requests.Info("You couldn't run away. You will fight your enemy."));
	}

	@Override
	protected void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.Ok)
			linkChild(new BattleDelegateC(opponent));
		else
			throw new UnrecognizedCommandException(command);
	}

	@Override
	protected void			whenChildResolved(ResolutionMessage message)
	{
		requestResolution(message);
	}
}
