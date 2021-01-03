package controller.open;

public abstract class				Commands
{
// -------------------------------> Abstract

	public interface				Abstract {}

	public static class				Null implements Abstract {}

	private static abstract class	CommandWithValue implements Abstract
	{
		private final Object		value;

		private boolean				isCached;
		private Integer				valueAsInteger;
		private String				valueAsString;

		public Integer				getValueAsInteger()
		{
			if (!isCached)
				cache();

			assert valueAsInteger != null;
			return valueAsInteger;
		}

		public String				getValueAsString()
		{
			if (!isCached)
				cache();

			assert valueAsString != null;
			return valueAsString;
		}

		public						CommandWithValue(Object value)
		{
			this.value = value;
		}

		private void				cache()
		{
			valueAsInteger = value instanceof Integer ? (Integer)value : null;
			valueAsString = value instanceof String ? (String)value : null;

			isCached = true;
		}
	}

// -------------------------------> Concrete

	public static class				Exit implements Abstract {}

	public static class				Create implements Abstract {}

	public static class				GoNorth implements Abstract {}
	public static class				GoEast implements Abstract {}
	public static class				GoSouth implements Abstract {}
	public static class				GoWest implements Abstract {}

	public static class				Enter extends CommandWithValue
	{
		public						Enter(String value)
		{
			super(value);
		}
	}

	public static class				Select extends CommandWithValue
	{
		public						Select(Object value)
		{
			super(value);
		}
	}

	public static class				Delete extends CommandWithValue
	{
		public						Delete(Object value)
		{
			super(value);
		}
	}

	public static class				Info extends CommandWithValue
	{
		public						Info(Object value)
		{
			super(value);
		}
	}

	public static class				AnswerA implements Abstract {}

	public static class				AnswerB implements Abstract {}

	public static class				Ok implements Abstract {}
}
