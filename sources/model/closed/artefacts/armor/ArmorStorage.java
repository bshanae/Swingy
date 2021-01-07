package model.closed.artefacts.armor;

import application.patterns.SingletonMap;
import model.closed.utils.AbstractStorage;
import model.closed.utils.ResourceManager;
import model.closed.utils.YamlParser;

public class						ArmorStorage extends AbstractStorage<Armor>
{
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
		for (String path : ResourceManager.getInstance().readListing(Armor.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Armor.class));

		super.markLoaded();
	}
}
