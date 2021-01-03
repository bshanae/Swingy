package controller.closed.console;

class							Pattern
{
	private final Class<?>		class_;
	private final Keyword[]		keywords;

	public Class<?>				getClass_()
	{
		return class_;
	}

	public Keyword[]			getKeywords()
	{
		return keywords;
	}

	public boolean				isWildcard()
	{
		return keywords.length == 1 && keywords[0].isWildcard();
	}

	public 						Pattern(Class<?> class_, Keyword... keywords)
	{
		this.class_ = class_;
		this.keywords = keywords;
	}

	public boolean				checkTokens(String[] tokens)
	{
		if (keywords.length == 1 && keywords[0] == Keyword.ANY)
			return true;

		if (keywords.length != tokens.length)
			return false;

		for (int i = 0; i < keywords.length; i++)
		{
			if (!keywords[i].checkToken(tokens[i]))
				return false;
		}

		return true;
	}
}