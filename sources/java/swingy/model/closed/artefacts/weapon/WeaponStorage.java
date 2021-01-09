package swingy.model.closed.artefacts.weapon;

import swingy.application.patterns.SingletonMap;
import swingy.model.closed.utils.AbstractStorage;
import swingy.model.closed.utils.ResourceManager;
import swingy.model.closed.utils.YamlParser;

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
		for (String path : ResourceManager.getInstance().readListing(Weapon.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Weapon.class));

		super.markLoaded();
	}
}
