package model.closed.artefacts.armor;

import application.patterns.SingletonMap;
import application.utils.ResourceManager;
import model.closed.utils.AbstractStorage;
import model.closed.utils.YamlParser;

import java.io.File;

public class						ArmorStorage extends AbstractStorage<Armor>
{
// -------------------------------> Constants

	private static final String		PATH_TO_ARMORS_FOLDER = "model/artefacts/armors";

// -------------------------------> Constructor

	public							ArmorStorage()
	{
		super(false);
	}

// -------------------------------> Properties

	public static ArmorStorage		getInstance()
	{
		return SingletonMap.getInstanceOf(ArmorStorage.class);
	}

// -------------------------------> Public methods

	public void						download()
	{
		for (File file : ResourceManager.getFiles(PATH_TO_ARMORS_FOLDER))
			data.add(YamlParser.parse(file, Armor.class));

		super.markLoaded();
	}
}
