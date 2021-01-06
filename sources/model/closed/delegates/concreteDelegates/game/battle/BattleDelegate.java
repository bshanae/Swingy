package model.closed.delegates.concreteDelegates.game.battle;

import controller.open.Commands;
import model.closed.Session;
import model.closed.battle.Battle;
import model.closed.battle.BattleLogger;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.artefacts.artefact.Artefact;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.InfoDelegate;
import model.closed.delegates.concreteDelegates.common.QuestionDelegate;
import model.open.Requests;

import java.util.Timer;
import java.util.TimerTask;

public class					BattleDelegate extends AbstractDelegate
{
// ---------------------------> Nested types

	public static class			ResolutionObject implements AbstractResolutionObject {}

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
		PROCESSING_ARTEFACT_DROP,
		PROCESSING_NEW_LEVEL
	}

// ---------------------------> Constants

	private static final float	LOG_DELAY = 1.f;
	private static final float	MILLISECONDS_IN_A_SECOND = 1000;

	private static final String	ARTEFACT_QUESTION = "Do you wish to take '%s'?";
	private static final String	ARTEFACT_ANSWER_A = "Leave";
	private static final String	ARTEFACT_ANSWER_B = "Take";

	private static final String	LEVEL_INFO = "Congratulations! You've upgraded to level %d.";

// ---------------------------> Attributes

	private final Battle		battle;

	private State				state;
	private Timer				timer;
	private boolean				shouldExecuteTurn;

	private Artefact			droppedArtefact;
	private final int			levelBeforeBattle;

// ---------------------------> Constructor

	public						BattleDelegate(Enemy opponent)
	{
		battle = new Battle(opponent);
		battle.setLogger(new BattleLogger());

		state = State.PROCESSING_BATTLE;
		timer = null;
		shouldExecuteTurn = false;

		droppedArtefact = null;
		levelBeforeBattle = Session.getHero().getLevel();
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
			if (tryGetArtefact(battle.getOpponent()))
				state = State.PROCESSING_ARTEFACT_DROP;
			else if (tryTellAboutNewLevel())
				state = State.PROCESSING_NEW_LEVEL;
			else
				resolveLater(new ResolutionObject());

			command.markExecuted();
		}
	}

	@Override
	public void					whenChildResolved(AbstractResolutionObject object)
	{
		switch (state)
		{
			case PROCESSING_ARTEFACT_DROP:
				if (object instanceof QuestionDelegate.ResolutionObject)
				{
					if (((QuestionDelegate.ResolutionObject)object).getAnswer() == QuestionDelegate.ResolutionObject.Answer.B)
						Session.getHero().getInventory().setArtefact(droppedArtefact);

					if (tryTellAboutNewLevel())
						state = State.PROCESSING_NEW_LEVEL;
					else
						resolveLater(new ResolutionObject());
				}
				break;

			case PROCESSING_NEW_LEVEL:
				if (object instanceof InfoDelegate.ResolutionObject)
					resolveLater(new ResolutionObject());

				break;
		}

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
		String					question;

		droppedArtefact = enemy.getArtefactDropper().drop();
		if (droppedArtefact != null)
		{
			question = String.format(ARTEFACT_QUESTION, droppedArtefact.getName());
			stackChildLater(new QuestionDelegate(question, ARTEFACT_ANSWER_A, ARTEFACT_ANSWER_B));
			return true;
		}

		return false;
	}

	private boolean				tryTellAboutNewLevel()
	{
		final int				levelAfterBattle = Session.getHero().getLevel();

		if (levelAfterBattle > levelBeforeBattle)
		{
			stackChildLater(new InfoDelegate(String.format(LEVEL_INFO, levelAfterBattle)));
			return true;
		}

		return false;
	}

}
