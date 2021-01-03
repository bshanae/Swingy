package controller.closed.console;

import java.util.HashMap;
import java.util.Map;

enum								Keyword
{
	// Wildcards
	ANY,
	VALUE,

	// Constants
	CREATE,
	SELECT,
	DELETE,
	INFO,
	EXIT,
	NORTH,
	EAST,
	SOUTH,
	WEST;

	private static final
	Map<String, Keyword>			stringToKeyword = new HashMap<String, Keyword>()
	{{
		put("create", CREATE);
		put("select", SELECT);
		put("delete", DELETE);
		put("info", INFO);
		put("north", NORTH);
		put("east", EAST);
		put("south", SOUTH);
		put("west", WEST);
		put("exit", EXIT);
	}};

	public boolean					isWildcard()
	{
		return this == ANY || this == VALUE;
	}

	public boolean					checkToken(String token)
	{
		if (this == VALUE)
			return true;

		for (Map.Entry<String, Keyword> entry : stringToKeyword.entrySet())
		{
			if (token.equalsIgnoreCase(entry.getKey()))
				return this == entry.getValue();
		}

		return false;
	}
}