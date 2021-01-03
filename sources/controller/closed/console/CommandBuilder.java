package controller.closed.console;

import controller.open.Commands;

class								CommandBuilder
{
	public static Commands.Abstract	build(CommandParser commandParser)
	{
		Object						object;
		Class<?>					class_;

		assert commandParser.getPattern() != null;

		class_ = commandParser.getPattern().getClass_();
		object =
			commandParser.hasValue() ?
				buildWithValue(class_, commandParser.extractValue()) : buildWithoutValue(class_);

		assert object instanceof Commands.Abstract;
		return (Commands.Abstract)object;
	}

	public static Object			buildWithValue(Class<?> class_, String value)
	{
		try
		{
			return class_.getConstructors()[0].newInstance(value);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	public static Object			buildWithoutValue(Class<?> class_)
	{
		try
		{
			return class_.newInstance();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
}
