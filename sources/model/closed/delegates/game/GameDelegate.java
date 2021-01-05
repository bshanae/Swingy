package model.closed.delegates.game;

import controller.open.Commands;
import model.closed.delegates.Delegate;
import model.closed.delegates.game.map.MapDelegate;

public class				GameDelegate extends Delegate
{
	@Override
	public void				whenActivated(boolean isFirstTime)
	{
		linkChild(new MapDelegate());
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		requestResolution();
	}

	@Override
	protected void			whenChildResolved(ResolutionMessage message)
	{
		linkChild(new MapDelegate());
	}
}
