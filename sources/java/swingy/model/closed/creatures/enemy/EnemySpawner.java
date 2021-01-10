package swingy.model.closed.creatures.enemy;

import swingy.application.service.Debug;
import swingy.application.service.Exceptions;
import swingy.application.service.LogGroup;
import swingy.application.utils.RandomGenerator;
import swingy.model.closed.Session;

import java.util.LinkedList;
import java.util.List;

public abstract class				EnemySpawner
{
// -------------------------------> Constants

	private static final int		NUMBER_OF_ATTEMPTS_TO_SELECT_ENEMY = 100;

// -------------------------------> Public methods

	public static Enemy				spawn()
	{
		List<Enemy>					possibleEnemies;
		Enemy						selectedEnemy;

		possibleEnemies = generateEnemiesForCurrentLevel();
		selectedEnemy = selectRandomEnemy(possibleEnemies);

		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/EnemyGenerator] Spawning enemy '%s'", selectedEnemy.getName()
		);

		return selectedEnemy.clone();
	}

// -------------------------------> Private methods

	private static List<Enemy>		generateEnemiesForCurrentLevel()
	{
		List<Enemy>					list;

		list = new LinkedList<>();
		for (Enemy enemy : EnemyStorage.getInstance())
		{
			if (enemy.getLevel() <= Session.getInstance().getHero().getLevel())
				list.add(enemy);
		}

		return list;
	}

	private static Enemy			selectRandomEnemy(List<Enemy> list)
	{
		int							selectedIndex;
		Enemy						selectedMeta;

		float						appearanceProbability;

		for (int i = 0; i < NUMBER_OF_ATTEMPTS_TO_SELECT_ENEMY; i++)
		{
			selectedIndex = RandomGenerator.randomBetween(0, list.size() - 1);
			selectedMeta = list.get(selectedIndex);

			appearanceProbability = getTransformedAppearanceProbability(selectedMeta);

			if (RandomGenerator.randomWithProbability(appearanceProbability))
				return selectedMeta;
		}

		throw new Exceptions.UnexpectedCodeBranch();
	}

	private static float			getTransformedAppearanceProbability(Enemy enemy)
	{
		int							currentLevel;
		int							appearanceLevel;
		int							levelDelta;
		float						spawnChance;

		currentLevel = Session.getInstance().getHero().getLevel();
		appearanceLevel = enemy.getLevel();
		levelDelta = currentLevel - appearanceLevel;

		spawnChance = 1.f;

		if (levelDelta > 0)
		{
			spawnChance = 1.f - 0.1f * levelDelta * levelDelta;
			spawnChance = Math.max(0.f, spawnChance);
		}

		return spawnChance;
	}
}
