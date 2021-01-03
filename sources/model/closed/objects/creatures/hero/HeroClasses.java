package model.closed.objects.creatures.hero;

import application.patterns.SingletonMap;
import model.closed.objects.artefacts.Weapons;

public class								HeroClasses
{
	public static abstract class			Abstract
	{
		public abstract String				getName();

		public abstract int					getBaseHealth();

		public abstract int					getBaseDefense();

		public abstract Weapons.Abstract	getBaseWeapon();

		public Hero							createHero(String name)
		{
			Hero							hero;

			hero = new Hero(name, this);
			hero.getInventory().setWeapon(getBaseWeapon());

			return hero;
		}
	}

	public static class						Warrior extends Abstract
	{
		public static Warrior				getInstance()
		{
			return SingletonMap.getInstanceOf(Warrior.class);
		}

		@Override
		public String						getName()
		{
			return "Warrior";
		}

		@Override
		public int							getBaseHealth()
		{
			return 30;
		}

		public int							getBaseDefense()
		{
			return 40;
		}

		public Weapons.Abstract				getBaseWeapon()
		{
			return new Weapons.OldSword();
		}
	}

	public static class						Swordsman extends Abstract
	{
		public static Swordsman				getInstance()
		{
			return SingletonMap.getInstanceOf(Swordsman.class);
		}

		@Override
		public String						getName()
		{
			return "Swordsman";
		}

		@Override
		public int							getBaseHealth()
		{
			return 40;
		}

		public int							getBaseDefense()
		{
			return 30;
		}

		public Weapons.Abstract				getBaseWeapon()
		{
			return new Weapons.OldSword();
		}
	}

	public static class						Assassin extends Abstract
	{
		public static Assassin				getInstance()
		{
			return SingletonMap.getInstanceOf(Assassin.class);
		}

		@Override
		public String						getName()
		{
			return "Assassin";
		}

		@Override
		public int							getBaseHealth()
		{
			return 30;
		}

		public int							getBaseDefense()
		{
			return 10;
		}

		public Weapons.Abstract				getBaseWeapon()
		{
			return new Weapons.OldSword();
		}
	}

	public static class						Mage extends Abstract
	{
		public static Mage					getInstance()
		{
			return SingletonMap.getInstanceOf(Mage.class);
		}

		@Override
		public String						getName()
		{
			return "Mage";
		}

		@Override
		public int							getBaseHealth()
		{
			return 25;
		}

		public int							getBaseDefense()
		{
			return 5;
		}

		public Weapons.Abstract				getBaseWeapon()
		{
			return new Weapons.OldSword();
		}
	}
}
