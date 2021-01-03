package model.closed.managers.meta;

import model.closed.objects.artefacts.Artefact;
import model.closed.objects.artefacts.WeaponsCategories;
import model.closed.objects.creatures.hero.HeroClasses;

public class								ArtefactMetas
{
// ---------------------------------------> Interface

	public static abstract class			Abstract
	{
		private final float					dropProbability;

		public								Abstract(float dropProbability)
		{
			this.dropProbability = dropProbability;
		}

		public final float					getDropProbability()
		{
			return dropProbability;
		}

		public abstract
		SupportedHeroClasses				getSupportedHeroClasses();

		public abstract Artefact			getInstance();
	}

// ---------------------------------------> Helms

	public static class						OldHelm extends Abstract
	{
		public								OldHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.OldHelm();
		}
	}

	public static class						LeatherHelm extends Abstract
	{
		public								LeatherHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.LeatherHelm();
		}
	}

	public static class						SteelHelm extends Abstract
	{
		public								SteelHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.SteelHelm();
		}
	}

	public static class						SoldierHelm extends Abstract
	{
		public								SoldierHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.SoldierHelm();
		}
	}

	public static class						KnightHelm extends Abstract
	{
		public								KnightHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.KnightHelm();
		}
	}

	public static class						LegendaryHelm extends Abstract
	{
		public								LegendaryHelm(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Helms.LegendaryHelm();
		}
	}


// ---------------------------------------> Armors

	public static class						OldArmor extends Abstract
	{
		public								OldArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.OldArmor();
		}
	}

	public static class						LeatherArmor extends Abstract
	{
		public								LeatherArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.LeatherArmor();
		}
	}

	public static class						ChainArmor extends Abstract
	{
		public								ChainArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.ChainArmor();
		}
	}

	public static class						SoldierArmor extends Abstract
	{
		public								SoldierArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.SoldierArmor();
		}
	}

	public static class						KnightArmor extends Abstract
	{
		public								KnightArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.KnightArmor();
		}
	}

	public static class						LegendaryArmor extends Abstract
	{
		public								LegendaryArmor(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ANY);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Armors.LegendaryArmor();
		}
	}

// ---------------------------------------> Weapons : Hammers

	private static abstract class			AbstractHammer extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractHammer(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Hammer();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						Hammer extends AbstractHammer
	{
		public								Hammer(float dropProbability)
		{
			super(dropProbability);
		}


		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.Hammer();
		}
	}

	public static class						GreatHammer extends AbstractHammer
	{
		public								GreatHammer(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.GreatHammer();
		}
	}

	public static class						KnightGreatHammer extends AbstractHammer
	{
		public								KnightGreatHammer(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.KnightGreatHammer();
		}
	}

// ---------------------------------------> Weapons : Swords

	private static abstract class			AbstractSword extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractSword(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Sword();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						OldSword extends AbstractSword
	{
		public								OldSword(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.OldSword();
		}
	}

	public static class						SteelSword extends AbstractSword
	{
		public								SteelSword(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.SteelSword();
		}
	}

	public static class						SoldierSword extends AbstractSword
	{
		public								SoldierSword(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.SoldierSword();
		}
	}

	public static class						KnightSword extends AbstractSword
	{
		public								KnightSword(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.KnightSword();
		}
	}

// ---------------------------------------> Weapons : Rapiers

	private static abstract class			AbstractRapier extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractRapier(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Sword();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						Rapier extends AbstractRapier
	{
		public								Rapier(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.Rapier();
		}
	}

	public static class						KnightRapier extends AbstractRapier
	{
		public								KnightRapier(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.KnightRapier();
		}
	}

	public static class						LegendaryRapier extends AbstractRapier
	{
		public								LegendaryRapier(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.LegendaryRapier();
		}
	}

// ---------------------------------------> Weapons : Scimitars

	private static abstract class			AbstractScimitar extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractScimitar(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Scimitar();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						OldScimitar extends AbstractScimitar
	{
		public								OldScimitar(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.OldScimitar();
		}
	}

	public static class						SteelScimitar extends AbstractScimitar
	{
		public								SteelScimitar(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.SteelScimitar();
		}
	}

	public static class						BanditScimitar extends AbstractScimitar
	{
		public								BanditScimitar(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.BanditScimitar();
		}
	}

	public static class						LegendaryScimitar extends AbstractScimitar
	{
		public								LegendaryScimitar(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.LegendaryScimitar();
		}
	}

// ---------------------------------------> Weapons : Daggers

	private static abstract class			AbstractDagger extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractDagger(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Dagger();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						OldDagger extends AbstractDagger
	{
		public								OldDagger(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.OldDagger();
		}
	}

	public static class						SteelDagger extends AbstractDagger
	{
		public								SteelDagger(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.SteelDagger();
		}
	}

	public static class						BanditDagger extends AbstractDagger
	{
		public								BanditDagger(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.BanditDagger();
		}
	}

	public static class						EnchantedDagger extends AbstractDagger
	{
		public								EnchantedDagger(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.EnchantedDagger();
		}
	}

// ---------------------------------------> Weapons : Stuffs

	private static abstract class			AbstractStuff extends Abstract
	{
		private final
		WeaponCategoryMetas.Abstract		weaponCategoryMeta;

		public								AbstractStuff(float dropProbability)
		{
			super(dropProbability);
			weaponCategoryMeta = new WeaponCategoryMetas.Stuff();
		}

		@Override
		public SupportedHeroClasses			getSupportedHeroClasses()
		{
			return weaponCategoryMeta.getSupportedHeroClasses();
		}
	}

	public static class						BrokenStuff extends AbstractStuff
	{
		public								BrokenStuff(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.BrokenStuff();
		}
	}

	public static class						StudentStuff extends AbstractStuff
	{
		public								StudentStuff(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.StudentStuff();
		}
	}

	public static class						MasterStuff extends AbstractStuff
	{
		public								MasterStuff(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.MasterStuff();
		}
	}

	public static class						RoyalMageStuff extends AbstractStuff
	{
		public								RoyalMageStuff(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.RoyalMageStuff();
		}
	}

	public static class						CursedStuff extends AbstractStuff
	{
		public								CursedStuff(float dropProbability)
		{
			super(dropProbability);
		}

		@Override
		public Artefact						getInstance()
		{
			return new model.closed.objects.artefacts.Weapons.CursedStuff();
		}
	}
}
