package swingy.model.closed.artefacts.helm;

import swingy.application.patterns.SingletonMap;
import swingy.model.closed.utils.AbstractStorage;
import swingy.model.closed.utils.ResourceManager;
import swingy.model.closed.utils.YamlParser;

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
		for (String path : ResourceManager.getInstance().readListing(Helm.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), Helm.class));

		super.markLoaded();
	}
}
