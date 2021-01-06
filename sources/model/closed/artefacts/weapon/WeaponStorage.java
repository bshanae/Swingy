package model.closed.artefacts.weapon;

import application.patterns.SingletonMap;
import application.utils.resources.ResourceManager;
import model.closed.utils.AbstractStorage;
import model.closed.utils.YamlParser;

import java.io.File;

public class						WeaponStorage extends AbstractStorage<Weapon>
{
// -------------------------------> Constants

	private static final String		PATH_TO_WEAPONS_FOLDER = "model/artefacts/weapons";

// -------------------------------> Constructor

	public							WeaponStorage()
	{
		super(false);
	}

// -------------------------------> Properties

	public static WeaponStorage		getInstance()
	{
		return SingletonMap.getInstanceOf(WeaponStorage.class);
	}

// -------------------------------> Public methods

	public void						download()
	{
		for (File file : ResourceManager.getFiles(PATH_TO_WEAPONS_FOLDER))
			data.add(YamlParser.parse(file, Weapon.class));

		super.markLoaded();
	}
}
