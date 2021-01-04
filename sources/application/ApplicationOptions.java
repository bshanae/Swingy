package application;

import java.util.HashMap;
import java.util.Map;

public enum								ApplicationOptions
{
// -----------------------------------> Values

	LAUNCH_CONSOLE("view/console"),
	LAUNCH_GUI("view/gui"),
	DEBUG("debug"),
	LOG_MVC("log-mvc"),
	LOG_DELEGATE("log-delegate"),
	LOG_GAME("log-game"),
	IDE("ide");

// -----------------------------------> Attributes

	public final String					stringRepresentation;

	private static final
	Map<ApplicationOptions, Boolean>	map;

// -----------------------------------> Static initializer

	static
	{
		map = new HashMap<>();

		for (ApplicationOptions option : ApplicationOptions.values())
			map.put(option, false);
	}

// -----------------------------------> Constructor

	private								ApplicationOptions(String stringRepresentation)
	{
		this.stringRepresentation = stringRepresentation;
	}

// -----------------------------------> Public methods

	public static void 					parse(String[] arguments)
	{
		for (String argument : arguments)
			parse(argument);
	}

	public static boolean				get(ApplicationOptions option)
	{
		return map.get(option);
	}

// -----------------------------------> Private methods

	private static void					parse(String argument)
	{
		for (Map.Entry<ApplicationOptions, Boolean> entry : map.entrySet())
			if (entry.getKey().stringRepresentation.equalsIgnoreCase(argument))
				entry.setValue(true);
	}
}
