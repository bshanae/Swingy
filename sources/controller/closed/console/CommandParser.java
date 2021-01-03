package controller.closed.console;

class						CommandParser
{
	private final String[]	tokens;
	private Pattern			pattern;

	public Pattern			getPattern()
	{
		return pattern;
	}

	public					CommandParser(String command)
	{
		tokens = command.split("\\s+");
	}

	public boolean			canApplyPattern(Pattern pattern)
	{
		return pattern.checkTokens(tokens);
	}

	public void				applyPattern(Pattern pattern)
	{
		assert pattern.checkTokens(tokens);
		this.pattern = pattern;
	}

	public boolean			hasValue()
	{
		Keyword[]			keywords = pattern.getKeywords();

		for (Keyword keyword : keywords)
		{
			if (keyword == Keyword.VALUE)
				return true;
		}

		return false;
	}

	public String			extractValue()
	{
		Keyword[]			keywords = pattern.getKeywords();

		for (int i = 0; i < keywords.length; i++)
		{
			if (keywords[i] == Keyword.VALUE)
				return tokens[i];
		}

		return null;
	}
}
