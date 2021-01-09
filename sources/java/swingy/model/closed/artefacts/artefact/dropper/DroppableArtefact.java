package swingy.model.closed.artefacts.artefact.dropper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.ArtefactAlias;

public class					DroppableArtefact
{
	@Getter
	private final ArtefactAlias	alias;

	@JsonCreator
	public						DroppableArtefact(@JsonProperty("name") String artefactName)
	{
		this.alias = new ArtefactAlias(artefactName);
	}
}
