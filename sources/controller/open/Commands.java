package controller.open;

import lombok.Getter;

public abstract class				Commands
{
// -------------------------------> Abstract

	public interface				Abstract {}

	public static class				Null implements Abstract {}

	private static abstract class	CommandWithString implements Abstract
	{
		@Getter
		private final String		string;

		public						CommandWithString(String string)
		{
			this.string = string;
		}
	}

// -------------------------------> Concrete

	public static class				Exit implements Abstract {}

	public static class				Create implements Abstract {}

	public static class				GoNorth implements Abstract {}
	public static class				GoEast implements Abstract {}
	public static class				GoSouth implements Abstract {}
	public static class				GoWest implements Abstract {}

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

	public static class				Stats implements Abstract {}

	public static class				Inventory implements Abstract {}

	public static class				AnswerA implements Abstract {}

	public static class				AnswerB implements Abstract {}

	public static class				Ok implements Abstract {}
}
