package swingy.model.closed.artefacts.armor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.Artefact;

public class			Armor extends Artefact
{
	@Getter
	private final int	defenseGain;

	@JsonCreator
	public				Armor
						(
							@JsonProperty("name") String name,
							@JsonProperty("level") int level,
							@JsonProperty("defense") int defenseGain)
	{
		super(name, level);
		this.defenseGain = defenseGain;
	}
}