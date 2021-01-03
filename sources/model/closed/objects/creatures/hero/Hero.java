package model.closed.objects.creatures.hero;

import application.service.Debug;
import application.service.LogGroup;
import model.closed.objects.Attack;
import model.closed.objects.creatures.Creature;

import java.util.LinkedList;
import java.util.List;

public class							Hero extends Creature
{
// -----------------------------------> Attributes

	private final HeroClasses.Abstract	heroClass;
	private final HeroInventory			inventory;

	private int							level;
	private int							experience;

// -----------------------------------> Properties

	@Override
	public int							getLevel()
	{
		return level;
	}

	@Override
	public int							getBaseHealth()
	{
		return heroClass.getBaseHealth() + calculateHealthGainViaLevel() + calculateHealthGainViaHelm();
	}

	@Override
	public int							getDefense()
	{
		return heroClass.getBaseDefense() + calculateDefenseGainViaLevel() + calculateDefenseGainViaArmor();
	}

	@Override
	public List<Attack>					getAttacks()
	{
		return transformAttacks(getWeaponAttacks(), calculateAttackGainViaLevel());
	}

	public HeroClasses.Abstract			getHeroClass()
	{
		return heroClass;
	}

	public HeroInventory				getInventory()
	{
		return inventory;
	}

// -----------------------------------> Constructor

	public								Hero(String name, HeroClasses.Abstract heroClass)
	{
		super(name);

		this.heroClass = heroClass;
		this.inventory = new HeroInventory();

		this.level = 0;
		this.experience = 0;
	}

// -----------------------------------> Public methods

	public void							addExperience(int value)
	{
		Debug.logFormat(LogGroup.GAME, "[Model/Hero] Hero gained %d experience", value);

		this.experience += value;
		updateLevel();
	}

// -----------------------------------> Private methods

	private int							calculateHealthGainViaLevel()
	{
		return 100 * (level + 1);
	}

	private int							calculateHealthGainViaHelm()
	{
		if (inventory.getHelm() != null)
			return inventory.getHelm().getHealthGain();

		return 0;
	}

	private int							calculateDefenseGainViaLevel()
	{
		return 5 * (level + 1);
	}

	private int							calculateDefenseGainViaArmor()
	{
		if (inventory.getArmor() != null)
			return inventory.getArmor().getDefenseGain();

		return 0;
	}

	private List<Attack>				getWeaponAttacks()
	{
		if (inventory.getWeapon() == null)
			throw new RuntimeException("Can't get attacks, because weapon is not set");

		return inventory.getWeapon().getAttacks();
	}

	private int							calculateAttackGainViaLevel()
	{
		return 10 * (level + 1);
	}

	private int							calculateExperienceForNextLevel()
	{
		return level * 1000 + (level - 1) * (level - 1) * 450;
	}

	private void						updateLevel()
	{
		int								experienceForNextLevel;

		experienceForNextLevel = calculateExperienceForNextLevel();
		if (experience >= experienceForNextLevel)
		{
			experience -= experienceForNextLevel;
			level++;

			Debug.logFormat(LogGroup.GAME, "[Model/Hero] Hero upgraded to level %d", level);
		}
	}

	private List<Attack>				transformAttacks(List<Attack> rawAttacks, int gain)
	{
		List<Attack>					attacks;

		attacks = new LinkedList<>();
		for (Attack rawAttack : rawAttacks)
			attacks.add(rawAttack.applyGain(gain));

		return attacks;
	}
}