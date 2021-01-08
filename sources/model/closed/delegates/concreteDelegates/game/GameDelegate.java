package model.closed.delegates.concreteDelegates.game;

import model.closed.Session;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.InfoDelegate;
import model.closed.delegates.concreteDelegates.game.map.MapDelegate;

public class				GameDelegate extends AbstractDelegate
{
// -----------------------> Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// -----------------------> Attributes

	private boolean			isFirstActivation;

// -----------------------> Constructor

	public 					GameDelegate()
	{
		isFirstActivation = true;
	}

// -----------------------> Implementations

	@Override
	public void				whenActivated()
	{
		if (isFirstActivation)
		{
			showMap();
			isFirstActivation = false;
		}
	}

	@Override
	protected void			whenChildResolved(AbstractResolutionObject object)
	{
		if (object instanceof InfoDelegate.ResolutionObject)
			showMap();
		else if (object instanceof MapDelegate.ResolutionObject)
		{
			if (Session.getInstance().getHero().didFinishGame())
				resolveLater(new ResolutionObject());
			else
				showInfo();
		}
	}

// -----------------------> Private methods

	private void			showMap()
	{
		stackChildLater(new MapDelegate());
	}

	private void			showInfo()
	{
		String				message;

		message = String.format("Generating map of level %s...", Session.getInstance().getHero().getLevel());
		stackChildLater(new InfoDelegate(message));
	}
}
