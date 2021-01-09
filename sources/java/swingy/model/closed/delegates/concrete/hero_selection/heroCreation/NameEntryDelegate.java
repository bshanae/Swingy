package swingy.model.closed.delegates.concrete.hero_selection.heroCreation;

import swingy.controller.open.Commands;
import lombok.Getter;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.storage.HeroStorageFactory;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.concrete.common.ErrorDelegate;
import swingy.model.open.Requests;

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
	@Pattern(regexp = "[A-Za-z0-9-_]+", message = "Name contains unsupported characters")

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

		for (Hero hero : HeroStorageFactory.buildInstance())
		{
			if (hero.getName().equalsIgnoreCase(heroName))
				return "Hero with same name already exists";
		}

		return null;
	}
}