package model.closed.artefacts.weapon;

import application.patterns.SingletonMap;
import model.closed.artefacts.helm.Helm;
import model.closed.utils.AbstractStorage;
import model.closed.utils.ResourceManager;
import model.closed.utils.YamlParser;

import java.io.File;

public class						WeaponStorage extends AbstractStorage<Weapon>
{
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
		for (String path : model.closed.utils.ResourceManager.getInstance().readListing(Weapon.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Weapon.class));

		super.markLoaded();
	}
}
