package controller.closed.console;

import application.service.Exceptions;
import lombok.Getter;

public class					Keyword
{
// ---------------------------> Constants

	public static final String	CREATE = "create";
	public static final String	SELECT = "select";
	public static final String	DELETE = "delete";
	public static final String	INFO = "info";
	public static final String	NORTH = "north";
	public static final String	EAST = "east";
	public static final String	SOUTH = "south";
	public static final String	WEST = "west";
	public static final String	STATS = "stats";
	public static final String	INVENTORY = "inventory";
	public static final String	GUI = "gui";
	public static final String	EXIT = "exit";

// ---------------------------> Attributes

	private final String		string;

	@Getter
	private final boolean		isWildcard;

// ---------------------------> Constructors

	public static Keyword		create(String string)
	{
		return new Keyword(string, false);
	}

	public static Keyword		createWildcard()
	{
		return new Keyword(null, true);
	}

	private						Keyword(String string, boolean isWildcard)
	{
		this.string = string;
		this.isWildcard = isWildcard;
	}

// ---------------------------> Properties

	public String				getString()
	{
		if (isWildcard)
			throw new Exceptions.InvalidUsage();

		return string;
	}

// ---------------------------> Public methods

	public boolean				compare(String token)
	{
		return isWildcard || string.equals(token);
	}
}