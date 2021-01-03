package model.closed.objects.creatures.enemies;

import application.utils.Range;
import model.closed.managers.meta.EnemyMetas;
import model.closed.objects.Attack;

import java.util.LinkedList;
import java.util.List;

public class					Ogre extends Enemy
{
// ---------------------------> Constructor

	public						Ogre()
	{
		super("Ogre", 1);
	}

// ---------------------------> Properties

	@Override
	public int					getBaseHealth()
	{
		return 1000;
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
		attacks.add(new Attack("Punch", 80, new Range<>(40, 60), 0.01f));
		attacks.add(new Attack("Fall", 15, new Range<>(80, 100), 0.01f));
		attacks.add(new Attack("Byte", 5, new Range<>(200), 0.4f));

		return attacks;
	}

	@Override
	public EnemyMetas.Abstract	getMeta()
	{
		return new EnemyMetas.Ogre();
	}

}
