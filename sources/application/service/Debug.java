package application.service;

import application.applicationOptions.ApplicationOption;

public abstract class		Debug
{
	public static boolean	isEnabled()
	{
		return ApplicationOption.DEBUG.isDefined();
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
