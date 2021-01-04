package model.closed.creatures.hero.heroTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.ArtefactAlias;
import model.closed.creatures.hero.HeroClass;

public class					HeroTemplate
{
	@Getter
	private final HeroClass		heroClass;

	@Getter
	private final int			health;

	@Getter
	private final int			defense;

	@Getter
	private final ArtefactAlias	weapon;

	@JsonCreator
	public						HeroTemplate
								(
									@JsonProperty("class") HeroClass heroClass,
									@JsonProperty("health") int health,
									@JsonProperty("defense") int defense,
									@JsonProperty("weapon") String weaponName
								)
	{
		this.heroClass = heroClass;
		this.health = health;
		this.defense = defense;
		this.weapon = new ArtefactAlias(weaponName);
	}
}
