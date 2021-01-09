package swingy.controller.open;

import lombok.Getter;

public abstract class				Commands
{
// -------------------------------> Abstract

	public interface				Abstract {}

	private static abstract class	CommandWithString implements Abstract
	{
		@Getter
		private final String		string;

		public						CommandWithString(String string)
		{
			this.string = string;
		}
	}

// -------------------------------> Concrete : System

	public static class				Unknown implements Abstract {}

// -------------------------------> Concrete : UI, Global

	public static class				Exit implements Abstract {}

	public static class				Console implements Abstract {}

	public static class				Gui implements Abstract {}

	public static class				Enter extends CommandWithString
	{
		public						Enter(String string)
		{
			super(string);
		}
	}

	public static class				Select extends CommandWithString
	{
		public						Select(String string)
		{
			super(string);
		}
	}

	public static class				Delete extends CommandWithString
	{
		public						Delete(String string)
		{
			super(string);
		}
	}

	public static class				Info extends CommandWithString
	{
		public						Info(String string)
		{
			super(string);
		}
	}

	public static class				AnswerA implements Abstract {}

	public static class				AnswerB implements Abstract {}

	public static class				Ok implements Abstract {}

// -------------------------------> Concrete : UI, Hero selector

	public static class				Create implements Abstract {}

// -------------------------------> Concrete : UI, Map

	public static class				GoNorth implements Abstract {}
	public static class				GoEast implements Abstract {}
	public static class				GoSouth implements Abstract {}
	public static class				GoWest implements Abstract {}

	public static class				HeroStats implements Abstract {}

	public static class				HeroInventory implements Abstract {}
}
