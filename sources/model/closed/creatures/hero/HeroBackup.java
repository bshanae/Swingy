package model.closed.creatures.hero;

public class						HeroBackup
{
// -------------------------------> Attributes

	private final Hero				hero;
	private final HeroInventory		inventory;
	private final int				level;
	private final int				experience;

// -------------------------------> Constructor

	public 							HeroBackup(Hero hero)
	{
		this.hero = hero;

		inventory = copyInventory(hero.inventory);
		level = hero.level;
		experience = hero.experience;
	}

// -------------------------------> Public methods

	public void 					apply()
	{
		hero.inventory = copyInventory(inventory);
		hero.level = level;
		hero.experience = experience;
	}

// -------------------------------> Private methods

	private static HeroInventory	copyInventory(HeroInventory original)
	{
		HeroInventory				copy;

		copy = new HeroInventory();
		copy.setHelm(original.getHelm());
		copy.setArmor(original.getArmor());
		copy.setWeapon(original.getWeapon());

		return copy;
	}
}
