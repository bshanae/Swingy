package model.closed.managers.delegates.game;

import controller.open.Commands;
import model.closed.managers.Session;
import model.closed.managers.battle.Battle;
import model.closed.managers.battle.BattleLogger;
import model.closed.managers.delegates.Delegate;
import model.closed.managers.generators.ArtefactGenerator;
import model.closed.objects.artefacts.Artefact;
import model.closed.objects.creatures.enemies.Enemy;
import model.open.Requests;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles battle
 */
public class					BattleDelegateC extends Delegate
{
// ---------------------------> Nested types

	private class				RequestBattleTurnTask extends TimerTask
	{
		@Override
		public void				run()
		{
			timer = null;
			shouldExecuteTurn = true;
		}
	}

// ---------------------------> Constants

	private static final float	LOG_DELAY = 1.f;
	private static final float	MILLISECONDS_IN_A_SECOND = 1000;

// ---------------------------> Attributes

	private final Battle		battle;

	private Timer				timer;
	private boolean				shouldExecuteTurn;

// ---------------------------> Constructor

	public						BattleDelegateC(Enemy opponent)
	{
		battle = new model.closed.managers.battle.Battle(opponent);
		battle.setLogger(new BattleLogger());

		timer = null;
		shouldExecuteTurn = false;
	}

// ---------------------------> Protected methods

	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			showLog();
	}

	@Override
	protected void				whenUpdated()
	{
		updateTimer();
		updateBattle();
	}

	@Override
	protected void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.Ok)
		{
			if (battle.getOpponent().isDead())
			{
				if (!tryGetArtefact(battle.getOpponent()))
					requestResolution();
			}
			else
				requestResolution();
		}
	}

	@Override
	protected void				whenChildResolved(ResolutionMessage message)
	{
		requestResolution();
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

	private boolean				tryGetArtefact(Enemy enemy)
	{
		Artefact				droppedArtefact;

		droppedArtefact = ArtefactGenerator.generate(enemy.getMeta());
		if (droppedArtefact != null)
		{
			linkChild(new ArtefactDelegate(droppedArtefact));
			return true;
		}

		return false;
	}
}
