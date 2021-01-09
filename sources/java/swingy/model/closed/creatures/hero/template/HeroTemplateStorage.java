package swingy.model.closed.creatures.hero.template;

import swingy.application.patterns.SingletonMap;
import swingy.application.service.Exceptions;
import swingy.model.closed.creatures.hero.HeroClass;
import swingy.model.closed.utils.AbstractStorage;
import swingy.model.closed.utils.ResourceManager;
import swingy.model.closed.utils.YamlParser;

public class							HeroTemplateStorage extends AbstractStorage<HeroTemplate>
{
// -----------------------------------> Constants

	private static final String			PATH_TO_WEAPONS_FOLDER = "model/creatures/hero";

// -----------------------------------> Constructor

	public								HeroTemplateStorage()
	{
		super(false);
	}

// -----------------------------------> Properties

	public static HeroTemplateStorage	getInstance()
	{
		return SingletonMap.getInstanceOf(HeroTemplateStorage.class);
	}

// -----------------------------------> Public methods

	public void							download()
	{
		for (String path : ResourceManager.getInstance().readListing(HeroTemplate.class))
			data.add(YamlParser.parse(ResourceManager.getInstance().readText(path), HeroTemplate.class));

		super.markLoaded();
	}

	public HeroTemplate					find(HeroClass heroClass)
	{
		for (HeroTemplate template : data)
		{
			if (template.getHeroClass() == heroClass)
				return template;
		}

		throw new Exceptions.ObjectNotFound();
	}
}
