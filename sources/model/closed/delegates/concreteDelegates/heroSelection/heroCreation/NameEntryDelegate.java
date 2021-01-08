package model.closed.delegates.concreteDelegates.heroSelection.heroCreation;

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

public class						NameEntryDelegate extends AbstractDelegate
{
// ------------------------------->	Nested types

	public static class				ResolutionObject implements AbstractResolutionObject
	{
		@Getter
		private final String		name;

		public						ResolutionObject(String name)
		{
			this.name = name;
		}
	}

// ------------------------------->	Attributes

	@NotBlank(message = "Name is empty")
	@Size(max = 16, message = "Name is too long")
	@Pattern(regexp = "[A-Za-z0-9]+", message = "Name contains unsupported characters")

	private String					heroName;

// ------------------------------->	Properties

	private static Validator		getValidator()
	{
		ValidatorFactory			factory;
		Validator					validator;

		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		return validator;
	}

// -------------------------------> Implementations

	@Override
	public void						whenActivated()
	{
		sendRequest(new Requests.NameEntry());
	}

	@Override
	public void						tryToExecuteCommand(ExecutableCommand command)
	{
		command.lock();

		if (tryExtractName(command))
			resolveLater(new ResolutionObject(heroName));
	}

// ------------------------------->	Private methods

	private boolean					tryExtractName(ExecutableCommand command)
	{
		String						validationError;

		if (command.getCommand() instanceof Commands.Enter)
		{
			heroName = ((Commands.Enter)command.getCommand()).getString();
			command.markExecuted();

			if ((validationError = validateName()) != null)
			{
				stackChildLater(new ErrorDelegate(validationError));
				return false;
			}
			else
				return true;
		}

		return false;
	}

	private String				validateName()
	{
		for (ConstraintViolation<NameEntryDelegate> violation : getValidator().validate(this))
			return violation.getMessage();

		return null;
	}
}