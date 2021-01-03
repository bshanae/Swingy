package model.closed.objects.creatures.enemies;

import model.closed.managers.meta.EnemyMetas;
import model.closed.objects.creatures.Creature;

public abstract class					Enemy extends Creature
{
// -----------------------------------> Attributes

	private final int					level;

// -----------------------------------> Constructor

	public 								Enemy(String name, int level)
	{
		super(name);
		this.level = level;
	}

// -----------------------------------> Properties

	@Override
	public int							getLevel()
	{
		return level;
	}

	public String						getNameWithLevel()
	{
		return String.format("%s (level %d)", getName(), getLevel());
	}

	public int							getExperienceForDefeating()
	{
		return 200 * (level + 1) * (level + 1);
	}

	public abstract EnemyMetas.Abstract	getMeta();
}