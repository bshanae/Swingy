package model.closed.managers.heroStorage;

import application.patterns.SingletonMap;
import model.closed.objects.creatures.hero.Hero;
import model.closed.objects.creatures.hero.HeroClasses;

import java.util.LinkedList;
import java.util.List;

public class					HeroStorage
{
	public static class			HeroNotFoundException extends Exception {}

	private LinkedList<Hero>	heroes;

	public static HeroStorage	getInstance()
	{
		return SingletonMap.getInstanceOf(HeroStorage.class);
	}

	public List<Hero>			getCopy()
	{
		checkIfLoaded();
		return (List<Hero>)heroes.clone();
	}

	public						HeroStorage() {}

	public void					download()
	{
		heroes = new LinkedList<>();
		heroes.add(new HeroClasses.Assassin().createHero("Petr"));
	}

	public void					upload()
	{
		heroes = null;
	}

	public Hero					get(int index)
	{
		checkIfLoaded();
		return heroes.get(index);
	}

	public void					add(Hero hero)
	{
		checkIfLoaded();
		heroes.add(hero);
	}

	public void					delete(int index)
	{
		checkIfLoaded();
		heroes.remove(index);
	}

	public void					delete(Hero hero)
	{
		checkIfLoaded();
		heroes.remove(hero);
	}

	public Hero					find(String name) throws HeroNotFoundException
	{
		for (Hero hero : heroes)
		{
			if (hero.getName().equalsIgnoreCase(name))
				return hero;
		}

		throw new HeroNotFoundException();
	}

	private void				checkIfLoaded()
	{
		assert heroes != null;
	}
}
