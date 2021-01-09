package swingy.view.closed.ui.console;

import swingy.application.patterns.ServerTask;
import lombok.Getter;
import swingy.view.open.Context;

public abstract class			ConsoleTasks
{
	public interface			Abstract extends ServerTask {}

	public static class			Terminate implements Abstract {}

	public static class			Clean implements Abstract {}

	public static class			Write implements Abstract
	{
		@Getter
		private final String	text;

		public 					Write(String text)
		{
			this.text = text;
		}
	}

	public static class			PromptInput implements Abstract
	{
		@Getter
		private final Context	context;

		public 					PromptInput(Context context)
		{
			this.context = context;
		}
	}

	public static class			PromptExpectedInput extends PromptInput
	{
		@Getter
		private final String	commandA;
		@Getter
		private final String	commandB;

		public 					PromptExpectedInput(Context context, String commandA, String commandB)
		{
			super(context);

			this.commandA = commandA;
			this.commandB = commandB;
		}
	}
}
