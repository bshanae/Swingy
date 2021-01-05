package model.closed.delegates.game.map;

import controller.open.Commands;
import model.closed.Session;
import model.closed.delegates.Delegate;
import model.open.Requests;

public class				HeroInventoryDelegate extends Delegate
{
	@Override
	public void				whenActivated(boolean isFirstTimeActivated)
	{
		sendRequest(new Requests.HeroInventory(Session.getHero().getInventory()));
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.Ok)
			requestResolution();
		else
			throw new UnexpectedCommandException(command);
	}
}
