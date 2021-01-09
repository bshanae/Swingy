package swingy.model.closed.delegates.concrete.hero_selection;

import swingy.application.service.Exceptions;
import swingy.controller.open.Commands;
import swingy.model.closed.Session;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.storage.AbstractHeroStorage;
import swingy.model.closed.creatures.hero.storage.HeroStorageFactory;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.concrete.common.ErrorDelegate;
import swingy.model.closed.delegates.concrete.hero_selection.heroCreation.HeroCreationDelegate;
import swingy.model.open.Pockets;
import swingy.model.open.Requests;

import java.util.LinkedList;
import java.util.List;

public class				HeroSelectionDelegate extends AbstractDelegate
{
// ----------------------->	Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// ----------------------->	Implementations

	@Override
	public void				whenActivated()
	{
		showHeroSelectionScreen();
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Create)
		{
			if (HeroStorageFactory.buildInstance().size() >= 4)
				stackChildLater(new ErrorDelegate("Can't create more heroes"));
			else
				stackChildLater(new HeroCreationDelegate());
		}
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
		Hero				selectedHero;

		try
		{
			selectedHero = HeroStorageFactory.buildInstance().find(command.getString());
			resolveLater(new ResolutionObject());

			if (selectedHero.didFinishGame())
				stackChildLater(new ErrorDelegate("Can't select this hero"));
			else
				Session.getInstance().setHero(selectedHero);
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
