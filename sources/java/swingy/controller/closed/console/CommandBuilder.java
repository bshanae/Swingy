package swingy.controller.closed.console;

import swingy.controller.open.Commands;

class						CommandBuilder
{
// -----------------------> Exceptions

	public static class 	CantInstantiateCommandException extends RuntimeException {}

// -----------------------> Public methods

	public static
	Commands.Abstract		build(CommandParser commandParser)
	{
		final Class<?>		patternClass = commandParser.getPattern().getClass_();

		if (commandParser.hasValue())
			return buildWithValue(patternClass, commandParser.extractValue());
		else
			return buildWithoutValue(patternClass);
	}

// -----------------------> Private methods

	private static
	Commands.Abstract		buildWithValue(Class<?> class_, String value)
	{
		try
		{
			return (Commands.Abstract)class_.getConstructors()[0].newInstance(value);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	private static
	Commands.Abstract		buildWithoutValue(Class<?> class_)
	{
		try
		{
			return (Commands.Abstract)class_.newInstance();
		}
		catch (Exception exception)
		{
			throw new CantInstantiateCommandException();
		}
	}
}
