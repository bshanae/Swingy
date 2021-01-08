package model.closed.delegates.concreteDelegates.game;

import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.concreteDelegates.game.map.MapDelegate;

public class				GameDelegate extends AbstractDelegate
{
	public static class		ResolutionObject implements AbstractResolutionObject {}

	@Override
	public void				whenActivated()
	{
		stackChildLater(new MapDelegate());
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		resolveLater(new ResolutionObject());
	}
}
