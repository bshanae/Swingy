package model.closed.delegates.heroSelection;

import controller.open.Commands;
import model.closed.creatures.hero.Hero;
import model.closed.delegates.Delegate;
import model.open.Requests;

public class				HeroInfoDelegate extends Delegate
{
	private final Hero		hero;

	public					HeroInfoDelegate(Hero hero)
	{
		this.hero = hero;
	}

	@Override
	public void				whenActivated(boolean isFirstTimeActivated)
	{
		sendRequest(new Requests.HeroInfo(hero));
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		if (command instanceof Commands.Ok)
			requestResolution();
		else
			throw new Delegate.UnexpectedCommandException(command);
	}
}
