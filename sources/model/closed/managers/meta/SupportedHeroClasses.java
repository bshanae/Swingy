package model.closed.managers.meta;


import application.service.Exceptions;
import model.closed.objects.creatures.hero.HeroClasses;

public class				SupportedHeroClasses
{
	public static final int	WARRIOR = 0b0001;
	public static final int	SWORDSMAN = 0b0010;
	public static final int	ASSASSIN = 0b0100;
	public static final int	MAGE = 0b1000;
	public static final int	ANY = 0b1111;

	private final int		bitflags;

	public					SupportedHeroClasses(int bitflags)
	{
		this.bitflags = bitflags;
	}

	public static int		getBitflagFromClass(HeroClasses.Abstract heroClass)
	{
		if (heroClass instanceof HeroClasses.Warrior)
			return WARRIOR;
		if (heroClass instanceof HeroClasses.Swordsman)
			return SWORDSMAN;
		if (heroClass instanceof HeroClasses.Assassin)
			return ASSASSIN;
		if (heroClass instanceof HeroClasses.Mage)
			return MAGE;

		throw new Exceptions.UnexpectedCodeBranch();
	}

	public boolean			hasBitflag(int bitflag)
	{
		return (bitflags & bitflag) == bitflag;
	}
}
