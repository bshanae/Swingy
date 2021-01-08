package controller.closed.console;

import lombok.Getter;

class							Pattern
{
// ---------------------------> Attributes

	@Getter
	private final Class<?>		class_;

	private final Keyword[]		keywords;

	@Getter
	private final boolean		isWildcard;

// ---------------------------> Constructor

	public static Pattern		create(Class<?> class_, Keyword... keywords)
	{
		return new Pattern(class_, false, keywords);
	}

	public static Pattern		createWildcard(Class<?> class_)
	{
		return new Pattern(class_, true);
	}

	private						Pattern(Class<?> class_, boolean isWildcard, Keyword... keywords)
	{
		this.class_ = class_;
		this.isWildcard = isWildcard;
		this.keywords = keywords;
	}

// ---------------------------> Properties

	public Keyword[]			getKeywords()
	{
		return keywords;
	}

// ---------------------------> Public methods

	public boolean				compare(String[] tokens)
	{
		return isWildcard || compareWithKeywords(tokens);
	}

// ---------------------------> Public methods

	private boolean				compareWithKeywords(String[] tokens)
	{
		if (keywords.length != tokens.length)
			return false;

		for (int i = 0; i < keywords.length; i++)
		{
			if (!keywords[i].compare(tokens[i]))
				return false;
		}

		return true;
	}
}