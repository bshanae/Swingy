package model.closed.managers;

import model.closed.objects.creatures.hero.Hero;

public class				Session
{
	private static Hero		hero;
	private static Map		map;

	public static Hero		getHero()
	{
		return hero;
	}

	public static void		setHero(Hero hero)
	{
		Session.hero = hero;
	}

	public static int		getLevel()
	{
		return hero.getLevel();
	}

	public static Map		getMap()
	{
		return map;
	}

	public static void		setMap(Map map)
	{
		Session.map = map;
	}
}
