package swingy.application.options;

import java.util.HashMap;
import java.util.Map;

public enum ApplicationOption
{
// -------------------------------> Values

	LAUNCH_CONSOLE("console"),
	LAUNCH_GUI("gui"),

	DEBUG("debug"),
	IDE("ide"),

	LOG_MVC("log-mvc"),
	LOG_DELEGATE("log-delegate"),
	LOG_GAME("log-game"),
	LOG_FULL("log-full"),

	ALWAYS_DROP_ARTEFACT("always-drop-artefact"),
	ALWAYS_ESCAPE("always-escape"),
	ALWAYS_WIN("always-win"),
	ALWAYS_LOSE("always-lose"),

	USE_DATABASE("use-database"),
	BETTER_ESCAPING("better-escaping"),
	BETTER_DROPPING("better-artefact-dropping");

// -------------------------------> Attributes

	public final String				stringRepresentation;

	static final
	Map<ApplicationOption, Boolean>	map;

// -------------------------------> Static initializer

	static
	{
		map = new HashMap<>();

		for (ApplicationOption option : ApplicationOption.values())
			map.put(option, false);
	}

// -------------------------------> Constructor

	private							ApplicationOption(String stringRepresentation)
	{
		this.stringRepresentation = stringRepresentation;
	}

// -------------------------------> Public methods

	public boolean					isDefined()
	{
		return map.get(this);
	}
}
