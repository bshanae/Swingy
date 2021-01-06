package model.closed;

import model.closed.creatures.hero.Hero;
import model.closed.map.Map;

public class				Session
{
	private static Hero		hero;
	private static Map map;

	public static Hero		getHero()
	{
		return hero;
	}

	public static void		setHero(Hero hero)
	{
		Session.hero = hero;
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
