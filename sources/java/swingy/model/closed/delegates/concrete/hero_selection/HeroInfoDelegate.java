package swingy.model.closed.delegates.concrete.hero_selection;

import swingy.controller.open.Commands;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.open.Requests;

public class				HeroInfoDelegate extends AbstractDelegate
{
// -----------------------> Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// ----------------------->	Attributes

	private final Hero		hero;

// ----------------------->	Constructor

	public					HeroInfoDelegate(Hero hero)
	{
		this.hero = hero;
	}

// ----------------------->	Implementations

	@Override
	public void				whenActivated()
	{
		sendRequest(new Requests.HeroInfo(hero));
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		command.lock();

		if (command.getCommand() instanceof Commands.Ok)
		{
			resolveLater(new ResolutionObject());
			command.markExecuted();
		}
	}
}
