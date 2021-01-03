package view.closed.ui.console.utils;

public class					Template
{
	private static class		Range
	{
		public final int		begin;
		public final int		end;

		public int				getLength()
		{
			return end - begin;
		}

		public					Range(int begin, int end)
		{
			// TODO Validation

			this.begin = begin;
			this.end = end;
		}
	}

	private String				string;

	public						Template(String string)
	{
		this.string = string;
	}

	@Override
	public String				toString()
	{
		return string;
	}

	public void					replace(String key, String value)
	{
		Range					range;

		range = findKey(key);
		assert range != null;

		// TODO If value longer than field, put "..."
		replaceAt(range, fillString(' ', range.getLength()));
		replaceAt(new Range(range.begin, range.begin + value.length()), value);
	}

	private Range				findKey(String key)
	{
		int						beginIndex;
		int						endIndex;

		for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) == '%' && compareAt(i + 1, key))
			{
				beginIndex = i;
				endIndex = string.indexOf('%', i + 1) + 1;

				if (endIndex == 0)
					throw new RuntimeException();

				return new Range(beginIndex, endIndex);
			}
		}

		return null;
	}

	private boolean				compareAt(int index, String other)
	{
		int						i;

		for (i = 0; i < other.length() && i + index < string.length(); i++)
			if (string.charAt(i + index) != other.charAt(i))
				return false;

		return i == other.length();
	}

	private void				replaceAt(Range range, String newPiece)
	{
		StringBuilder			stringBuilder = new StringBuilder(string);

		stringBuilder.replace(range.begin, range.end, newPiece);
		string = stringBuilder.toString();
	}

	private static String		fillString(char character, int length)
	{
		return new String(new char[length]).replace('\0', character);
	}
}
