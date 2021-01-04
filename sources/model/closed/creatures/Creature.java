package model.closed.creatures;

import application.utils.Point;
import lombok.Getter;
import lombok.Setter;
import model.closed.battle.Attack;

import java.util.List;

public abstract class				Creature
{
// -------------------------------> Attributes

	@Getter
	private final String			name;

	@Getter
	private int						currentHealth;

	@Getter @Setter
	private Point					position;


// -------------------------------> Constructor

	protected						Creature(String name)
	{
		this.name = name;
	}

// -------------------------------> Abstract properties

	public abstract int				getLevel();

	protected abstract int			getFullHealth();

	public abstract int				getDefense();

	public abstract List<Attack>	getAttacks();

// -------------------------------> Public methods

	public boolean					isAlive()
	{
		return currentHealth > 0;
	}

	public boolean					isDead()
	{
		return !isAlive();
	}

// -------------------------------> Public methods : Health

	public void						resetHealth()
	{
		currentHealth = getFullHealth();
	}

	public void						hit(int damage)
	{
		damage = Math.max(0, damage - getDefense());
		currentHealth = Math.max(0, currentHealth - damage);
	}
}
