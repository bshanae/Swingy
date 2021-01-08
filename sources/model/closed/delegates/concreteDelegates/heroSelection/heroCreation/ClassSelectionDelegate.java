package model.closed.delegates.concreteDelegates.heroSelection.heroCreation;

import application.service.Exceptions;
import controller.open.Commands;
import lombok.Getter;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroClass;
import model.closed.creatures.hero.heroStorage.HeroStorageFactory;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
import model.open.Requests;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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