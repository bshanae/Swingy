package model.closed.artefacts.helm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.Artefact;
import model.closed.creatures.hero.HeroClass;

public class			Helm extends Artefact
{
	@Getter
	private final int	healthGain;

	@JsonCreator
	public 				Helm
						(
							@JsonProperty("name") String name,
							@JsonProperty("health") int healthGain
						)
	{
		super(name);
		this.healthGain = healthGain;
	}
}
