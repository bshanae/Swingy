package swingy.model.closed.creatures.hero;

import swingy.application.service.Debug;
import swingy.application.service.LogGroup;
import lombok.Getter;
import swingy.model.closed.artefacts.armor.Armor;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.artefacts.helm.Helm;
import swingy.model.closed.artefacts.weapon.Weapon;

public class								HeroInventory
{
// -----------------------> Attributes

	@Getter
	private Helm			helm;

	@Getter
	private Armor			armor;

	@Getter
	private Weapon			weapon;

// -----------------------> Properties

	public void				setHelm(Helm helm)
	{
		logSettingHelm(helm);
		this.helm = helm;
	}

	public void				setArmor(Armor armor)
	{
		logSettingArmor(armor);
		this.armor = armor;
	}

	public void				setWeapon(Weapon weapon)
	{
		logSettingWeapon(weapon);
		this.weapon = weapon;
	}

// -----------------------> Public methods

	public void				setArtefact(Artefact artefact)
	{
		if (artefact instanceof Helm)
			setHelm((Helm)artefact);
		else if (artefact instanceof Armor)
			setArmor((Armor)artefact);
		else if (artefact instanceof Weapon)
			setWeapon((Weapon)artefact);
	}

	public boolean			has(Artefact artefact)
	{
		return helm.equals(artefact) || armor.equals(artefact) || weapon.equals(artefact);
	}

// -----------------------> Private methods

	private static void		logSettingHelm(Helm helm)
	{
		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/HeroInventory] Setting helm to '%s'",
			helm != null ? helm.getName() : ""
		);
	}

	private static void		logSettingArmor(Armor armor)
	{
		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/HeroInventory] Setting armor to '%s'",
			armor != null ? armor.getName() : ""
		);
	}

	private static void		logSettingWeapon(Weapon weapon)
	{
		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/HeroInventory] Setting weapon to '%s'",
			weapon != null ? weapon.getName() : ""
		);
	}
}
