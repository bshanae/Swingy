package swingy.model.closed.utils;

import swingy.application.patterns.SingletonMap;
import swingy.model.closed.artefacts.armor.Armor;
import swingy.model.closed.artefacts.helm.Helm;
import swingy.model.closed.artefacts.weapon.Weapon;
import swingy.model.closed.creatures.enemy.Enemy;
import swingy.model.closed.creatures.hero.template.HeroTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class					ResourceManager extends swingy.application.utils.resources.ResourceManager
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
