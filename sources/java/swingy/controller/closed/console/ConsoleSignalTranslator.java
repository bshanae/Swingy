package swingy.controller.closed.console;

import swingy.application.service.Exceptions;
import swingy.controller.closed.SignalTranslator;
import swingy.controller.open.Commands;
import swingy.view.open.Context;
import swingy.view.open.Signals;

import java.util.*;

public class					ConsoleSignalTranslator extends SignalTranslator
{
// ---------------------------> Attributes

	private static final
	Map<Context, Pattern[]>		staticPatterns = new HashMap<Context, Pattern[]>()
	{{
		put
		(
			Context.INFO,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);

		put
		(
			Context.ERROR,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);

		put
		(
			Context.HERO_SELECTOR,
			new Pattern[]
			{
				Pattern.create(Commands.Create.class, Keyword.create(Keyword.CREATE)),
				Pattern.create(Commands.Select.class, Keyword.create(Keyword.SELECT), Keyword.createWildcard()),
				Pattern.create(Commands.Delete.class, Keyword.create(Keyword.DELETE), Keyword.createWildcard()),
				Pattern.create(Commands.Info.class, Keyword.create(Keyword.INFO), Keyword.createWildcard()),

				Pattern.create(Commands.Gui.class, Keyword.create(Keyword.GUI)),
				Pattern.create(Commands.Exit.class, Keyword.create(Keyword.EXIT))
			}
		);

		put
		(
			Context.HERO_INFO,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);


		put
		(
			Context.NAME_ENTRY,
			new Pattern[]{ Pattern.create(Commands.Enter.class, Keyword.createWildcard()) }
		);

		put
		(
			Context.CLASS_SELECTOR,
			new Pattern[]
			{
				Pattern.create(Commands.Select.class, Keyword.create(Keyword.SELECT), Keyword.createWildcard())
			}
		);

		put
		(
			Context.MAP,
			new Pattern[]
			{
				Pattern.create(Commands.GoNorth.class, Keyword.create(Keyword.NORTH)),
				Pattern.create(Commands.GoEast.class, Keyword.create(Keyword.EAST)),
				Pattern.create(Commands.GoSouth.class, Keyword.create(Keyword.SOUTH)),
				Pattern.create(Commands.GoWest.class, Keyword.create(Keyword.WEST)),

				Pattern.create(Commands.HeroStats.class, Keyword.create(Keyword.STATS)),
				Pattern.create(Commands.HeroInventory.class, Keyword.create(Keyword.INVENTORY)),
				Pattern.create(Commands.Gui.class, Keyword.create(Keyword.GUI)),
				Pattern.create(Commands.Exit.class, Keyword.create(Keyword.EXIT))
			}
		);

		put
		(
			Context.HERO_STATS,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);

		put
		(
			Context.HERO_INVENTORY,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);

		put
		(
			Context.BATTLE,
			new Pattern[]{ Pattern.createWildcard(Commands.Ok.class) }
		);
	}};

// ---------------------------> Implementations

	@Override
	public Commands.Abstract	translateImplementation(Signals.Abstract signal)
	{
		Signals.Console			consoleSignal;
		Pattern[]				possiblePatterns;
		CommandParser			commandParser;

		consoleSignal = (Signals.Console)signal;
		possiblePatterns = findPossiblePatterns(consoleSignal);
		commandParser = new CommandParser(consoleSignal.getInput());

		for (Pattern pattern : possiblePatterns)
		{
			if (commandParser.canApplyPattern(pattern))
				return buildCommand(commandParser, pattern);
		}

		return new Commands.Unknown();
	}

// ---------------------------> Private methods

	private Pattern[]			findPossiblePatterns(Signals.Console signal)
	{
		final Context			context = signal.getContext();

		Map<Context, Pattern[]>	dynamicPatterns;

		if (staticPatterns.containsKey(context))
			return staticPatterns.get(context);
		else
		{
			dynamicPatterns = buildDynamicPatterns(signal);
			if (dynamicPatterns.containsKey(context))
				return dynamicPatterns.get(context);

			throw new Exceptions.ObjectNotFound();
		}
	}

	private static
	Map<Context, Pattern[]>		buildDynamicPatterns(Signals.Console signal)
	{
		Map<Context, Pattern[]>	patterns = new HashMap<>();

		if (signal.getExpectedCommands() == null)
			return patterns;

		patterns.put
		(
			Context.QUESTION,
			new Pattern[]
			{
				Pattern.create(Commands.AnswerA.class, Keyword.create(signal.getExpectedCommands().getCommandA())),
				Pattern.create(Commands.AnswerB.class, Keyword.create(signal.getExpectedCommands().getCommandB()))
			}
		);

		return patterns;
	}

	private static
	Commands.Abstract			buildCommand(CommandParser commandParser, Pattern pattern)
	{
		commandParser.applyPattern(pattern);
		return CommandBuilder.build(commandParser);
	}
}
