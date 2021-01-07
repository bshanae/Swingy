package application.utils.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class					Template
{
// ---------------------------> Exceptions

	public static class			TemplateIsNotReadyException extends RuntimeException {}

// ---------------------------> Attributes

	private final String		originalText;

	protected String			modifiedText;
	private boolean				isValid;

// ---------------------------> Constructor

	public						Template(String pathToFile)
	{
		originalText = ResourceManager.getInstance().readText(pathToFile);
		modifiedText = originalText;
	}

// ---------------------------> Public methods

	@Override
	public String				toString()
	{
		if (!isValid)
			validate();

		return modifiedText;
	}

	public void					put(String key, String value)
	{
		modifiedText = modifiedText.replaceFirst(buildKeyRegex(key), value);
	}

	public final void 			validate()
	{
		if (modifiedText.matches(buildAnyKeyRegex()))
			throw new TemplateIsNotReadyException();

		isValid = true;
	}

	public void					reset()
	{
		modifiedText = originalText;
		isValid = false;
	}

// ---------------------------> Protected methods

	protected String			buildKeyRegex(String key)
	{
		return String.format("(%%%s *%%)", key);
	}

// ---------------------------> Private methods

	private String				buildAnyKeyRegex()
	{
		return "%%*%%";
	}
}
