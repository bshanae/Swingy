package view.open;

import lombok.Getter;

public abstract class					Signals
{
// -----------------------------------> Abstract

	public interface					Abstract {}

// -----------------------------------> System

	public static class					FinishedTermination implements Abstract {}

// -----------------------------------> Console

	public static class					Console implements Abstract
	{
	// ------------------------------->	Nested types

		public static class				ExpectedCommands
		{
			@Getter
			private final String		commandA;

			@Getter
			private final String		commandB;

			public						ExpectedCommands(String commandA, String commandB)
			{
				this.commandA = commandA;
				this.commandB = commandB;
			}
		}

	// ------------------------------->	Attributes

		@Getter
		private final Context			context;

		@Getter
		private final String			input;

		@Getter
		private final ExpectedCommands	expectedCommands;

	// ------------------------------->	Constructors

		public							Console(Context context, String input)
		{
			this.context = context;
			this.input = input;
			this.expectedCommands = null;
		}

		public							Console(Context context, String input, ExpectedCommands expectedCommands)
		{
			this.context = context;
			this.input = input;
			this.expectedCommands = expectedCommands;
		}
	}

// -----------------------------------> GUI

	public static class					Gui implements Abstract
	{
		@Getter
		private final ButtonId			buttonId;

		@Getter
		private final String			string;

		public 							Gui(ButtonId buttonId)
		{
			this.buttonId = buttonId;
			this.string = null;
		}

		public 							Gui(ButtonId buttonId, String string)
		{
			this.buttonId = buttonId;
			this.string = string;
		}
	}
}
