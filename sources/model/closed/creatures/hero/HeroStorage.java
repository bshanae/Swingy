package model.closed.creatures.hero;

import application.patterns.SingletonMap;
import application.service.Exceptions;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.utils.AbstractStorage;

public class					HeroStorage extends AbstractStorage<Hero>
{
// ---------------------------> Constructor

	public 						HeroStorage()
	{
		super(true);
	}

// ---------------------------> Properties

	public static HeroStorage	getInstance()
	{
		return SingletonMap.getInstanceOf(HeroStorage.class);
	}

// ---------------------------> Public methods

	public void					download()
	{
		data.add(new Hero(HeroTemplateStorage.getInstance().find(HeroClass.SWORDSMAN), "Ilya"));
		super.markLoaded();
	}

	public void					upload()
	{
		// TODO
	}

	public Hero					find(String name)
	{
		for (Hero hero : data)
		{
			if (hero.getName().equalsIgnoreCase(name))
				return hero;
		}

		throw new Exceptions.ObjectNotFound();
	}
}
