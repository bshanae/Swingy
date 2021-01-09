package swingy.model.closed.creatures.hero.storage.concrete;

import swingy.model.closed.creatures.hero.storage.AbstractHeroStorage;

public class						FakeHeroStorage extends AbstractHeroStorage
{
	public void						download()
	{
		super.markLoaded();
	}

	public void						upload() {}
}
