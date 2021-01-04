package model.closed.artefacts.artefact.artefactDropper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.Artefact;
import model.closed.artefacts.artefact.ArtefactAlias;

public class					DroppableArtefact
{
	@Getter
	private final ArtefactAlias	alias;

	@Getter
	private final float			dropChance;

	@JsonCreator
	public						DroppableArtefact
								(
									@JsonProperty("name") String artefactName,
									@JsonProperty("dropChance") float dropChance
								)
	{
		this.alias = new ArtefactAlias(artefactName);
		this.dropChance = dropChance;
	}
}
