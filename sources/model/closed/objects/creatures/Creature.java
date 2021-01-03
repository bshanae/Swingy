package model.closed.objects.creatures;

import application.utils.Point;
import model.closed.objects.Attack;

import java.util.List;

public abstract class				Creature
{
// -------------------------------> Attributes

	private final String			name;

	private int						health;
	private Point					position;

// -------------------------------> Constructor

	protected						Creature(String name)
	{
		this.name = name;
	}

// -------------------------------> Abstract properties

	public abstract int				getLevel();

	protected abstract int			getBaseHealth();

	public abstract int				getDefense();

	public abstract List<Attack>	getAttacks();

// -------------------------------> Properties

	public String					getName()
	{
		return name;
	}

	public int						getHealth()
	{
		return health;
	}

	public Point					getPosition()
	{
		return position;
	}

	public void						setPosition(Point position)
	{
		this.position = position;
	}

// -------------------------------> Public methods

	public boolean					isAlive()
	{
		return health > 0;
	}

	public boolean					isDead()
	{
		return !isAlive();
	}

// -------------------------------> Public methods : Health

	public void						resetHealth()
	{
		health = getBaseHealth();
	}

	public void						hit(int damage)
	{
		damage = Math.max(0, damage - getDefense());
		health = Math.max(0, health - damage);
	}
}
