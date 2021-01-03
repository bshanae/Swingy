package model.closed.objects.creatures.enemies;

import application.utils.Range;
import model.closed.managers.meta.EnemyMetas;
import model.closed.objects.Attack;

import java.util.LinkedList;
import java.util.List;

public class					HollowSoldier extends Enemy
{
// ---------------------------> Constructor

	public						HollowSoldier()
	{
		super("Hollow soldier", 1);
	}

// ---------------------------> Properties

	@Override
	public int					getBaseHealth()
	{
		return 400;
	}

	@Override
	public int					getDefense()
	{
		return 10;
	}

	@Override
	public List<Attack>			getAttacks()
	{
		List<Attack>			attacks;

		attacks = new LinkedList<>();
		attacks.add(new Attack("Blow with sword", 70, new Range<>(40, 55), 0.05f));
		attacks.add(new Attack("Punch", 30, new Range<>(20, 30), 0.01f));

		return attacks;
	}

	@Override
	public EnemyMetas.Abstract	getMeta()
	{
		return new EnemyMetas.HollowSoldier();
	}

}
