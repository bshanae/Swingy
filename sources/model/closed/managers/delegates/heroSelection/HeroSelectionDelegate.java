package model.closed.managers.delegates.heroSelection;

import controller.open.Commands;
import model.closed.managers.Session;
import model.closed.objects.creatures.hero.Hero;
import model.closed.managers.heroStorage.HeroStorage;
import model.closed.managers.delegates.Delegate;
import model.closed.managers.delegates.common.ErrorDelegate;
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

		for (Hero hero : HeroStorage.getInstance().getCopy())
			heroes.add((Pockets.Hero)Pockets.build(hero));

		sendRequest(new Requests.HeroSelector(heroes));
	}

	private void			trySelectHero(Commands.Select command)
	{
		try
		{
			Hero			hero = null;

			if (command.getValueAsInteger() != null)
				hero = HeroStorage.getInstance().get(command.getValueAsInteger());
			else if (command.getValueAsString() != null)
				hero = HeroStorage.getInstance().find(command.getValueAsString());
			else
				assert false;

			Session.setHero(hero);
			requestResolution();
		}
		catch (HeroStorage.HeroNotFoundException exception)
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
		catch (HeroStorage.HeroNotFoundException exception)
		{
			linkChild(new ErrorDelegate("Hero not found"));
		}
	}
}
