package swingy.model.closed.delegates.concrete.common;

import swingy.controller.open.Commands;
import lombok.Getter;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.open.Requests;

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
	protected void				whenActivated()
	{
		showQuestion();
	}

	@Override
	protected void				tryToExecuteCommand(ExecutableCommand command)
	{
		ResolutionObject.Answer	answer;

		command.lock();

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
