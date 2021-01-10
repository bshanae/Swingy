package swingy.model.closed.artefacts.artefact;

import lombok.Getter;
import swingy.model.closed.creatures.hero.HeroClass;

public abstract class		Artefact
{
// ----------------------->	Attributes

	@Getter
	private final String	name;

	@Getter
	private final int		level;

	@Getter
	private final int		supportedClassesFlags;

// ----------------------->	Constructors

	protected 				Artefact(String name, int level, int supportedClassesFlags)
	{
		this.name = name;
		this.level = level;
		this.supportedClassesFlags = supportedClassesFlags;
	}

	protected 				Artefact(String name, int level)
	{
		this.name = name;
		this.level = level;

		this.supportedClassesFlags =
			HeroClass.WARRIOR.toFlag() |
			HeroClass.SWORDSMAN.toFlag() |
			HeroClass.ASSASSIN.toFlag() |
			HeroClass.MAGE.toFlag();
	}

// ----------------------->	Implementations

	@Override
	public boolean			equals(Object other)
	{
		return other instanceof Artefact && name.equals(((Artefact)other).name);
	}
}
