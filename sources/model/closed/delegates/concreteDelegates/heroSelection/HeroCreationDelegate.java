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
import model.open.Requests;

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

	private String					heroName;
	private HeroClass				heroClass;
	private Hero					hero;

// ------------------------------->	Constructor

	public							HeroCreationDelegate()
	{
		state = State.PENDING;
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
		if (command.getCommand() instanceof Commands.Enter)
		{
			heroName = ((Commands.Enter)command.getCommand()).getString();
			state = State.RECEIVED_NAME;

			command.markExecuted();
			return true;
		}

		return false;
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