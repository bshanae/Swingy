package model.closed.artefacts.armor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.Artefact;

public class			Armor extends Artefact
{
	@Getter
	private final int	defenseGain;

	@JsonCreator
	public				Armor
						(
							@JsonProperty("name") String name,
							@JsonProperty("defense") int defenseGain)
	{
		super(name);
		this.defenseGain = defenseGain;
	}
}