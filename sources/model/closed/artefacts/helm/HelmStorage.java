package model.closed.artefacts.helm;

import application.patterns.SingletonMap;
import model.closed.artefacts.armor.Armor;
import model.closed.utils.AbstractStorage;
import model.closed.utils.ResourceManager;
import model.closed.utils.YamlParser;

import java.io.File;

public class						HelmStorage extends AbstractStorage<Helm>
{
// -------------------------------> Constructor

	public							HelmStorage()
	{
		super(false);
	}

// -------------------------------> Properties

	public static HelmStorage		getInstance()
	{
		return SingletonMap.getInstanceOf(HelmStorage.class);
	}

// -------------------------------> Public methods

	public void						download()
	{
		for (String path : model.closed.utils.ResourceManager.getInstance().readListing(Helm.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Helm.class));

		super.markLoaded();
	}
}
