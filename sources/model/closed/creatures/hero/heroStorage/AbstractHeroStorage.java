package model.closed.creatures.hero.heroStorage;

import application.service.Exceptions;
import model.closed.creatures.hero.Hero;
import model.closed.utils.AbstractStorage;

public abstract class			AbstractHeroStorage extends AbstractStorage<Hero>
{
// ---------------------------> Constructor

	public						AbstractHeroStorage()
	{
		super(true);
	}

// ---------------------------> Public methods

	public abstract	void		upload();

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
