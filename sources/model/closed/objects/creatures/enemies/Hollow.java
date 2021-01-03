package model.closed.objects.creatures.enemies;

import application.utils.Range;
import model.closed.managers.meta.EnemyMetas;
import model.closed.objects.Attack;

import java.util.LinkedList;
import java.util.List;

public class					Hollow extends Enemy
{
// ---------------------------> Constructor

	public						Hollow()
	{
		super("Hollow", 0);
	}

// ---------------------------> Properties

	@Override
	public int					getBaseHealth()
	{
		return 200;
	}

	@Override
	public int					getDefense()
	{
		return 0;
	}

	@Override
	public List<Attack>			getAttacks()
	{
		List<Attack>			attacks;

		attacks = new LinkedList<>();
		attacks.add(new Attack("Punch", 60, new Range<>(20, 30), 0.01f));
		attacks.add(new Attack("Byte", 40, new Range<>(30, 40), 0.01f));

		return attacks;
	}

	@Override
	public EnemyMetas.Abstract	getMeta()
	{
		return new EnemyMetas.Hollow();
	}

}
