package model.closed.objects.artefacts;

public abstract class				Helms
{
	public static abstract class	Abstract extends Artefact
	{
		private final int			healthGain;

		public 						Abstract(String name, int healthGain)
		{
			super(name);
			this.healthGain = healthGain;
		}

		public int					getHealthGain()
		{
			return healthGain;
		}
	}

	public static class				OldHelm extends Abstract
	{
		public						OldHelm()
		{
			super("Old helm", 15);
		}
	}

	public static class				LeatherHelm extends Abstract
	{
		public						LeatherHelm()
		{
			super("Leather helm", 50);
		}
	}

	public static class				SteelHelm extends Abstract
	{
		public						SteelHelm()
		{
			super("Steel helm", 140);
		}
	}

	public static class				SoldierHelm extends Abstract
	{
		public						SoldierHelm()
		{
			super("Soldier helm", 250);
		}
	}

	public static class				KnightHelm extends Abstract
	{
		public						KnightHelm()
		{
			super("Knight helm", 350);
		}
	}

	public static class				LegendaryHelm extends Abstract
	{
		public						LegendaryHelm()
		{
			super("Legendary helm", 800);
		}
	}
}
