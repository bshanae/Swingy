package swingy.model.closed.delegates.concrete.game.battle;

import swingy.application.service.Exceptions;
import swingy.model.closed.Session;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.creatures.enemy.Enemy;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.concrete.common.QuestionDelegate;

public class					ArtefactDropDelegate extends AbstractDelegate
{
// ---------------------------> Nested types

	public static class			ResolutionObject implements AbstractResolutionObject {}

// ---------------------------> Constants

	private static final String	QUESTION = "Do you wish to take '%s'?";
	private static final String	ANSWER_A = "Leave";
	private static final String	ANSWER_B = "Take";

// ---------------------------> Attributes

	private final Artefact		artefact;

// ---------------------------> Constructor

	public						ArtefactDropDelegate(Enemy opponent)
	{
		Artefact				temporary;

		temporary = opponent.getArtefactDropper().drop();
		if (Session.getInstance().getHero().getInventory().has(temporary))
			temporary = null;

		this.artefact = temporary;
	}

// ---------------------------> Implementations

	@Override
	public void					whenActivated()
	{
		if (waitingToResolve())
			return;

		if (artefact != null)
			askQuestion();
		else
			resolveLater(new ArtefactDropDelegate.ResolutionObject());
	}

	@Override
	public void					whenChildResolved(AbstractResolutionObject object)
	{
		if (object instanceof QuestionDelegate.ResolutionObject)
		{
			if (((QuestionDelegate.ResolutionObject)object).getAnswer() == QuestionDelegate.ResolutionObject.Answer.B)
				Session.getInstance().getHero().getInventory().setArtefact(artefact);

			resolveLater(new ArtefactDropDelegate.ResolutionObject());
		}
		else
			throw new Exceptions.UnexpectedCodeBranch();
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

		question = String.format(QUESTION, artefact.getName());
		stackChildLater(new QuestionDelegate(question, ANSWER_A, ANSWER_B));
	}
}
