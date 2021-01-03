package model.closed.managers.generators;

import application.service.Debug;
import application.service.LogGroup;
import model.closed.managers.Session;
import model.closed.managers.meta.EnemyMetas;
import model.closed.objects.creatures.enemies.Enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class							EnemyGenerator
{
// -------------------------------------------> Constants

	private static final int					NUMBER_OF_ATTEMPTS_TO_SELECT_ENEMY = 100;

// -------------------------------------------> Attributes

	private static final
	List<EnemyMetas.Abstract>					allEnemyMetas = new ArrayList<EnemyMetas.Abstract>()
	{{
		add(new EnemyMetas.Hollow());
		add(new EnemyMetas.HollowSoldier());
		add(new EnemyMetas.Ogre());

	}};

// -------------------------------------------> Public methods

	public static Enemy							generate()
	{
		List<EnemyMetas.Abstract>				possibleEnemies;
		EnemyMetas.Abstract						selectedEnemy;

		possibleEnemies = generateEnemiesForCurrentLevel();
		selectedEnemy = selectRandomEnemy(possibleEnemies);

		Debug.logFormat
		(
			LogGroup.GAME,
			"[Model/EnemyGenerator] Spawning enemy '%s'", selectedEnemy.getInstance().getName()
		);

		return selectedEnemy.getInstance();
	}

// -------------------------------------------> Private methods

	private static List<EnemyMetas.Abstract>	generateEnemiesForCurrentLevel()
	{
		List<EnemyMetas.Abstract>				list;

		list = new LinkedList<>();
		for (EnemyMetas.Abstract enemyMeta : allEnemyMetas)
		{
			if (enemyMeta.getAppearanceLevel() <= Session.getLevel())
				list.add(enemyMeta);
		}

		return list;
	}

	private static EnemyMetas.Abstract			selectRandomEnemy(List<EnemyMetas.Abstract> list)
	{
		int										selectedIndex;
		EnemyMetas.Abstract						selectedMeta;

		float									appearanceProbability;

		for (int i = 0; i < NUMBER_OF_ATTEMPTS_TO_SELECT_ENEMY; i++)
		{
			selectedIndex = RandomGenerator.randomBetween(0, list.size() - 1);
			selectedMeta = list.get(selectedIndex);

			appearanceProbability = getTransformedAppearanceProbability(selectedMeta);

			if (RandomGenerator.randomWithProbability(appearanceProbability))
				return selectedMeta;
		}

		Debug.throwException("Can't select enemy");
		return list.get(0);
	}

	private static float						getTransformedAppearanceProbability(EnemyMetas.Abstract meta)
	{
		int										currentLevel;
		int										appearanceLevel;

		float									appearProbabilityMultiplier;

		currentLevel = Session.getLevel();
		appearanceLevel = meta.getAppearanceLevel();

		appearProbabilityMultiplier = 1.f;

		if (currentLevel > appearanceLevel)
		{
			appearProbabilityMultiplier = 1.f - 0.05f * (currentLevel - appearanceLevel);
			appearProbabilityMultiplier = Math.max(0.f, appearProbabilityMultiplier);
		}

		return meta.getAppearanceProbability() * appearProbabilityMultiplier;
	}
}
