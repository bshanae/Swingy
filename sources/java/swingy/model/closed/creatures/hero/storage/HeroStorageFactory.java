package swingy.model.closed.creatures.hero.storage;

import swingy.application.options.ApplicationOption;
import swingy.application.patterns.SingletonMap;
import swingy.model.closed.creatures.hero.storage.concrete.FakeHeroStorage;
import swingy.model.closed.creatures.hero.storage.concrete.database.DatabaseHeroStorage;

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
