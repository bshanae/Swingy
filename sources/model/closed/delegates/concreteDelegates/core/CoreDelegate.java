package model.closed.delegates.concreteDelegates.core;

import application.applicationOptions.ApplicationOption;
import application.service.Exceptions;
import controller.open.Commands;
import model.closed.Game;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.abstractDelegate.commands.LostCommand;
import model.closed.delegates.concreteDelegates.common.AutoResolvableDelegate;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
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
