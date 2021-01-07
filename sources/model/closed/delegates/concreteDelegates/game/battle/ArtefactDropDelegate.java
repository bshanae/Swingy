package model.closed.delegates.concreteDelegates.game.battle;

import application.service.Exceptions;
import model.closed.Session;
import model.closed.artefacts.artefact.Artefact;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.concreteDelegates.common.QuestionDelegate;

public class					ArtefactDropDelegate extends AbstractDelegate
{
// ---------------------------> Nested types

	public static class			ResolutionObject implements AbstractResolutionObject {}

// ---------------------------> Constants

	private static final String	QUESTION = "Do you wish to take '%s'?";
	private static final String	ANSWER_A = "Leave";
	private static final String	ANSWER_B = "Take";

// ---------------------------> Attributes

	private final Enemy			opponent;
	private Artefact			droppedArtefact;

// ---------------------------> Constructor

	public						ArtefactDropDelegate(Enemy opponent)
	{
		this.opponent = opponent;
	}

// ---------------------------> Implementations

	@Override
	public void					whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
		{
			if (tryGetArtefact())
				askQuestion();
			else
				resolveLater(new ArtefactDropDelegate.ResolutionObject());
		}
	}

	@Override
	public void					whenChildResolved(AbstractResolutionObject object)
	{
		if (object instanceof QuestionDelegate.ResolutionObject)
		{
			if (((QuestionDelegate.ResolutionObject)object).getAnswer() == QuestionDelegate.ResolutionObject.Answer.B)
				Session.getInstance().getHero().getInventory().setArtefact(droppedArtefact);

			resolveLater(new ArtefactDropDelegate.ResolutionObject());
		}
		else
			throw new Exceptions.UnexpectedCodeBranch();
	}

// ---------------------------> Private methods

	private boolean				tryGetArtefact()
	{
		droppedArtefact = opponent.getArtefactDropper().drop();
		return droppedArtefact != null;
	}

	private void				askQuestion()
	{
		String					question;

		question = String.format(QUESTION, droppedArtefact.getName());
		stackChildLater(new QuestionDelegate(question, ANSWER_A, ANSWER_B));
	}
}
