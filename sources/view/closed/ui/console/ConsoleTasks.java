package view.closed.ui.console;

import application.patterns.server.ServerTask;
import view.open.Context;

public abstract class			ConsoleTasks
{
	public interface			Abstract extends ServerTask {}

	public static class			Clean implements Abstract {}

	public static class			Write implements Abstract
	{
		public final String		text;

		public 					Write(String text)
		{
			this.text = text;
		}
	}

	public static class			WriteLast implements Abstract {}

	public static class			PromptInput implements Abstract
	{
		public final Context	context;

		public 					PromptInput(Context context)
		{
			this.context = context;
		}
	}
}
