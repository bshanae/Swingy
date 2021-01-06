package model.closed.creatures.enemy;

import application.patterns.SingletonMap;
import application.utils.resources.ResourceManager;
import model.closed.utils.YamlParser;
import model.closed.utils.AbstractStorage;

import java.io.File;

public class						EnemyStorage extends AbstractStorage<Enemy>
{
// -------------------------------> Constants

	private static final String		PATH_TO_ENEMIES_FOLDER = "model/creatures/enemies";

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
		for (File file : ResourceManager.getFiles(PATH_TO_ENEMIES_FOLDER))
			data.add(YamlParser.parse(file, Enemy.class));

		super.markLoaded();
	}
}
