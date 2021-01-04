package controller.closed.console;

import controller.closed.SignalTranslator;
import controller.open.Commands;
import view.open.Context;
import view.open.Signals;

import java.util.*;

public class							ConsoleSignalTranslator extends SignalTranslator
{
	private static final
	Map<Context, Pattern[]>				contextToPatterns = new HashMap<Context, Pattern[]>()
	{{
		put
		(
			Context.ERROR,
			new Pattern[]{ new Pattern(Commands.Null.class) }
		);

		put
		(
			Context.HERO_SELECTOR,
			new Pattern[]
			{
				new Pattern(Commands.Select.class, Keyword.VALUE),

				new Pattern(Commands.Create.class, Keyword.CREATE),
				new Pattern(Commands.Select.class, Keyword.SELECT, Keyword.VALUE),
				new Pattern(Commands.Delete.class, Keyword.DELETE, Keyword.VALUE),
				new Pattern(Commands.Delete.class, Keyword.INFO, Keyword.VALUE)
			}
		);

		put
		(
			Context.NAME_ENTRY,
			new Pattern[]{ new Pattern(Commands.Enter.class, Keyword.VALUE) }
		);

		put
		(
			Context.CLASS_SELECTOR,
			new Pattern[]
			{
				new Pattern(Commands.Select.class, Keyword.VALUE),
				new Pattern(Commands.Select.class, Keyword.SELECT, Keyword.VALUE)
			}
		);

		put
		(
			Context.MAP,
			new Pattern[]
			{
				new Pattern(Commands.GoNorth.class, Keyword.NORTH),
				new Pattern(Commands.GoEast.class, Keyword.EAST),
				new Pattern(Commands.GoSouth.class, Keyword.SOUTH),
				new Pattern(Commands.GoWest.class, Keyword.WEST)
			}
		);

		put
		(
			Context.BATTLE,
			new Pattern[]
			{
				new Pattern(Commands.Ok.class, Keyword.ANY)
			}
		);
	}};

	private static final Pattern[]		commonPatterns = new Pattern[]
	{
		new Pattern(Commands.Exit.class, Keyword.EXIT)
	};

	@Override
	public Commands.Abstract			translateImplementation(Signals.Abstract signal)
	{
		Signals.Console					consoleSignal;
		List<Pattern>					possiblePatterns;
		CommandParser					commandParser;
		Pattern							resultPattern;

		consoleSignal = (Signals.Console)signal;
		assert contextToPatterns.containsKey(consoleSignal.getContext());

		possiblePatterns = new LinkedList<>();

		possiblePatterns.addAll(Arrays.asList(contextToPatterns.get(consoleSignal.getContext())));
		possiblePatterns.addAll(Arrays.asList(commonPatterns));

		commandParser = new CommandParser(consoleSignal.getInput());
		resultPattern = null;

		for (Pattern pattern : possiblePatterns)
		{
			if (commandParser.canApplyPattern(pattern))
			{
				if (!pattern.isWildcard())
					return buildCommand(commandParser, pattern);

				resultPattern = pattern;
			}
		}

		if (resultPattern != null)
			return buildCommand(commandParser, resultPattern);

		return new Commands.Null();
	}

	private static Commands.Abstract	buildCommand(CommandParser commandParser, Pattern pattern)
	{
		if (commandParser.canApplyPattern(pattern))
		{
			commandParser.applyPattern(pattern);
			return CommandBuilder.build(commandParser);
		}
		return null;
	}
}
