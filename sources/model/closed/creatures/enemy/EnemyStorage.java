package model.closed.creatures.enemy;

import application.patterns.SingletonMap;
import application.utils.resources.ResourceManager;
import model.closed.artefacts.weapon.Weapon;
import model.closed.utils.YamlParser;
import model.closed.utils.AbstractStorage;

import java.io.File;

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
		for (String path : model.closed.utils.ResourceManager.getInstance().readListing(Enemy.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Enemy.class));

		super.markLoaded();
	}
}
