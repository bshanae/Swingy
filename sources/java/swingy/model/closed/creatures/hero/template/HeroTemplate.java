package swingy.model.closed.creatures.hero.template;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.ArtefactAlias;
import swingy.model.closed.creatures.hero.HeroClass;

public class					HeroTemplate
{
	@Getter
	private final HeroClass		heroClass;

	@Getter
	private final ArtefactAlias	helm;

	@Getter
	private final ArtefactAlias	armor;

	@Getter
	private final ArtefactAlias	weapon;

	@JsonCreator
	public						HeroTemplate
								(
									@JsonProperty("class") HeroClass heroClass,
									@JsonProperty("helm") String helm,
									@JsonProperty("armor") String armor,
									@JsonProperty("weapon") String weaponName
								)
	{
		this.heroClass = heroClass;
		this.helm = new ArtefactAlias(helm);
		this.armor = new ArtefactAlias(armor);
		this.weapon = new ArtefactAlias(weaponName);
	}
}
