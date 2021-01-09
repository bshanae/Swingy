package swingy.model.closed.artefacts.armor;

import swingy.application.patterns.SingletonMap;
import swingy.model.closed.utils.AbstractStorage;
import swingy.model.closed.utils.ResourceManager;
import swingy.model.closed.utils.YamlParser;

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
