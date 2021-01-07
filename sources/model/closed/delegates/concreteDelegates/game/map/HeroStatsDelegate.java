package model.closed.delegates.concreteDelegates.game.map;

import controller.open.Commands;
import model.closed.Session;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.open.Requests;

public class				HeroStatsDelegate extends AbstractDelegate
{
	public static class		ResolutionObject implements AbstractResolutionObject {}

	@Override
	public void				whenActivated(boolean isFirstTime)
	{
		sendRequest(new Requests.HeroStats(Session.getInstance().getHero()));
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Ok)
		{
			resolveLater(new ResolutionObject());
			command.markExecuted();
		}
	}
}
