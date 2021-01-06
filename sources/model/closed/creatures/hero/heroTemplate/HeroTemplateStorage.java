package model.closed.creatures.hero.heroTemplate;

import application.patterns.SingletonMap;
import application.service.Exceptions;
import application.utils.resources.ResourceManager;
import model.closed.creatures.hero.HeroClass;
import model.closed.utils.AbstractStorage;
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
		for (File file : ResourceManager.getFiles(PATH_TO_WEAPONS_FOLDER))
			data.add(YamlParser.parse(file, HeroTemplate.class));

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
