package model.closed.delegates.concreteDelegates.heroSelection;

import application.service.Exceptions;
import controller.open.Commands;
import model.closed.Session;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.heroStorage.AbstractHeroStorage;
import model.closed.creatures.hero.heroStorage.HeroStorageFactory;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
import model.open.Pockets;
import model.open.Requests;

import java.util.LinkedList;
import java.util.List;

public class				HeroSelectionDelegate extends AbstractDelegate
{
// ------------------------------->	Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// ----------------------->	Implementations

	@Override
	public void				whenActivated(boolean isFirstTime)
	{
		showHeroSelectionScreen();
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Create)
			stackChildLater(new HeroCreationDelegate());
		else if (command.getCommand() instanceof Commands.Select)
			trySelectHero((Commands.Select)command.getCommand());
		else if (command.getCommand() instanceof Commands.Delete)
			tryDeleteHero((Commands.Delete)command.getCommand());
		else if (command.getCommand() instanceof Commands.Info)
			tryShowInfo((Commands.Info)command.getCommand());
		else
			return;

		command.markExecuted();
	}

// ----------------------->	Private methods

	private void			showHeroSelectionScreen()
	{
		List<Pockets.Hero>	heroes;

		heroes = new LinkedList<>();

		for (Hero hero : HeroStorageFactory.buildInstance())
			heroes.add(new Pockets.Hero(hero));

		sendRequest(new Requests.HeroSelector(heroes));
	}

	private void			trySelectHero(Commands.Select command)
	{
		try
		{
			Session.setHero(HeroStorageFactory.buildInstance().find(command.getString()));
			resolveLater(new ResolutionObject());
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			stackChildLater(new ErrorDelegate("Hero not found"));
		}
	}

	private void			tryDeleteHero(Commands.Delete command)
	{
		AbstractHeroStorage	heroStorage;

		heroStorage = HeroStorageFactory.buildInstance();

		try
		{
			heroStorage.delete(heroStorage.find(command.getString()));
			showHeroSelectionScreen();
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			stackChildLater(new ErrorDelegate("Hero not found"));
		}
	}

	private void			tryShowInfo(Commands.Info command)
	{
		try
		{
			Hero			hero;

			hero = HeroStorageFactory.buildInstance().find(command.getString());
			stackChildLater(new HeroInfoDelegate(hero));
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			stackChildLater(new ErrorDelegate("Hero not found"));
		}
	}
}
