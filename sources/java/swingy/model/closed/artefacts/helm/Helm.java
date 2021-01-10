package swingy.model.closed.artefacts.helm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.creatures.hero.HeroClass;

public class			Helm extends Artefact
{
	@Getter
	private final int	healthGain;

	@JsonCreator
	public 				Helm
						(
							@JsonProperty("name") String name,
							@JsonProperty("level") int level,
							@JsonProperty("health") int healthGain
						)
	{
		super(name, level);
		this.healthGain = healthGain;
	}
}
