package model.closed.managers.delegates.game;

import controller.open.Commands;
import model.closed.managers.delegates.Delegate;
import model.closed.managers.generators.RandomGenerator;
import model.closed.objects.creatures.enemies.Enemy;
import model.open.Requests;

/**
 * Asks question, if player wants to start battle or run away
 */
public class BattleDelegateA extends Delegate
{
// ---------------------------> Constants

	private static final String	QUESTION_TEMPLATE = "Do you wish to fight '%s' or try escape?";
	private static final String	ANSWER_ESCAPE = "Try to escape";
	private static final String	ANSWER_FIGHT = "Fight";

// ---------------------------> Attributes

	private final Enemy			opponent;

// ---------------------------> Constructor

	public BattleDelegateA(Enemy opponent)
	{
		this.opponent = opponent;
	}

// ---------------------------> Protected methods

	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			showQuestion();
	}

	@Override
	protected void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.AnswerA)
			tryEscape();
		else if (command instanceof Commands.AnswerB)
			fight();
		else
			throw new UnrecognizedCommandException(command);
	}

	@Override
	protected void				whenChildResolved(ResolutionMessage message)
	{
		requestResolution();
	}

// ---------------------------> Private methods

	private void				showQuestion()
	{
		sendRequest
		(
			new Requests.Question
			(
				String.format(QUESTION_TEMPLATE, opponent.getNameWithLevel()),
				ANSWER_ESCAPE,
				ANSWER_FIGHT
			)
		);
	}

	private void				tryEscape()
	{
		if (RandomGenerator.randomWithProbability(0.5f))
			linkChild(new BattleDelegateB1());
		else
			linkChild(new BattleDelegateB2(opponent));
	}

	private void				fight()
	{
		linkChild(new BattleDelegateC(opponent));
	}
}
