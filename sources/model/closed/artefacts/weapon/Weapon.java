package model.closed.artefacts.weapon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.Artefact;
import model.closed.battle.Attack;

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
										@JsonProperty("category") WeaponCategory category,
										@JsonProperty("attacks") List<Attack> attacks
									)
	{
		super(name, category.getSupportedClassesFlags());

		this.category = category;
		this.attacks = attacks;
	}
}