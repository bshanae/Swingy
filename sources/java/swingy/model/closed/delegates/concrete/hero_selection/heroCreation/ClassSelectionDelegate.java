package swingy.model.closed.delegates.concrete.hero_selection.heroCreation;

import swingy.application.service.Exceptions;
import swingy.controller.open.Commands;
import lombok.Getter;
import swingy.model.closed.creatures.hero.HeroClass;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.concrete.common.ErrorDelegate;
import swingy.model.open.Requests;

public class ClassSelectionDelegate extends AbstractDelegate
{
// ------------------------------->	Nested types

	public static class				ResolutionObject implements AbstractResolutionObject
	{
		@Getter
		private final HeroClass		heroClass;

		public						ResolutionObject(HeroClass heroClass)
		{
			this.heroClass = heroClass;
		}
	}

// -------------------------------> Implementations

	@Override
	public void						whenActivated()
	{
		sendRequest(new Requests.ClassSelector());
	}

	@Override
	public void						tryToExecuteCommand(ExecutableCommand command)
	{
		String						classString;

		if (command.getCommand() instanceof Commands.Select)
		{
			classString = ((Commands.Select)command.getCommand()).getString();

			try
			{
				resolveLater(new ResolutionObject(HeroClass.fromString(classString)));
			}
			catch (Exceptions.ObjectNotFound exception)
			{
				stackChildLater(new ErrorDelegate("Unknown class"));
			}

			command.markExecuted();
		}
	}
}