package model.closed.artefacts.helm;

import application.patterns.SingletonMap;
import application.utils.resources.ResourceManager;
import model.closed.utils.AbstractStorage;
import model.closed.utils.YamlParser;

import java.io.File;

public class						HelmStorage extends AbstractStorage<Helm>
{
// -------------------------------> Constants

	private static final String		PATH_TO_HELMS_FOLDER = "model/artefacts/helms";

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
		for (File file : ResourceManager.getFiles(PATH_TO_HELMS_FOLDER))
			data.add(YamlParser.parse(file, Helm.class));

		super.markLoaded();
	}
}
