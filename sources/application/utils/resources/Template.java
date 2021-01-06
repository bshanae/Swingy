package application.utils.resources;

import java.io.File;

public class					Template
{
// ---------------------------> Exceptions

	public static class			TemplateIsNotReadyException extends RuntimeException {}

// ---------------------------> Attributes

	private String				originalText;
	private String				modifiedText;

	private boolean				isValid;

// ---------------------------> Constructor

	public						Template(String source)
	{
		originalText = source;
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

	public void 				validate()
	{
		if (modifiedText.matches(buildAnyKeyRegex()))
			throw new TemplateIsNotReadyException();

		isValid = true;
	}

	public void 				reset()
	{
		modifiedText = originalText;
		isValid = false;
	}

// ---------------------------> Private methods

	private String				buildKeyRegex(String key)
	{
		return String.format("%%%s *%%", key);
	}

	private String				buildAnyKeyRegex()
	{
		return "%%*%%";
	}

}
