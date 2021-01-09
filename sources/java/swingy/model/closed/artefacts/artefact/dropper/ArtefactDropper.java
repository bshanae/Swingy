package swingy.model.closed.artefacts.artefact.dropper;

import swingy.application.options.ApplicationOption;
import swingy.application.utils.RandomGenerator;
import swingy.model.closed.Session;
import swingy.model.closed.artefacts.artefact.Artefact;

import java.util.List;

public class							ArtefactDropper
{
// -----------------------------------> Constants

	private static final int			NUMBER_OF_ATTEMPTS_TO_SELECT_SUPPORTED_ARTEFACT = 10;
	private static final float			ARTEFACT_DROP_CHANCE = 0.6f;

// -----------------------------------> Attributes

	private final
	List<DroppableArtefact>				artefacts;

// -----------------------------------> Constructor

	public 								ArtefactDropper(List<DroppableArtefact> artefacts)
	{
		this.artefacts = artefacts;
	}

// -----------------------------------> Public methods

	public Artefact						drop()
	{
		int								index;
		DroppableArtefact				droppableArtefact;
		Artefact						artefact;

		if (artefacts == null)
			return null;

		if (ApplicationOption.TEST_ARTEFACT_DROPPER.isDefined())
			return artefacts.get(0).getAlias().get();

		for (int i = 0; i < NUMBER_OF_ATTEMPTS_TO_SELECT_SUPPORTED_ARTEFACT; i++)
		{
			index = RandomGenerator.randomBetween(0, artefacts.size() - 1);
			droppableArtefact = artefacts.get(index);
			artefact = droppableArtefact.getAlias().get();

			if (isArtefactSupported(artefact))
				return getArtefactOrNull(droppableArtefact);
		}

		return null;
	}


// -----------------------------------> Private methods

	private static boolean				isArtefactSupported(Artefact artefact)
	{
		int								supportedHeroClasses;
		int								currentClass;

		supportedHeroClasses = artefact.getSupportedClassesFlags();
		currentClass = Session.getInstance().getHero().getHeroClass().toFlag();

		return (supportedHeroClasses & currentClass) == currentClass;
	}

	private static Artefact				getArtefactOrNull(DroppableArtefact artefact)
	{
		if (RandomGenerator.randomWithProbability(ARTEFACT_DROP_CHANCE))
			return artefact.getAlias().get();

		return null;
	}

}
