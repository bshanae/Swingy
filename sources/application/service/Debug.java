package application.service;

import application.ApplicationOptions;

public abstract class		Debug
{
	public static boolean	isEnabled()
	{
		return ApplicationOptions.get(ApplicationOptions.DEBUG);
	}

	public static void		throwException(String message)
	{
		if (isEnabled())
			throw new RuntimeException(message);
	}

	public static void		log(LogGroup group, String message)
	{
		if (isEnabled() && group.isEnabled())
			System.out.println(message);
	}

	public static void		logFormat(LogGroup group, String format, Object... arguments)
	{
		log(group, String.format(format, arguments));
	}
}
