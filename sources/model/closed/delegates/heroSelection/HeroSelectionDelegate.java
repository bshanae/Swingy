package model.closed.delegates.heroSelection;

import application.service.Exceptions;
import controller.open.Commands;
import model.closed.Session;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroStorage;
import model.closed.delegates.Delegate;
import model.closed.delegates.common.ErrorDelegate;
import model.open.Pockets;
import model.open.Requests;

import java.util.LinkedList;
import java.util.List;

public class				HeroSelectionDelegate extends Delegate
{
// ----------------------->	Open methods

	@Override
	public void				whenActivated(boolean isFirstTimeActivated)
	{
		showHeroSelectionScreen();
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		if (tryRespondToCommonCommands(command))
			return ;

		if (command instanceof Commands.Create)
			linkChild(new HeroCreationDelegate());
		else if (command instanceof Commands.Select)
			trySelectHero((Commands.Select)command);
		else if (command instanceof Commands.Delete)
			tryDeleteHero((Commands.Delete)command);
		else if (command instanceof Commands.Info)
			tryShowInfo((Commands.Info)command);
	}

	@Override
	protected void			whenChildResolved(ResolutionMessage message)
	{
	}

// ----------------------->	Closed methods

	private void			showHeroSelectionScreen()
	{
		List<Pockets.Hero>	heroes;

		heroes = new LinkedList<>();

		for (Hero hero : HeroStorage.getInstance())
			heroes.add(new Pockets.Hero(hero));

		sendRequest(new Requests.HeroSelector(heroes));
	}

	private void			trySelectHero(Commands.Select command)
	{
		try
		{
			Hero			hero;

			if (command.getValueAsInteger() != null)
				hero = HeroStorage.getInstance().get(command.getValueAsInteger());
			else if (command.getValueAsString() != null)
				hero = HeroStorage.getInstance().find(command.getValueAsString());
			else
				throw new Exceptions.UnexpectedCodeBranch();

			Session.setHero(hero);
			requestResolution();
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			linkChild(new ErrorDelegate("Hero not found"));
		}
	}

	private void			tryDeleteHero(Commands.Delete command)
	{
		try
		{
			if (command.getValueAsInteger() != null)
				HeroStorage.getInstance().delete(command.getValueAsInteger());
			else if (command.getValueAsString() != null)
				HeroStorage.getInstance().delete(HeroStorage.getInstance().find(command.getValueAsString()));
			else
				assert false;

			showHeroSelectionScreen();
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			linkChild(new ErrorDelegate("Hero not found"));
		}
	}

	private void			tryShowInfo(Commands.Info command)
	{
		try
		{
			Hero			hero;

			if (command.getValueAsInteger() != null)
				hero = HeroStorage.getInstance().get(command.getValueAsInteger());
			else if (command.getValueAsString() != null)
				hero = HeroStorage.getInstance().find(command.getValueAsString());
			else
				throw new Exceptions.UnexpectedCodeBranch();

			linkChild(new HeroInfoDelegate(hero));
		}
		catch (Exceptions.ObjectNotFound exception)
		{
			linkChild(new ErrorDelegate("Hero not found"));
		}
	}
}
