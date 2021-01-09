package swingy.model.open;

import swingy.application.utils.Point;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.battle.BattleLogger;
import swingy.model.closed.creatures.Creature;
import swingy.model.closed.creatures.enemy.Enemy;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.HeroInventory;
import swingy.model.closed.map.Map;

import java.util.*;

public abstract class					Pockets
{
	public interface					Abstract {}

	public static class					Artefact implements Abstract
	{
		@Getter
		private final String			name;

		public							Artefact(swingy.model.closed.artefacts.artefact.Artefact artefact)
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

		public							Creature(swingy.model.closed.creatures.Creature creature)
		{
			this.name = creature.getName();
			this.position = creature.getPosition();
		}
	}

	public static class					Enemy extends Creature
	{
		public 							Enemy(swingy.model.closed.creatures.enemy.Enemy enemy)
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

		private final boolean			didFinishGame;

		public 							Hero(swingy.model.closed.creatures.hero.Hero hero)
		{
			super(hero);

			heroClass = hero.getHeroClass().toString();
			level = hero.getLevel();
			experience = hero.getExperience();
			didFinishGame = hero.didFinishGame();
		}

		public boolean					didFinishGame()
		{
			return didFinishGame;
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

		public 							HeroInventory(swingy.model.closed.creatures.hero.HeroInventory inventory)
		{
			helm = buildArtefact(inventory.getHelm());
			armor = buildArtefact(inventory.getArmor());
			weapon = buildArtefact(inventory.getWeapon());
		}

		private Artefact				buildArtefact(swingy.model.closed.artefacts.artefact.Artefact artefact)
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

		public							Map(swingy.model.closed.map.Map map)
		{
			size = map.getSize();
			creatures = new LinkedList<>();

			for (swingy.model.closed.creatures.Creature creature : map.getCreatures())
			{
				if (creature instanceof swingy.model.closed.creatures.hero.Hero)
					creatures.add(new Hero((swingy.model.closed.creatures.hero.Hero)(creature)));
				else if (creature instanceof swingy.model.closed.creatures.enemy.Enemy)
					creatures.add(new Enemy((swingy.model.closed.creatures.enemy.Enemy)(creature)));
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