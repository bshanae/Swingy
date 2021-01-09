package swingy.application.options;

public abstract	class	ApplicationOptionsValidator
{
	public static class	InvalidOptionsException extends RuntimeException {}

	public static void	validate()
	{
		if (!ApplicationOption.LAUNCH_CONSOLE.isDefined() && !ApplicationOption.LAUNCH_GUI.isDefined())
			throw new InvalidOptionsException();
	}
}
