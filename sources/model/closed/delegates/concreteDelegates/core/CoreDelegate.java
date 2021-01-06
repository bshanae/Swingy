package model.closed.delegates.concreteDelegates.core;

import application.ApplicationOptions;
import controller.open.Commands;
import model.closed.Game;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.game.GameDelegate;
import model.closed.delegates.concreteDelegates.heroSelection.HeroSelectionDelegate;
import model.open.Requests;

public class					CoreDelegate extends AbstractDelegate
{
// --------------------------->	Nested types

	private enum				State
	{
		WAITING,
		SELECTING_HERO,
		SELECTED_HERO,
		PLAYING_GAME,
		PLAYED_GAME,
	}

// --------------------------->	Attributes

	private State				state;

// --------------------------->	Constructor

	public						CoreDelegate()
	{
		state = State.WAITING;
	}

// --------------------------->	Implementations

	@Override
	protected void				whenUpdated()
	{
		switch (state)
		{
			case WAITING:
				state = State.SELECTING_HERO;
				stackChildLater(new HeroSelectionDelegate());
				break;

			case SELECTED_HERO:
				state = State.PLAYING_GAME;
				stackChildLater(new GameDelegate());
				break;

			case PLAYED_GAME:
				resolveLater(null);
				break;

			default:
				assert false;
		}
	}

	@Override
	protected void				whenChildResolved(AbstractResolutionObject message)
	{
		switch (state)
		{
			case SELECTING_HERO:
				state = State.SELECTED_HERO;
				break;

			case PLAYING_GAME:
				state = State.PLAYED_GAME;
				break;

			default:
				assert false;
		}
	}

	@Override
	protected void				tryToExecuteCommandSilently(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Exit)
		{
			command.markExecuted();
			Game.getInstance().terminate();
		}
	}

// --------------------------->	Public methods

	public void					run()
	{
		this.activate();

		if (ApplicationOptions.get(ApplicationOptions.LAUNCH_CONSOLE))
			sendRequest(new Requests.SwitchToConsole());
		else
			sendRequest(new Requests.SwitchToGui());
	}

	public void					terminate() {}
}
