package swingy.model.closed.map;

import swingy.application.utils.Point;
import swingy.model.closed.creatures.Creature;

import java.util.LinkedList;
import java.util.List;

public class						Map
{
	private final Point				size;
	private final List<Creature>	creatures;

	public 							Map(Point size)
	{
		this.size = size;
		this.creatures = new LinkedList<>();
	}

	public Point					getSize()
	{
		return size;
	}

	public List<Creature>			getCreatures()
	{
		return creatures;
	}

	public List<Creature>			getCreaturesAt(Point position)
	{
		List<Creature>				creatures;

		creatures = new LinkedList<>();
		for (Creature creature : this.creatures)
		{
			if (creature.getPosition().equals(position))
				creatures.add(creature);
		}

		return creatures;
	}

	public boolean					isInside(Point position)
	{
		return position.isGreaterOrEquals(new Point()) && position.isLess(size);
	}

	public boolean					isOnBorder(Point position)
	{
		return
			position.x == 0 ||
			position.x == size.x - 1||
			position.y == 0 ||
			position.y == size.y - 1;
	}
}
