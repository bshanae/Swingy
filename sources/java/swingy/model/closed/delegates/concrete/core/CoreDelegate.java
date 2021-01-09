package swingy.model.closed.delegates.concrete.core;

import swingy.application.options.ApplicationOption;
import swingy.application.service.Exceptions;
import swingy.controller.open.Commands;
import swingy.model.closed.Game;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.abstract_.commands.LostCommand;
import swingy.model.closed.delegates.concrete.common.AutoResolvableDelegate;
import swingy.model.closed.delegates.concrete.common.ErrorDelegate;
import swingy.model.closed.delegates.concrete.game.GameDelegate;
import swingy.model.closed.delegates.concrete.hero_selection.HeroSelectionDelegate;
import swingy.model.open.Requests;

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
				throw new Exceptions.UnexpectedCodeBranch();
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
				state = State.WAITING;
				break;

			default:
				throw new Exceptions.UnexpectedCodeBranch();
		}
	}

	@Override
	protected void				tryToExecuteCommandSilently(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Console)
		{
			sendRequest(new Requests.SwitchToConsole());
			stackChildLater(new AutoResolvableDelegate());
		}
		else if (command.getCommand() instanceof Commands.Gui)
		{
			sendRequest(new Requests.SwitchToGui());
			stackChildLater(new AutoResolvableDelegate());
		}
		else if (command.getCommand() instanceof Commands.Exit)
			sendRequest(new Requests.Terminate());
		else if (command.getCommand() instanceof Commands.FinishTermination)
			Game.getInstance().terminate();
		else
			return;

		command.markExecuted();
	}

	@Override
	public void					tryCatchLostCommand(LostCommand command)
	{
		stackChildLater(new ErrorDelegate("Unknown command"));
		command.markCaught();
	}

// --------------------------->	Public methods

	public void					run()
	{
		this.activate();

		if (ApplicationOption.LAUNCH_CONSOLE.isDefined())
			sendRequest(new Requests.SwitchToConsole());
		else
			sendRequest(new Requests.SwitchToGui());
	}
}
