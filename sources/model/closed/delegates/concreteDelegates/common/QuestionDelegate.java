package model.closed.delegates.concreteDelegates.common;

import controller.open.Commands;
import lombok.Getter;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.open.Requests;

public class					QuestionDelegate extends AbstractDelegate
{
// ---------------------------> Resolution object

	public static class			ResolutionObject implements AbstractResolutionObject
	{
		public enum				Answer
		{
			A,
			B
		}

		@Getter
		private final Answer	answer;

		public 					ResolutionObject(Answer answer)
		{
			this.answer = answer;
		}
	}

// ---------------------------> Attributes

	private final String		question;
	private final String		answerA;
	private final String		answerB;

// ---------------------------> Constructor

	public						QuestionDelegate(String question, String answerA, String answerB)
	{
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
	}

// ---------------------------> Implementations

	@Override
	protected void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			showQuestion();
	}

	@Override
	protected void				tryToExecuteCommand(ExecutableCommand command)
	{
		ResolutionObject.Answer	answer;

		if (command.getCommand() instanceof Commands.AnswerA)
			answer = ResolutionObject.Answer.A;
		else if (command.getCommand() instanceof Commands.AnswerB)
			answer = ResolutionObject.Answer.B;
		else
			return;

		command.markExecuted();
		resolveLater(new ResolutionObject(answer));
	}

// ---------------------------> Private methods

	private void				showQuestion()
	{
		sendRequest(new Requests.Question(question, answerA, answerB));
	}
}
