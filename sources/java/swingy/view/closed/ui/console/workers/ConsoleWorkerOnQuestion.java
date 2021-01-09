package swingy.view.closed.ui.console.workers;

import swingy.model.open.Requests;
import swingy.view.closed.ui.console.ConsoleWorker;
import swingy.view.closed.ui.console.utils.ConsoleTemplate;
import swingy.view.open.Context;

public class				ConsoleWorkerOnQuestion extends ConsoleWorker
{
// ---------------------------> Attributes

	private Requests.Question	request;

// ---------------------------> Implementations

	@Override
	public void					execute(Requests.Abstract request)
	{
		castRequest(request);

		clean();
		write(getText());

		promptExpectedInput
		(
			Context.parse(request),
			this.request.getAnswerA().toLowerCase(),
			this.request.getAnswerB().toLowerCase()
		);
	}

// ---------------------------> Private methods

	private void				castRequest(Requests.Abstract request)
	{
		this.request = (Requests.Question)request;
	}

	private String				getText()
	{
		ConsoleTemplate			template;

		template = new ConsoleTemplate("view/console/Template-Question.txt");
		template.put("TITLE", "Question : ", ConsoleTemplate.Style.BOLD);
		template.put("QUESTION", request.getQuestion());
		template.put("COMMANDS", "Commands : ", ConsoleTemplate.Style.BOLD);
		template.put("ANSWER_A", request.getAnswerA().toLowerCase());
		template.put("ANSWER_B", request.getAnswerB().toLowerCase());

		return template.toString();
	}
}
