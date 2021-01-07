package model.closed.utils;

import application.patterns.SingletonMap;
import model.closed.artefacts.armor.Armor;
import model.closed.artefacts.helm.Helm;
import model.closed.artefacts.weapon.Weapon;
import model.closed.creatures.enemy.Enemy;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class					ResourceManager extends application.utils.resources.ResourceManager
{
// ---------------------------> Exceptions

	public static class			CantListEntitiesOfClass extends RuntimeException
	{
		public 					CantListEntitiesOfClass(Class<?> class_)
		{
			super("Can't list entities of class '" + class_.getName() + "'");
		}
	}

// ---------------------------> Attributes

	private static final
	Map<Class<?>, String>		classToListingName = new HashMap<Class<?>, String>()
	{{
		put(Helm.class, "Helms.txt");
		put(Armor.class, "Armors.txt");
		put(Weapon.class, "Weapons.txt");
		put(Enemy.class, "Enemies.txt");
		put(HeroTemplate.class, "HeroTemplates.txt");
	}};

// ---------------------------> Properties

	public static
	ResourceManager				getInstance()
	{
		return SingletonMap.getInstanceOf(ResourceManager.class);
	}

// ---------------------------> Public methods

	public List<String>			readListing(Class<?> class_)
	{
		if (!classToListingName.containsKey(class_))
			throw new CantListEntitiesOfClass(class_);

		return readLines(getPathToListing(classToListingName.get(class_)));
	}

// ---------------------------> Private methods

	public String				getPathToListing(String listingName)
	{
		return "model/listings/" + listingName;
	}
}
