package swingy.model.closed.creatures.enemy;

import swingy.application.patterns.SingletonMap;
import swingy.application.utils.resources.ResourceManager;
import swingy.model.closed.utils.YamlParser;
import swingy.model.closed.utils.AbstractStorage;

public class						EnemyStorage extends AbstractStorage<Enemy>
{
// -------------------------------> Constructor

	public							EnemyStorage()
	{
		super(false);
	}

// -------------------------------> Properties

	public static EnemyStorage		getInstance()
	{
		return SingletonMap.getInstanceOf(EnemyStorage.class);
	}

// -------------------------------> Public methods

	public void						download()
	{
		for (String path : swingy.model.closed.utils.ResourceManager.getInstance().readListing(Enemy.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Enemy.class));

		super.markLoaded();
	}
}
