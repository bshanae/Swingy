package swingy.model.closed.artefacts.artefact;

import lombok.Getter;
import swingy.model.closed.creatures.hero.HeroClass;

public abstract class		Artefact
{
	@Getter
	private final String	name;

	@Getter
	private final int		supportedClassesFlags;

	protected 				Artefact(String name, int supportedClassesFlags)
	{
		this.name = name;
		this.supportedClassesFlags = supportedClassesFlags;
	}

	protected 				Artefact(String name)
	{
		this.name = name;

		this.supportedClassesFlags =
			HeroClass.WARRIOR.toFlag() |
			HeroClass.SWORDSMAN.toFlag() |
			HeroClass.ASSASSIN.toFlag() |
			HeroClass.MAGE.toFlag();
	}
}
