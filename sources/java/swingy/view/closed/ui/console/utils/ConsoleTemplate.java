package swingy.view.closed.ui.console.utils;

import swingy.application.utils.resources.Template;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class					ConsoleTemplate extends Template
{
// ---------------------------> Nested types

	public enum					Style
	{
		NORMAL("\033[0m"),
		BOLD("\033[1m"),
		ITALIC("\033[3m");

		private final String	code;

		private					Style(String code)
		{
			this.code = code;
		}
	}

// ---------------------------> Constructor

	public						ConsoleTemplate(String pathToFile)
	{
		super(pathToFile);
	}

// ---------------------------> Public methods

	@Override
	public void 				put(String key, String value)
	{
		putPreserveLength(key, value, "", "");
	}

	public void 				put(String key, String value, Style style)
	{
		putPreserveLength(key, value, style.code, Style.NORMAL.code);
	}

// ---------------------------> Private methods

	private void				putPreserveLength(String key, String value, String prefix, String suffix)
	{
		Pattern					pattern;
		Matcher					matcher;

		String					matchedKey;
		String					transformedValue;

		pattern = Pattern.compile(buildKeyRegex(key));
		matcher = pattern.matcher(modifiedText);

		while (matcher.find())
		{
			matchedKey = matcher.group(0);
			transformedValue = addSpaces(value, matchedKey.length() - value.length());

			transformedValue = prefix + transformedValue + suffix;

			modifiedText = matcher.replaceFirst(transformedValue);
		}
	}

	private String				addSpaces(String source, int numberOfSpaces)
	{
		final int				totalLength = source.length() + numberOfSpaces;

		return String.format("%-" + totalLength + "s", source);
	}
}
