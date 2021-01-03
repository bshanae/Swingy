package model.closed.objects.artefacts;

public abstract class				Armors
{
	public static abstract class	Abstract extends Artefact
	{
		private final int			defense;

		public						Abstract(String name, int defense)
		{
			super(name);
			this.defense = defense;
		}

		public int					getDefenseGain()
		{
			return defense;
		}
	}

	public static class				OldArmor extends Abstract
	{
		public						OldArmor()
		{
			super("Old armor", 10);
		}
	}

	public static class				LeatherArmor extends Abstract
	{
		public						LeatherArmor()
		{
			super("Leather armor", 40);
		}
	}

	public static class				ChainArmor extends Abstract
	{
		public						ChainArmor()
		{
			super("Chain armor", 100);
		}
	}

	public static class				SoldierArmor extends Abstract
	{
		public						SoldierArmor()
		{
			super("Soldier armor", 180);
		}
	}

	public static class				KnightArmor extends Abstract
	{
		public						KnightArmor()
		{
			super("Knight armor", 240);
		}
	}

	public static class				LegendaryArmor extends Abstract
	{
		public						LegendaryArmor()
		{
			super("Legendary armor", 400);
		}
	}

}
