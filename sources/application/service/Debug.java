package application.service;

import application.ApplicationOptions;

public abstract class		Debug
{
	public static void		throwException(String message)
	{
		if (ApplicationOptions.get(ApplicationOptions.DEBUG))
			throw new RuntimeException(message);
	}

	public static void		log(LogGroup group, String message)
	{
		if
		(
			ApplicationOptions.get(ApplicationOptions.DEBUG) &&
			ApplicationOptions.get(group.getApplicationOption())
		)
		{
			System.out.println(message);
		}
	}

	public static void		logFormat(LogGroup group, String format, Object... arguments)
	{
		log(group, String.format(format, arguments));
	}
}
