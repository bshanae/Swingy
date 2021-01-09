package swingy.model.closed.artefacts.weapon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.creatures.hero.HeroClass;

public enum				WeaponCategory
{
// -------------------> Values

	@JsonProperty("hammer")
	HAMMER(HeroClass.WARRIOR.toFlag()),

	@JsonProperty("sword")
	SWORD(HeroClass.WARRIOR.toFlag() | HeroClass.SWORDSMAN.toFlag()),

	@JsonProperty("rapier")
	RAPIER(HeroClass.SWORDSMAN.toFlag()),

	@JsonProperty("scimitar")
	SCIMITAR(HeroClass.SWORDSMAN.toFlag() | HeroClass.ASSASSIN.toFlag()),

	@JsonProperty("dagger")
	DAGGER(HeroClass.ASSASSIN.toFlag()),

	@JsonProperty("stuff")
	STUFF(HeroClass.MAGE.toFlag());

// -------------------> Attributes

	@Getter
	private final int	supportedClassesFlags;

// -------------------> Constructor

	private				WeaponCategory(int supportedClassesFlags)
	{
		this.supportedClassesFlags = supportedClassesFlags;
	}
}
