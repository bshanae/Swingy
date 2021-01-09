package swingy.model.closed.creatures.hero.storage;

import swingy.application.service.Exceptions;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.utils.AbstractStorage;

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
