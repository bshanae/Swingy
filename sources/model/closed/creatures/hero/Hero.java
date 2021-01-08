package model.closed.creatures.hero;

import application.service.Debug;
import application.service.LogGroup;
import lombok.Getter;
import model.closed.artefacts.armor.Armor;
import model.closed.artefacts.helm.Helm;
import model.closed.artefacts.weapon.Weapon;
import model.closed.battle.Attack;
import model.closed.creatures.Creature;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;

import java.util.LinkedList;
import java.util.List;

public class							Hero extends Creature
{
// -----------------------------------> Attributes

	@Getter
	private final HeroClass				heroClass;

	private final int					baseHealth;
	private final int					baseDefense;

	@Getter
	HeroInventory						inventory;
	int									level;
	@Getter
	int									experience;

// -----------------------------------> Properties

	@Override
	public int							getLevel()
	{
		return level;
	}

	@Override
	public int							getFullHealth()
	{
		return baseHealth + calculateHealthGainViaLevel() + calculateHealthGainViaHelm();
	}

	@Override
	public int							getDefense()
	{
		return baseDefense + calculateDefenseGainViaLevel() + calculateDefenseGainViaArmor();
	}

	@Override
	public List<Attack>					getAttacks()
	{
		return transformAttacks(getWeaponAttacks(), calculateAttackGainViaLevel());
	}

	public boolean						didFinishGame()
	{
		return level >= 8;
	}

// -----------------------------------> Constructors

	public static Hero					create(HeroTemplate template, String name)
	{
		Hero							hero;

		hero = new Hero(name, template.getHeroClass(), template.getHealth(), template.getDefense());
		hero.inventory.setWeapon((Weapon)template.getWeapon().get());

		logCreatingHero(name, template.getHeroClass());

		return hero;
	}

	public static Hero					restore
										(
											String name,
											HeroClass heroClass,
											int level,
											int experience,
											Helm helm,
											Armor armor,
											Weapon weapon
										)
	{
		HeroTemplate					template;
		Hero							hero;

		template = HeroTemplateStorage.getInstance().find(heroClass);
		hero = new Hero(name, heroClass, template.getHealth(), template.getDefense());

		hero.inventory.setHelm(helm);
		hero.inventory.setArmor(armor);
		hero.inventory.setWeapon(weapon);

		hero.level = level;
		hero.experience = experience;

		logRestoringHero(name, heroClass, level, experience, helm, armor, weapon);

		return hero;
	}

	private								Hero
										(
											String name,
											HeroClass heroClass,
											int baseHealth,
											int baseDefense
										)
	{

		super(name);

		this.heroClass = heroClass;
		this.baseHealth = baseHealth;
		this.baseDefense = baseDefense;

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

// -----------------------------------> Private methods : Calculations

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

	private List<Attack>				transformAttacks(List<Attack> rawAttacks, int gain)
	{
		List<Attack>					attacks;

		attacks = new LinkedList<>();
		for (Attack rawAttack : rawAttacks)
			attacks.add(rawAttack.applyGain(gain));

		return attacks;
	}

	private int							calculateAttackGainViaLevel()
	{
		return 10 * (level + 1);
	}

// -----------------------------------> Private methods : Leveling

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

	private int							calculateExperienceForNextLevel()
	{
		return level * 1000 + (level - 1) * (level - 1) * 450;
	}

// -----------------------------------> Private methods : Logging

	private static void					logCreatingHero(String name, HeroClass heroClass)
	{
		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/Hero] Creating new hero '%s' using class '%s'",
			name,
			heroClass.toString()
		);
	}

	private static void 				logRestoringHero
										(
											String name,
											HeroClass heroCLass,
											int level,
											int experience,
											Helm helm,
											Armor armor,
											Weapon weapon
										)
	{
		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/Hero] Restoring hero '%s' from data : class = '%s', level = %d, experience = %d, helm = %s, armor = %s, weapon = %s",
			name,
			heroCLass.toString(),
			level,
			experience,
			helm != null ? helm.getName() : "",
			armor != null ? armor.getName() : "",
			weapon != null ? weapon.getName() : ""
		);
	}
}