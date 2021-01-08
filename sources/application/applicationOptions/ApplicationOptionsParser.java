package application.applicationOptions;

import java.util.Map;

public class 				ApplicationOptionsParser
{
	public static void 		parse(String[] arguments)
	{
		for (String argument : arguments)
			parse(argument);
	}

	private static void		parse(String argument)
	{
		for (Map.Entry<ApplicationOption, Boolean> entry : ApplicationOption.map.entrySet())
		{
			if (entry.getKey().stringRepresentation.equalsIgnoreCase(argument))
				entry.setValue(true);
		}
	}
}
