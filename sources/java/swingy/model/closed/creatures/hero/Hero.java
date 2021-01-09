package swingy.model.closed.creatures.hero;

import swingy.application.service.Debug;
import swingy.application.service.LogGroup;
import lombok.Getter;
import swingy.model.closed.artefacts.armor.Armor;
import swingy.model.closed.artefacts.helm.Helm;
import swingy.model.closed.artefacts.weapon.Weapon;
import swingy.model.closed.battle.Attack;
import swingy.model.closed.creatures.Creature;
import swingy.model.closed.creatures.hero.template.HeroTemplate;
import swingy.model.closed.creatures.hero.template.HeroTemplateStorage;

import java.util.List;

public class							Hero extends Creature
{
// -----------------------------------> Attributes

	@Getter
	private final HeroClass				heroClass;

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
		if (inventory.getHelm() != null)
			return inventory.getHelm().getHealthGain();

		return 0;
	}

	@Override
	public int							getDefense()
	{
		if (inventory.getArmor() != null)
			return inventory.getArmor().getDefenseGain();

		return 0;
	}

	@Override
	public List<Attack>					getAttacks()
	{
		if (inventory.getWeapon() == null)
			throw new RuntimeException("Can't get attacks, because weapon is not set");

		return inventory.getWeapon().getAttacks();
	}

	public boolean						didFinishGame()
	{
		return level >= 8;
	}

// -----------------------------------> Constructors

	public static Hero					create(String name, HeroTemplate template)
	{
		Hero							hero;

		hero = new Hero(name, template.getHeroClass());

		hero.inventory.setHelm((Helm)template.getHelm().get());
		hero.inventory.setArmor((Armor)template.getArmor().get());
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
		Hero							hero;

		hero = new Hero(name, heroClass);

		hero.inventory.setHelm(helm);
		hero.inventory.setArmor(armor);
		hero.inventory.setWeapon(weapon);

		hero.level = level;
		hero.experience = experience;

		logRestoringHero(name, heroClass, level, experience, helm, armor, weapon);

		return hero;
	}

	private								Hero(String name, HeroClass heroClass)
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
		logGainingExperience(value);
		this.experience += value;
		logCurrentExperience();

		updateLevel();
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

			logUpgradingLevel();
			logCurrentExperience();
		}
	}

	private int							calculateExperienceForNextLevel()
	{
		final int						nextLevel = level + 1;

		return nextLevel * 1000 + (nextLevel - 1) * (nextLevel - 1) * 450;
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

	private void		 				logGainingExperience(int value)
	{
		Debug.logFormat(LogGroup.GAME, "[Model/Hero] Hero gained %d experience", value);
	}

	private void 						logUpgradingLevel()
	{
		Debug.logFormat(LogGroup.GAME, "[Model/Hero] Hero upgraded to level %d", level);
	}

	private void 						logCurrentExperience()
	{
		Debug.logFormat(LogGroup.GAME, "[Model/Hero] Hero has %d experience", experience);
	}
}