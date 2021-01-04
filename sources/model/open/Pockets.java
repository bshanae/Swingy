package model.open;

import application.utils.Point;
import lombok.Getter;
import model.closed.battle.BattleLogger;

import java.util.*;

public abstract class					Pockets
{
	public interface					Abstract {}

	public static class					Creature implements Abstract
	{
		@Getter
		private final Point				position;

		public							Creature(Point position)
		{
			this.position = position;
		}
	}

	public static class					Enemy extends Creature
	{
		public 							Enemy(model.closed.creatures.enemy.Enemy enemy)
		{
			super(enemy.getPosition());
		}
	}

	public static class					Hero extends Creature
	{
		@Getter
		private final String			name;

		public 							Hero(model.closed.creatures.hero.Hero hero)
		{
			super(hero.getPosition());
			this.name = hero.getName();
		}
	}

	public static class					Map implements Abstract
	{
		@Getter
		private final Point				size;

		@Getter
		private final List<Creature>	creatures;

		public							Map(model.closed.map.Map map)
		{
			size = map.getSize();
			creatures = new LinkedList<>();

			for (model.closed.creatures.Creature creature : map.getCreatures())
			{
				if (creature instanceof model.closed.creatures.hero.Hero)
					creatures.add(new Hero((model.closed.creatures.hero.Hero)(creature)));
				else if (creature instanceof model.closed.creatures.enemy.Enemy)
					creatures.add(new Enemy((model.closed.creatures.enemy.Enemy)(creature)));
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