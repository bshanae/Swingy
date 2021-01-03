package model.closed.managers.generators;

import model.closed.managers.Session;
import model.closed.managers.meta.ArtefactMetas;
import model.closed.managers.meta.EnemyMetas;
import model.closed.managers.meta.SupportedHeroClasses;
import model.closed.objects.artefacts.Artefact;

import java.util.List;

public class							ArtefactGenerator
{
// -----------------------------------> Constants

	private static final int			NUMBER_OF_ATTEMPTS_TO_SELECT_SUPPORTED_ARTEFACT = 10;

// -----------------------------------> Public methods

	public static Artefact				generate(EnemyMetas.Abstract enemyMeta)
	{
		List<ArtefactMetas.Abstract>	weaponMetas;

		int								selectedIndex;
		ArtefactMetas.Abstract			selectedMeta;

		weaponMetas = enemyMeta.getWeaponMetas();

		for (int i = 0; i < NUMBER_OF_ATTEMPTS_TO_SELECT_SUPPORTED_ARTEFACT; i++)
		{
			selectedIndex = RandomGenerator.randomBetween(0, weaponMetas.size() - 1);
			selectedMeta = weaponMetas.get(selectedIndex);

			if (isArtefactSupported(selectedMeta))
				return generateArtefact(selectedMeta);
		}

		return null;
	}

// -----------------------------------> Private methods

	private static boolean				isArtefactSupported(ArtefactMetas.Abstract meta)
	{
		SupportedHeroClasses			supportedHeroClasses;
		int								currentClass;

		supportedHeroClasses = meta.getSupportedHeroClasses();
		currentClass = SupportedHeroClasses.getBitflagFromClass(Session.getHero().getHeroClass());

		return supportedHeroClasses.hasBitflag(currentClass);
	}

	private static Artefact				generateArtefact(ArtefactMetas.Abstract meta)
	{
		if (RandomGenerator.randomWithProbability(meta.getDropProbability()))
			return meta.getInstance();

		return null;
	}
}
