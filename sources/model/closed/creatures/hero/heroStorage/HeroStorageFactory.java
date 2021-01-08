package model.closed.creatures.hero.heroStorage;

import application.applicationOptions.ApplicationOption;
import application.patterns.SingletonMap;
import model.closed.creatures.hero.heroStorage.concrete.FakeHeroStorage;
import model.closed.creatures.hero.heroStorage.concrete.databaseHeroStorage.DatabaseHeroStorage;

public abstract class					HeroStorageFactory
{
	public static AbstractHeroStorage	buildInstance()
	{
		if (ApplicationOption.USE_DATABASE.isDefined())
			return SingletonMap.getInstanceOf(DatabaseHeroStorage.class);
		else
			return SingletonMap.getInstanceOf(FakeHeroStorage.class);
	}
}
