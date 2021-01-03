package model.open;

import application.utils.Point;
import model.closed.managers.battle.BattleLogger;

import java.util.*;

public abstract class					Pockets
{
	private static final
	HashMap<Class<?>, Class<?>>			objectToPocket = new HashMap<Class<?>, Class<?>>()
	{{
		put(model.closed.objects.creatures.enemies.Enemy.class, Enemy.class);
		put(model.closed.objects.creatures.hero.Hero.class, Hero.class);
	}};

	public static Abstract				build(Object object)
	{
		final Class<?>					objectClass = object.getClass();

		try
		{
			assert objectToPocket.containsKey(objectClass);
			return (Abstract)objectToPocket.get(objectClass).getConstructor(objectClass).newInstance(object);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			assert false;
			return null;
		}
	}

	public interface					Abstract {}

	public static class					Creature implements Abstract
	{
		public final Point				position;

		public							Creature(Point position)
		{
			this.position = position;
		}
	}

	public static class					Enemy extends Creature
	{
		public 							Enemy(model.closed.objects.creatures.enemies.Enemy enemy)
		{
			super(enemy.getPosition());
		}
	}

	public static class					Hero extends Creature
	{
		public final String				name;

		public 							Hero(model.closed.objects.creatures.hero.Hero hero)
		{
			super(hero.getPosition());
			this.name = hero.getName();
		}
	}

	public static class					Map implements Abstract
	{
		public final Point				size;
		public final List<Creature>		creatures;

		public							Map(model.closed.managers.Map map)
		{
			size = map.getSize();
			creatures = new LinkedList<>();

			for (model.closed.objects.creatures.Creature creature : map.getCreatures())
			{
				if (creature instanceof model.closed.objects.creatures.hero.Hero)
					creatures.add(new Hero((model.closed.objects.creatures.hero.Hero)(creature)));
				else if (creature instanceof model.closed.objects.creatures.enemies.Enemy)
					creatures.add(new Enemy((model.closed.objects.creatures.enemies.Enemy)(creature)));
			}
		}
	}

	public static class					BattleLog implements Abstract
	{
		public final List<String>		lines;

		public							BattleLog(BattleLogger logger)
		{
			lines = logger.getLines();
		}
	}
}