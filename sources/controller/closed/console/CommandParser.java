package controller.closed.console;

import lombok.Getter;

class						CommandParser
{
// -----------------------> Attributes

	private final String[]	tokens;

	@Getter
	private Pattern			pattern;

// -----------------------> Constructor

	public					CommandParser(String command)
	{
		tokens = command.split("\\s+");
	}

// -----------------------> Public methods

	public boolean			canApplyPattern(Pattern pattern)
	{
		return pattern.compare(tokens);
	}

	public void				applyPattern(Pattern pattern)
	{
		this.pattern = pattern;
	}

	public boolean			hasValue()
	{
		for (Keyword keyword : pattern.getKeywords())
		{
			if (keyword.isWildcard())
				return true;
		}

		return false;
	}

	public String			extractValue()
	{
		final Keyword[]		keywords = pattern.getKeywords();

		for (int i = 0; i < keywords.length; i++)
		{
			if (keywords[i].isWildcard())
				return tokens[i];
		}

		return null;
	}
}
