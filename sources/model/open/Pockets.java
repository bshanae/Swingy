package model.open;

import application.utils.Point;
import lombok.Getter;
import model.closed.battle.BattleLogger;

import java.util.*;

public abstract class					Pockets
{
	public interface					Abstract {}

	public static class					Artefact implements Abstract
	{
		@Getter
		private final String			name;

		public							Artefact(model.closed.artefacts.artefact.Artefact artefact)
		{
			name = artefact.getName();
		}
	}

	public static class					Creature implements Abstract
	{
		@Getter
		private final String			name;

		@Getter
		private final Point				position;

		public							Creature(model.closed.creatures.Creature creature)
		{
			this.name = creature.getName();
			this.position = creature.getPosition();
		}
	}

	public static class					Enemy extends Creature
	{
		public 							Enemy(model.closed.creatures.enemy.Enemy enemy)
		{
			super(enemy);
		}
	}

	public static class					Hero extends Creature
	{
		@Getter
		private final String			heroClass;

		@Getter
		private final int				level;

		@Getter
		private final int				experience;

		public 							Hero(model.closed.creatures.hero.Hero hero)
		{
			super(hero);

			heroClass = hero.getHeroClass().toString();
			level = hero.getLevel();
			experience = hero.getExperience();
		}
	}

	public static class					HeroInventory implements Abstract
	{
		@Getter
		private final Artefact			helm;

		@Getter
		private final Artefact			armor;

		@Getter
		private final Artefact			weapon;

		public 							HeroInventory(model.closed.creatures.hero.HeroInventory inventory)
		{
			helm = buildArtefact(inventory.getHelm());
			armor = buildArtefact(inventory.getArmor());
			weapon = buildArtefact(inventory.getWeapon());
		}

		private Artefact				buildArtefact(model.closed.artefacts.artefact.Artefact artefact)
		{
			return artefact != null ? new Artefact(artefact) : null;
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