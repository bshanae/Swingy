package model.closed.delegates.concreteDelegates.game.battle;

import application.service.Exceptions;
import controller.open.Commands;
import model.closed.Session;
import model.closed.battle.Battle;
import model.closed.battle.BattleLogger;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.InfoDelegate;
import model.open.Requests;

import java.util.Timer;
import java.util.TimerTask;

public class					BattleDelegate extends AbstractDelegate
{
// ---------------------------> Nested types

	public static class			ResolutionObject implements AbstractResolutionObject
	{
		private final boolean	didHeroWon;

		public					ResolutionObject(boolean didHeroWon)
		{
			this.didHeroWon = didHeroWon;
		}

		public boolean			didHeroWon()
		{
			return didHeroWon;
		}
	}

	private class				RequestBattleTurnTask extends TimerTask
	{
		@Override
		public void				run()
		{
			timer = null;
			shouldExecuteTurn = true;
		}
	}

	private enum				State
	{
		PROCESSING_BATTLE,
		SHOWING_THAT_HERO_LOST,
		DROPPING_ARTEFACT,
		SHOWING_NEW_LEVEL
	}

// ---------------------------> Constants

	private static final float	LOG_DELAY = 1.f;
	private static final float	MILLISECONDS_IN_A_SECOND = 1000;

	private static final String	LEVEL_INFO = "Congratulations! You've upgraded to level %d.";
	private static final String	LOST_INFO = "You lost battle... All your progress on this map is lost.";

// ---------------------------> Attributes

	private final Battle		battle;

	private State				state;
	private Timer				timer;
	private boolean				shouldExecuteTurn;

	private final int			levelBeforeBattle;

// ---------------------------> Constructor

	public						BattleDelegate(Enemy opponent)
	{
		battle = new Battle(opponent);
		battle.setLogger(new BattleLogger());

		state = State.PROCESSING_BATTLE;
		timer = null;
		shouldExecuteTurn = false;

		levelBeforeBattle = Session.getInstance().getHero().getLevel();
	}

// ---------------------------> Implementations

	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			showLog();
	}

	@Override
	protected void				whenUpdated()
	{
		if (state == State.PROCESSING_BATTLE)
		{
			updateTimer();
			updateBattle();
		}
	}

	@Override
	protected void				tryToExecuteCommand(ExecutableCommand command)
	{
		if (state != State.PROCESSING_BATTLE)
			return ;

		if (command.getCommand() instanceof Commands.Ok)
		{
			if (battle.getHero().isDead())
				tellThatHeroLost();
			else
				tryDropArtefact();

			command.markExecuted();
		}
	}

	@Override
	public void					whenChildResolved(AbstractResolutionObject object)
	{
		switch (state)
		{
			case SHOWING_THAT_HERO_LOST:
				if (object instanceof InfoDelegate.ResolutionObject)
				{
					resolveLater(new ResolutionObject(false));
					return;
				}
				break;

			case DROPPING_ARTEFACT:
				if (object instanceof ArtefactDropDelegate.ResolutionObject)
				{
					if (didHeroGetNewLevel())
						tellAboutNewLevel();
					else
						resolveLater(new ResolutionObject(true));
					return;
				}
				break;

			case SHOWING_NEW_LEVEL:
				if (object instanceof InfoDelegate.ResolutionObject)
				{
					resolveLater(new ResolutionObject(true));
					return;
				}
				break;
		}

		throw new Exceptions.UnexpectedCodeBranch();
	}

// ---------------------------> Private methods

	private void				showLog()
	{
		sendRequest(new Requests.Battle(battle));
	}

	private void				updateTimer()
	{
		if (timer == null)
		{
			timer = new Timer();
			timer.schedule(new RequestBattleTurnTask(), (int)(LOG_DELAY * MILLISECONDS_IN_A_SECOND));
		}
	}

	private void				updateBattle()
	{
		if (shouldExecuteTurn)
		{
			if (!battle.isFinished())
			{
				battle.executeTurn();
				showLog();
			}

			shouldExecuteTurn = false;
		}
	}

	private void				tellThatHeroLost()
	{
		stackChildLater(new InfoDelegate(LOST_INFO));
		Session.getInstance().applyHeroBackup();
		state = State.SHOWING_THAT_HERO_LOST;
	}

	private void				tryDropArtefact()
	{
		stackChildLater(new ArtefactDropDelegate(battle.getOpponent()));
		state = State.DROPPING_ARTEFACT;
	}

	private boolean				didHeroGetNewLevel()
	{
		return Session.getInstance().getHero().getLevel() > levelBeforeBattle;
	}

	private void				tellAboutNewLevel()
	{
		stackChildLater(new InfoDelegate(String.format(LEVEL_INFO, Session.getInstance().getHero().getLevel())));
		state = State.SHOWING_NEW_LEVEL;
	}
}
