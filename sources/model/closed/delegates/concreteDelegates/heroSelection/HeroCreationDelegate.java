package model.closed.delegates.concreteDelegates.heroSelection;

import controller.open.Commands;
import lombok.Getter;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroClass;
import model.closed.creatures.hero.heroStorage.HeroStorageFactory;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
import model.open.Requests;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;

public class						HeroCreationDelegate extends AbstractDelegate
{
// ------------------------------->	Nested types

	public static class				ResolutionObject implements AbstractResolutionObject
	{
		@Getter
		private final Hero			hero;

		public						ResolutionObject(Hero hero)
		{
			this.hero = hero;
		}
	}

	private enum					State
	{
		PENDING,
		WAITING_FOR_NAME,
		RECEIVED_NAME,
		WAITING_FOR_CLASS,
		RECEIVED_CLASS,
		CREATED_HERO
	}

// ------------------------------->	Attributes

	private State					state;

	@NotBlank(message = "Name is empty")
	@Size(max = 16, message = "Name is too long")
	@Pattern(regexp = "[A-Za-z0-9]+", message = "Name contains unsupported characters")
	private String					heroName;

	private HeroClass				heroClass;
	private Hero					hero;

// ------------------------------->	Constructor

	public							HeroCreationDelegate()
	{
		state = State.PENDING;
	}

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
	public void						whenActivated(boolean isFirstTime)
	{
		requestName();
	}

	@Override
	public void						whenUpdated()
	{
		switch (state)
		{
			case RECEIVED_NAME:
				requestClass();
				break;

			case CREATED_HERO:
				resolveLater(new ResolutionObject(hero));
				break;
		}
	}

	@Override
	public void						tryToExecuteCommand(ExecutableCommand command)
	{
		switch (state)
		{
			case WAITING_FOR_NAME :
				if (tryExtractName(command))
					requestClass();
				break;

			case WAITING_FOR_CLASS:
				if (tryExtractClass(command))
					createHero();
				break;
		}
	}
// ------------------------------->	Private methods

	private void					requestName()
	{
		state = State.WAITING_FOR_NAME;
		sendRequest(new Requests.NameEntry());
	}

	private boolean					tryExtractName(ExecutableCommand command)
	{
		String						validationError;

		if (command.getCommand() instanceof Commands.Enter)
		{
			heroName = ((Commands.Enter)command.getCommand()).getString();
			command.markExecuted();

			System.out.println("Hero name = " + heroName);

			if ((validationError = validateName()) != null)
			{
				stackChildLater(new ErrorDelegate(validationError));
				return false;
			}
			else
			{
				state = State.RECEIVED_NAME;
				return true;
			}
		}

		return false;
	}

	private String				validateName()
	{
		for (ConstraintViolation<HeroCreationDelegate> violation : getValidator().validate(this))
			return violation.getMessage();

		return null;
	}

	private void					requestClass()
	{
		state = State.WAITING_FOR_CLASS;
		sendRequest(new Requests.ClassSelector());
	}

	private boolean					tryExtractClass(ExecutableCommand command)
	{
		String						classString;

		if (command.getCommand() instanceof Commands.Select)
		{
			classString = ((Commands.Select)command.getCommand()).getString();

			heroClass = HeroClass.fromString(classString);
			state = State.RECEIVED_CLASS;

			command.markExecuted();
			return true;
		}

		return false;
	}

	private void 					createHero()
	{
		HeroTemplate				template;

		template = HeroTemplateStorage.getInstance().find(heroClass);
		hero = Hero.create(template, heroName);

		HeroStorageFactory.buildInstance().add(hero);
		state = State.CREATED_HERO;
	}
}