package model.closed.creatures.enemy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.artefact.artefactDropper.ArtefactDropper;
import model.closed.battle.Attack;
import model.closed.creatures.Creature;

import java.util.List;

public class						Enemy extends Creature
{
// -------------------------------> Attributes

	private final int				baseHealth;
	private final int				defense;
	private final List<Attack>		attacks;

	private final int				level;

	@Getter
	private final float				spawnChance;

	@Getter
	private final ArtefactDropper	artefactDropper;

// -------------------------------> Constructor

	@JsonCreator
	public 							Enemy
									(
										@JsonProperty("name") String name,
										@JsonProperty("health") int baseHealth,
										@JsonProperty("defense") int defense,
										@JsonProperty("level") int level,
										@JsonProperty("spawnChance") float spawnChance,
										@JsonProperty("attacks") List<Attack> attacks,
										@JsonProperty("artefacts") ArtefactDropper artefactDropper
									)
	{
		super(name);
		this.baseHealth = baseHealth;
		this.defense = defense;

		this.level = level;
		this.spawnChance = spawnChance;

		this.attacks = attacks;
		this.artefactDropper = artefactDropper;
	}

// -------------------------------> Properties

	@Override
	public int						getLevel()
	{
		return level;
	}

	@Override
	protected int					getFullHealth()
	{
		return baseHealth;
	}

	@Override
	public int						getDefense()
	{
		return defense;
	}

	@Override
	public List<Attack>				getAttacks()
	{
		return attacks;
	}

	public String					getNameWithLevel()
	{
		return String.format("%s (level %d)", getName(), getLevel());
	}

	public int						getExperienceForDefeating()
	{
		return 200 * (level + 1) * (level + 1);
	}

// -------------------------------> Public methods

	@Override
	public Enemy					clone()
	{
		return new Enemy
		(
			getName(),
			getFullHealth(),
			getDefense(),
			getLevel(),
			getSpawnChance(),
			getAttacks(),
			getArtefactDropper()
		);
	}
}
