package model.closed.objects.creatures.hero;

import application.service.Debug;
import application.service.LogGroup;
import model.closed.objects.artefacts.Armors;
import model.closed.objects.artefacts.Artefact;
import model.closed.objects.artefacts.Helms;
import model.closed.objects.artefacts.Weapons;

public class								HeroInventory
{
// ---------------------------------------> Attributes

	private Helms.Abstract					helm;
	private Armors.Abstract					armor;
	private Weapons.Abstract				weapon;

// ---------------------------------------> Properties

	public Helms.Abstract					getHelm()
	{
		return helm;
	}

	public void								setHelm(Helms.Abstract helm)
	{
		Debug.logFormat(LogGroup.GAME, "[Model/HeroInventory] Setting weapon to '%s'", helm.getName());
		this.helm = helm;
	}

	public Armors.Abstract					getArmor()
	{
		return armor;
	}

	public void								setArmor(Armors.Abstract armor)
	{
		Debug.logFormat(LogGroup.GAME, "[Model/HeroInventory] Setting armor to '%s'", armor.getName());
		this.armor = armor;
	}

	public Weapons.Abstract					getWeapon()
	{
		return weapon;
	}

	public void								setWeapon(Weapons.Abstract weapon)
	{
		Debug.logFormat(LogGroup.GAME, "[Model/HeroInventory] Setting weapon to '%s'", weapon.getName());
		this.weapon = weapon;
	}

// ---------------------------------------> Public methods

	public void								setArtefact(Artefact artefact)
	{
		if (artefact instanceof Helms.Abstract)
			setHelm((Helms.Abstract)artefact);
		else if (artefact instanceof Armors.Abstract)
			setArmor((Armors.Abstract)artefact);
		else if (artefact instanceof Weapons.Abstract)
			setWeapon((Weapons.Abstract)artefact);
	}
}
