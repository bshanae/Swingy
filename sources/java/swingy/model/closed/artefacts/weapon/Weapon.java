package swingy.model.closed.artefacts.weapon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.battle.Attack;

import java.util.List;

public class						Weapon extends Artefact
{
// -------------------------------> Attributes

	@Getter
	private final WeaponCategory	category;

	@Getter
	private final List<Attack>		attacks;


// -------------------------------> Constructor

	@JsonCreator
	protected						Weapon
									(
										@JsonProperty("name") String name,
										@JsonProperty("level") int level,
										@JsonProperty("category") WeaponCategory category,
										@JsonProperty("attacks") List<Attack> attacks
									)
	{
		super(name, level, category.getSupportedClassesFlags());

		this.category = category;
		this.attacks = attacks;
	}
}
