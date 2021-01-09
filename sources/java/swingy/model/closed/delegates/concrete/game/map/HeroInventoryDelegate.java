package swingy.model.closed.delegates.concrete.game.map;

import swingy.controller.open.Commands;
import swingy.model.closed.Session;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.open.Requests;

public class				HeroInventoryDelegate extends AbstractDelegate
{
	public static class		ResolutionObject implements AbstractResolutionObject {}

	@Override
	public void				whenActivated()
	{
		sendRequest(new Requests.HeroInventory(Session.getInstance().getHero().getInventory()));
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		command.lock();

		if (command.getCommand() instanceof Commands.Ok)
		{
			resolveLater(new ResolutionObject());
			command.markExecuted();
		}
	}
}
