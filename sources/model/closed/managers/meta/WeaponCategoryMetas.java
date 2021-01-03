package model.closed.managers.meta;

public class						WeaponCategoryMetas
{
	public static abstract class	Abstract
	{
		public abstract
		SupportedHeroClasses		getSupportedHeroClasses();
	}

	public static class				Hammer extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.WARRIOR);
		}
	}

	public static class				Sword extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses
			(
				SupportedHeroClasses.WARRIOR |
				SupportedHeroClasses.SWORDSMAN |
				SupportedHeroClasses.ASSASSIN
			);
		}
	}

	public static class				Rapier extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.SWORDSMAN);
		}
	}

	public static class				Scimitar extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.SWORDSMAN | SupportedHeroClasses.ASSASSIN);
		}
	}

	public static class				Dagger extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.ASSASSIN);
		}
	}

	public static class				Stuff extends Abstract
	{
		@Override
		public SupportedHeroClasses	getSupportedHeroClasses()
		{
			return new SupportedHeroClasses(SupportedHeroClasses.MAGE);
		}
	}

}
