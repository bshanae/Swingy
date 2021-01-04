package model.closed.delegates.heroSelection;

import application.service.Exceptions;
import controller.open.Commands;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroClass;
import model.closed.creatures.hero.HeroStorage;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.Delegate;
import model.closed.delegates.common.ErrorDelegate;
import model.open.Requests;

public class						HeroCreationDelegate extends Delegate
{
// ------------------------------->	Nested types

	public static class				ResolutionMessage extends Delegate.ResolutionMessage
	{
		public final Hero			hero;

		public						ResolutionMessage(Hero hero)
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

// ------------------------------->	Public methods

	@Override
	public void						whenActivated(boolean isFirstTimeActivated)
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
				requestResolution(new ResolutionMessage(hero));
				break;
		}
	}

	@Override
	public void						whenResponded(Commands.Abstract command)
	{
		if (tryRespondToCommonCommands(command))
			return ;

		switch (state)
		{
			case WAITING_FOR_NAME :
				extractName(command);
				requestClass();
				break;

			case WAITING_FOR_CLASS:
				try
				{
					extractClass(command);
					createHero();
				}
				catch (Exception exception)
				{
					linkChild(new ErrorDelegate("Unknown class"));
					requestClass();
				}
				break;
		}
	}
// ------------------------------->	Private methods

	private void					requestName()
	{
		state = State.WAITING_FOR_NAME;
		sendRequest(new Requests.NameEntry());
	}

	private void 					extractName(Commands.Abstract command)
	{
		if (validateCommand(command, Commands.Enter.class))
		{
			heroName = ((Commands.Enter)command).getValueAsString();
			state = State.RECEIVED_NAME;
		}
	}

	private void					requestClass()
	{
		state = State.WAITING_FOR_CLASS;
		sendRequest(new Requests.ClassSelector());
	}

	private void 					extractClass(Commands.Abstract command)
	{
		String			classString;

		if (validateCommand(command, Commands.Select.class))
		{
			classString = ((Commands.Select)command).getValueAsString();

			heroClass = getHeroClassFromString(classString);
			state = State.RECEIVED_CLASS;
		}
	}

	private void 					createHero()
	{
		HeroTemplate				template;

		template = HeroTemplateStorage.getInstance().find(heroClass);
		hero = new Hero(template, heroName);

		HeroStorage.getInstance().add(hero);
		state = State.CREATED_HERO;
	}

	private HeroClass				getHeroClassFromString(String string)
	{
		if (string.equalsIgnoreCase(HeroClass.WARRIOR.toString()))
			return HeroClass.WARRIOR;
		else if (string.equalsIgnoreCase(HeroClass.SWORDSMAN.toString()))
			return HeroClass.SWORDSMAN;
		else if (string.equalsIgnoreCase(HeroClass.ASSASSIN.toString()))
			return HeroClass.ASSASSIN;
		else if (string.equalsIgnoreCase(HeroClass.MAGE.toString()))
			return HeroClass.MAGE;
		else
			throw new Exceptions.UnexpectedCodeBranch();
	}
}
