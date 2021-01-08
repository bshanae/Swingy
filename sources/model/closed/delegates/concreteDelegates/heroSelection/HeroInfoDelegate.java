package model.closed.delegates.concreteDelegates.heroSelection;

import controller.open.Commands;
import model.closed.creatures.hero.Hero;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.open.Requests;

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
