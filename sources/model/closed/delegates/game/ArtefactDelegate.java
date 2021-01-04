package model.closed.delegates.game;

import controller.open.Commands;
import model.closed.Session;
import model.closed.delegates.Delegate;
import model.closed.artefacts.artefact.Artefact;
import model.open.Requests;

public class					ArtefactDelegate extends Delegate
{
// ---------------------------> Constants

	private static final String	QUESTION_TEMPLATE = "Do you wish to take '%s' or leave it?";
	private static final String	ANSWER_LEAVE = "Leave";
	private static final String	ANSWER_TAKE = "Take";

// ---------------------------> Attributes

	private final Artefact		artefact;

// ---------------------------> Constructor

	public						ArtefactDelegate(Artefact artefact)
	{
		this.artefact = artefact;
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
			;
		else if (command instanceof Commands.AnswerB)
			Session.getHero().getInventory().setArtefact(artefact);
		else
			throw new UnrecognizedCommandException(command);

		requestResolution();
	}

// ---------------------------> Private methods

	private void				showQuestion()
	{
		sendRequest
		(
			new Requests.Question
			(
				String.format(QUESTION_TEMPLATE, artefact.getName()),
				ANSWER_LEAVE,
				ANSWER_TAKE
			)
		);
	}
}
