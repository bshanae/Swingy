package model.closed.managers.generators;

import application.utils.Point;
import application.patterns.SingletonMap;
import model.closed.managers.Map;
import model.closed.managers.Session;
import model.closed.objects.creatures.Creature;
import model.closed.objects.creatures.enemies.Enemy;
import model.closed.objects.creatures.hero.Hero;

public class							MapGenerator
{
// -----------------------------------> Constants

	private static final float			ENEMY_DENSITY = 0.4f;
	private static final int			LIMIT_OF_ATTEMPTS_TO_GENERATE_POSITION = 100;

// -----------------------------------> Attributes

	private Map							map;

// -----------------------------------> Public methods

	public static MapGenerator			getInstance()
	{
		return SingletonMap.getInstanceOf(MapGenerator.class);
	}

	public Map							generate()
	{
		Hero							hero;
		int								level;

		int								numberOfEnemies;
		Point							position;
		Enemy							enemy;

		hero = Session.getHero();
		level = Session.getLevel();

		map = new Map(new Point(getSizeForLevel(level)));

		hero.setPosition(map.getSize().subtract(new Point(1)).divide(2));
		map.getCreatures().add(hero);

		numberOfEnemies = getNumberOfEnemies();
		for (int i = 0; i < numberOfEnemies; i++)
		{
			enemy = EnemyGenerator.generate();
			position = tryGetUniqueRandomPosition();

			if (position != null)
			{
				enemy.setPosition(position);
				map.getCreatures().add(enemy);
			}
		}

		return map;
	}

// -----------------------------------> Private methods

	private static int					getSizeForLevel(int level)
	{
		return (level - 1 ) * 5 + 10 - (level % 2);
	}

	private int							getNumberOfEnemies()
	{
		return (int)((float)map.getSize().x * ENEMY_DENSITY * (float)map.getSize().y * ENEMY_DENSITY);
	}

	private Point						tryGetUniqueRandomPosition()
	{
		Point							position;

		for (int i = 0; i < LIMIT_OF_ATTEMPTS_TO_GENERATE_POSITION; i++)
		{
			position = getRandomPosition();

			if (isPositionUnique(position))
				return position;
		}

		return null;
	}

	private Point						getRandomPosition()
	{
		return Point.random(new Point(0), map.getSize().subtract(new Point(1)));
	}

	private boolean						isPositionUnique(Point position)
	{
		for (Creature creature : map.getCreatures())
		{
			if (position.equals(creature.getPosition()))
				return false;
		}

		return true;
	}
}
