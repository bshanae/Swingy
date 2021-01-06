package model.closed.creatures.hero.heroStorage;

import application.ApplicationOptions;
import application.patterns.SingletonMap;
import model.closed.creatures.hero.heroStorage.concrete.FakeHeroStorage;
import model.closed.creatures.hero.heroStorage.concrete.databaseHeroStorage.DatabaseHeroStorage;

public abstract class					HeroStorageFactory
{
	public static AbstractHeroStorage	buildInstance()
	{
		if (ApplicationOptions.get(ApplicationOptions.USE_DATABASE))
			return SingletonMap.getInstanceOf(DatabaseHeroStorage.class);
		else
			return SingletonMap.getInstanceOf(FakeHeroStorage.class);
	}
}
