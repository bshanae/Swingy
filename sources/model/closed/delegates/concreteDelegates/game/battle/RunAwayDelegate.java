package model.closed.delegates.concreteDelegates.game.battle;

import application.applicationOptions.ApplicationOption;
import application.service.Exceptions;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.InfoDelegate;
import model.closed.delegates.concreteDelegates.common.QuestionDelegate;
import model.closed.utils.RandomGenerator;

public class					RunAwayDelegate extends AbstractDelegate
{
// ---------------------------> Nested types

	public static class			ResolutionObject implements AbstractResolutionObject
	{
		private final boolean	shouldStartBattle;

		public 					ResolutionObject(boolean shouldStartBattle)
		{
			this.shouldStartBattle = shouldStartBattle;
		}

		public boolean			shouldStartBattle()
		{
			return shouldStartBattle;
		}
	}

	private enum				State
	{
		WAITING_TO_ASK_QUESTION,
		ASKED_QUESTION,
		SHOWED_RESULT
	}

// ---------------------------> Constants

	private static final String	QUESTION = "Do you wish to fight '%s' or try escape?";
	private static final String	ANSWER_ESCAPE = "Escape";
	private static final String	ANSWER_FIGHT = "Fight";

	private static final String	RESULT_DID_ESCAPE = "You ran away from enemy!";
	private static final String	RESULT_DID_NOT_ESCAPE = "You couldn't run away. You will fight your enemy.";

// ---------------------------> Attributes

	private final Enemy			opponent;

	private State				state;
	private Boolean				didEscape;

// ---------------------------> Constructor

	public 						RunAwayDelegate(Enemy opponent)
	{
		this.opponent = opponent;
		this.state = State.WAITING_TO_ASK_QUESTION;
	}

// ---------------------------> Implementations

	@Override
	public void					whenActivated()
	{
		if (state == State.WAITING_TO_ASK_QUESTION)
			askQuestion();
	}

	@Override
	public void					whenChildResolved(AbstractResolutionObject object)
	{
		switch (state)
		{
			case ASKED_QUESTION:
				tryParseAnswer(object);
				break;

			case SHOWED_RESULT:
				resolveLater(new ResolutionObject(!didEscape));
				break;
		}
	}

	@Override
	public void					tryToExecuteCommand(ExecutableCommand command)
	{
		command.lock();
	}

// ---------------------------> Private methods

	private void				askQuestion()
	{
		String					question;

		question = String.format(QUESTION, opponent.getNameWithLevel());
		stackChildLater(new QuestionDelegate(question, ANSWER_ESCAPE, ANSWER_FIGHT));

		state = State.ASKED_QUESTION;
	}

	private void				tryParseAnswer(AbstractResolutionObject object)
	{
		if (object instanceof QuestionDelegate.ResolutionObject)
		{
			switch (((QuestionDelegate.ResolutionObject)object).getAnswer())
			{
				case A:
					tryEscape();
					break;

				case B:
					resolveLater(new ResolutionObject(true));
					break;
			}
		}
		else
			throw new Exceptions.UnexpectedCodeBranch();
	}

	private void 				tryEscape()
	{
		didEscape = RandomGenerator.randomWithProbability(0.5f);

		if (ApplicationOption.TEST_ALWAYS_ESCAPE.isDefined())
			didEscape = true;

		stackChildLater(new InfoDelegate(didEscape ? RESULT_DID_ESCAPE : RESULT_DID_NOT_ESCAPE));
		state = State.SHOWED_RESULT;
	}
}
