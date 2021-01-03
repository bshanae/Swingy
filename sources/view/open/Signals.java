package view.open;

public abstract class					Signals
{
	public interface					Abstract {}

	public static class					Null implements Abstract {}

// -----------------------------------> Console

	public static class					Console implements Abstract
	{
		public final Context			context;
		public final String				input;

		public							Console(Context context, String input)
		{
			this.context = context;
			this.input = input;
		}
	}

// -----------------------------------> GUI

	public static class					Gui implements Abstract
	{
		public final ButtonId			buttonId;
		public final Object				linkedData;

		public 							Gui(ButtonId buttonId)
		{
			this.buttonId = buttonId;
			this.linkedData = null;
		}

		public 							Gui(ButtonId buttonId, Object linkedData)
		{
			this.buttonId = buttonId;
			this.linkedData = linkedData;
		}
	}
}
