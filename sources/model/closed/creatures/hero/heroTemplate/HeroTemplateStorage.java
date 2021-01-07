package model.closed.creatures.hero.heroTemplate;

import application.patterns.SingletonMap;
import application.service.Exceptions;
import model.closed.artefacts.armor.Armor;
import model.closed.creatures.hero.HeroClass;
import model.closed.utils.AbstractStorage;
import model.closed.utils.ResourceManager;
import model.closed.utils.YamlParser;

import java.io.File;

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
