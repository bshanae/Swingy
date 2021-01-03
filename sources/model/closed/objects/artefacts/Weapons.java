package model.closed.objects.artefacts;

import application.utils.Range;
import model.closed.objects.Attack;

import java.util.LinkedList;
import java.util.List;

public abstract class						Weapons
{
// ---------------------------------------> Abstract

	public static abstract class			Abstract extends Artefact
	{
		protected							Abstract(String name)
		{
			super(name);
		}

		public abstract
		WeaponsCategories.Abstract			getCategory();

		public abstract List<Attack>		getAttacks();
	}

// ---------------------------------------> Hammers

	private static abstract class			AbstractHammer extends Abstract
	{
		public								AbstractHammer(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Hammer();
		}

	}

	public static class						Hammer extends AbstractHammer
	{
		public								Hammer()
		{
			super("Hammer");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Regular blow", 95, new Range<>(20)));
			attacks.add(new Attack("Strong blow", 5, new Range<>(80)));

			return attacks;
		}
	}

	public static class						GreatHammer extends AbstractHammer
	{
		public								GreatHammer()
		{
			super("Great hammer");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Regular blow", 95, new Range<>(90)));
			attacks.add(new Attack("Strong blow", 5, new Range<>(250)));

			return attacks;
		}
	}

	public static class						KnightGreatHammer extends AbstractHammer
	{
		public								KnightGreatHammer()
		{
			super("Great hammer");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Regular blow", 95, new Range<>(500)));
			attacks.add(new Attack("Strong blow", 5, new Range<>(1600)));

			return attacks;
		}
	}

// ---------------------------------------> Swords

	private static abstract class			AbstractSword extends Abstract
	{
		public								AbstractSword(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Sword();
		}

	}

	public static class						OldSword extends AbstractSword
	{
		public								OldSword()
		{
			super("Old sword")	;
		}

		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(30, 40), 0.1f));

			return attacks;
		}
	}

	public static class						SteelSword extends AbstractSword
	{
		public								SteelSword()
		{
			super("Steel sword");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Light blow", 60, new Range<>(60, 90), 0.1f));
			attacks.add(new Attack("Strong blow", 40, new Range<>(110), 0.1f));

			return attacks;
		}
	}

	public static class						SoldierSword extends AbstractSword
	{
		public								SoldierSword()
		{
			super("Soldier sword");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Light blow", 60, new Range<>(110, 130), 0.1f));
			attacks.add(new Attack("Strong blow", 40, new Range<>(160), 0.1f));

			return attacks;
		}
	}

	public static class						KnightSword extends AbstractSword
	{
		public								KnightSword()
		{
			super("Knight sword");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Light blow", 60, new Range<>(180, 240), 0.1f));
			attacks.add(new Attack("Strong blow", 60, new Range<>(400), 0.1f));
			attacks.add(new Attack("Thrust", 40, new Range<>(800), 0.3f));

			return attacks;
		}
	}


// ---------------------------------------> Rapiers

	private static abstract class			AbstractRapier extends Abstract
	{
		public								AbstractRapier(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Rapier();
		}

	}

	public static class						Rapier extends AbstractRapier
	{
		public								Rapier()
		{
			super("Rapier");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Thrust", 60, new Range<>(40, 60), 0.1f));
			attacks.add(new Attack("Blow", 40, new Range<>(40), 0.1f));

			return attacks;
		}
	}

	public static class						KnightRapier extends AbstractRapier
	{
		public								KnightRapier()
		{
			super("Knight rapier");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Thrust", 60, new Range<>(250, 400), 0.3f));
			attacks.add(new Attack("Double thrust", 20, new Range<>(500, 700), 0.25f));
			attacks.add(new Attack("Blow", 20, new Range<>(200), 0.01f));

			return attacks;
		}
	}

	public static class						LegendaryRapier extends AbstractRapier
	{
		public								LegendaryRapier()
		{
			super("Legendary rapier");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Thrust", 60, new Range<>(600, 800), 0.3f));
			attacks.add(new Attack("Double thrust", 20, new Range<>(1400, 1800), 0.25f));
			attacks.add(new Attack("Blow", 20, new Range<>(500), 0.01f));

			return attacks;
		}
	}

// ---------------------------------------> Scimitars

	private static abstract class			AbstractScimitar extends Abstract
	{
		public								AbstractScimitar(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Scimitar();
		}

	}

	public static class						OldScimitar extends AbstractScimitar
	{
		public								OldScimitar()
		{
			super("Old scimitar");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(10, 40), 0.2f));

			return attacks;
		}
	}

	public static class						SteelScimitar extends AbstractScimitar
	{
		public								SteelScimitar()
		{
			super("Steel scimitar");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(40, 70), 0.2f));

			return attacks;
		}
	}

	public static class						BanditScimitar extends AbstractScimitar
	{
		public								BanditScimitar()
		{
			super("Bandit scimitar");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(120, 200), 0.2f));

			return attacks;
		}
	}

	public static class						LegendaryScimitar extends AbstractScimitar
	{
		public								LegendaryScimitar()
		{
			super("Legendary scimitar");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(500, 2500), 0.3f));

			return attacks;
		}
	}

// ---------------------------------------> Dagger

	private static abstract class			AbstractDagger extends Abstract
	{
		public								AbstractDagger(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Dagger();
		}

	}

	public static class						OldDagger extends AbstractDagger
	{
		public								OldDagger()
		{
			super("Old dagger");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(20, 30), 0.5f));

			return attacks;
		}
	}

	public static class						SteelDagger extends AbstractDagger
	{
		public								SteelDagger()
		{
			super("Steel dagger");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(100, 150), 0.5f));

			return attacks;
		}
	}

	public static class						BanditDagger extends AbstractDagger
	{
		public								BanditDagger()
		{
			super("Bandit dagger");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 80, new Range<>(200, 250), 0.5f));
			attacks.add(new Attack("Bleeding cut", 20, new Range<>(300), 0.8f));

			return attacks;
		}
	}

	public static class						EnchantedDagger extends AbstractDagger
	{
		public								EnchantedDagger()
		{
			super("Enchanted dagger");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Blow", 100, new Range<>(800, 1000), 0.9f));

			return attacks;
		}
	}

// ---------------------------------------> Stuffs

	private static abstract class			AbstractStuff extends Abstract
	{
		public								AbstractStuff(String name)
		{
			super(name);
		}

		@Override
		public WeaponsCategories.Abstract	getCategory()
		{
			return new WeaponsCategories.Stuff();
		}

	}

	public static class						BrokenStuff extends AbstractStuff
	{
		public								BrokenStuff()
		{
			super("Broken stuff");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Magic arrow", 85, new Range<>(25)));

			return attacks;
		}
	}

	public static class						StudentStuff extends AbstractStuff
	{
		public								StudentStuff()
		{
			super("Student stuff");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Magic arrow", 85, new Range<>(30)));
			attacks.add(new Attack("Heavy magic arrow", 15, new Range<>(50)));

			return attacks;
		}
	}

	public static class						MasterStuff extends AbstractStuff
	{
		public								MasterStuff()
		{
			super("Master stuff");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Magic arrow", 70, new Range<>(50)));
			attacks.add(new Attack("Heavy magic arrow", 15, new Range<>(80)));
			attacks.add(new Attack("Magic spear", 15, new Range<>(160)));

			return attacks;
		}
	}

	public static class						RoyalMageStuff extends AbstractStuff
	{
		public								RoyalMageStuff()
		{
			super("Royal mage stuff");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Magic arrow", 25, new Range<>(100)));
			attacks.add(new Attack("Heavy magic arrow", 25, new Range<>(150)));
			attacks.add(new Attack("Magic spear", 25, new Range<>(200)));
			attacks.add(new Attack("Heavy magic spear", 15, new Range<>(250)));
			attacks.add(new Attack("Cristal magic spear", 10, new Range<>(750)));

			return attacks;
		}
	}

	public static class						CursedStuff extends AbstractStuff
	{
		public								CursedStuff()
		{
			super("Cursed stuff");
		}

		@Override
		public List<Attack>					getAttacks()
		{
			List<Attack>					attacks;

			attacks = new LinkedList<>();
			attacks.add(new Attack("Dark storm", 50, new Range<>(500), 0.4f));
			attacks.add(new Attack("Dark burst", 50, new Range<>(1500), 0.8f));

			return attacks;
		}
	}
}
