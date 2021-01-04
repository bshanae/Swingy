package model.closed.artefacts.artefact.artefactDropper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.closed.Session;
import model.closed.artefacts.artefact.Artefact;
import model.closed.utils.RandomGenerator;

import java.util.List;

public class							ArtefactDropper
{
// -----------------------------------> Constants

	private static final int			NUMBER_OF_ATTEMPTS_TO_SELECT_SUPPORTED_ARTEFACT = 10;

// -----------------------------------> Attributes

	private final
	List<DroppableArtefact>				artefacts;

// -----------------------------------> Constructor

	@JsonCreator
	public 								ArtefactDropper(@JsonProperty("artefacts") List<DroppableArtefact> artefacts)
	{
		this.artefacts = artefacts;
	}

// -----------------------------------> Public methods

	public Artefact						drop()
	{
		int								index;
		DroppableArtefact				droppableArtefact;
		Artefact						artefact;

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
		currentClass = Session.getHero().getHeroClass().toFlag();

		return (supportedHeroClasses & currentClass) == currentClass;
	}

	private static Artefact				getArtefactOrNull(DroppableArtefact artefact)
	{
		if (RandomGenerator.randomWithProbability(artefact.getDropChance()))
			return artefact.getAlias().get();

		return null;
	}

}
