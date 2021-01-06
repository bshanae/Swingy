package model.closed.creatures.hero.heroStorage.concrete;

import model.closed.creatures.hero.heroStorage.AbstractHeroStorage;

public class						FakeHeroStorage extends AbstractHeroStorage
{
	public void						download()
	{
		super.markLoaded();
	}

	public void						upload() {}
}
