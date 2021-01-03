package model.closed.managers.delegates.core;

import application.ApplicationOptions;
import model.closed.managers.Session;
import model.closed.objects.creatures.hero.Hero;
import model.closed.managers.heroStorage.HeroStorage;
import model.closed.managers.delegates.Delegate;
import model.closed.managers.delegates.game.GameDelegate;
import model.closed.managers.delegates.heroSelection.HeroSelectionDelegate;
import model.open.Requests;

public class					CoreDelegate extends Delegate
{
	private enum				State
	{
		WAITING,
		SELECTING_HERO,
		SELECTED_HERO,
		PLAYING_GAME,
		PLAYED_GAME,
	}

	private State				state;

	public void					run()
	{
		this.activate();

		if (ApplicationOptions.get(ApplicationOptions.LAUNCH_CONSOLE))
			sendRequest(new Requests.SwitchToConsole());
		else
			sendRequest(new Requests.SwitchToGui());
	}

	public void					terminate()
	{
		this.deactivate();
	}

	public						CoreDelegate()
	{
		state = State.WAITING;
	}

	@Override
	protected void				whenUpdated()
	{
		switch (state)
		{
			case WAITING:
				state = State.SELECTING_HERO;
				linkChild(new HeroSelectionDelegate());
				break;

			case SELECTED_HERO:
				state = State.PLAYING_GAME;
				linkChild(new GameDelegate());
				break;

			case PLAYED_GAME:
				requestResolution();
				break;

			default:
				assert false;
		}
	}

	@Override
	protected void				whenChildResolved(ResolutionMessage message)
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
}
