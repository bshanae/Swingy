package model.closed.managers.delegates.heroSelection;

import controller.open.Commands;
import model.closed.objects.creatures.hero.Hero;
import model.closed.managers.heroStorage.HeroStorage;
import model.closed.managers.delegates.Delegate;
import model.closed.managers.delegates.common.ErrorDelegate;
import model.closed.objects.creatures.hero.HeroClasses;
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
	private HeroClasses.Abstract	heroClass;
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
		hero = heroClass.createHero(heroName);
		HeroStorage.getInstance().add(hero);

		state = State.CREATED_HERO;
	}

	private HeroClasses.Abstract	getHeroClassFromString(String string)
	{
		if (string.equalsIgnoreCase(HeroClasses.Warrior.getInstance().getName()))
			return HeroClasses.Warrior.getInstance();
		else if (string.equalsIgnoreCase(HeroClasses.Swordsman.getInstance().getName()))
			return HeroClasses.Swordsman.getInstance();
		else if (string.equalsIgnoreCase(HeroClasses.Assassin.getInstance().getName()))
			return HeroClasses.Assassin.getInstance();
		else if (string.equalsIgnoreCase(HeroClasses.Mage.getInstance().getName()))
			return HeroClasses.Mage.getInstance();
		else
			throw new RuntimeException(); // TODO
	}
}
