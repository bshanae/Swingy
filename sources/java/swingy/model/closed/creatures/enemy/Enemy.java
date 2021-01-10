package swingy.model.closed.creatures.enemy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import swingy.model.closed.artefacts.artefact.dropper.ArtefactDropper;
import swingy.model.closed.artefacts.artefact.dropper.DroppableArtefact;
import swingy.model.closed.battle.Attack;
import swingy.model.closed.creatures.Creature;

import java.util.List;

public class						Enemy extends Creature
{
// -------------------------------> Attributes

	private final int				baseHealth;
	private final int				defense;
	private final List<Attack>		attacks;

	private final int				level;

	@Getter
	private final ArtefactDropper	artefactDropper;

// -------------------------------> Constructors

	@JsonCreator
	public 							Enemy
									(
										@JsonProperty("name") String name,
										@JsonProperty("health") int baseHealth,
										@JsonProperty("defense") int defense,
										@JsonProperty("level") int level,
										@JsonProperty("attacks") List<Attack> attacks,
										@JsonProperty("artefacts") List<DroppableArtefact> artefacts
									)
	{
		this(name, baseHealth, defense, level, attacks, new ArtefactDropper(artefacts));
	}

	private							Enemy
									(
										String name,
										int baseHealth,
										int defense,
										int level,
										List<Attack> attacks,
										ArtefactDropper artefactDropper
									)
	{
		super(name);
		this.baseHealth = baseHealth;
		this.defense = defense;

		this.level = level;

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
		return 100 * (level + 1) + 20 * (level + 1) * (level + 1) + 50;
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
			getAttacks(),
			getArtefactDropper()
		);
	}
}
