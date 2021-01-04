package model.closed.delegates.game;

import application.utils.Point;
import controller.open.Commands;
import model.closed.creatures.enemy.Enemy;
import model.closed.map.Map;
import model.closed.Session;
import model.closed.delegates.Delegate;
import model.closed.map.MapGenerator;
import model.closed.creatures.Creature;
import model.closed.creatures.hero.Hero;
import model.open.Requests;

import java.util.List;

public class				MapDelegate extends Delegate
{
// -----------------------> Attributes

	private final Hero		hero;
	private final Map		map;

	private Enemy			enemy;

// -----------------------> Public methods

	public					MapDelegate()
	{
		hero = Session.getHero();
		map = MapGenerator.getInstance().generate();
		enemy = null;

		Session.setMap(map);
	}

// -----------------------> Protected methods

	@Override
	protected void			whenActivated(boolean isFirstTime)
	{
		drawMap(true);
	}

	@Override
	public void				whenUpdated()
	{
		tryResolve();
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		boolean				shouldDrawMap;
		boolean				shouldStartBattle;

		shouldDrawMap = tryMoveHero(command);
		enemy = checkCollisionWithEnemy();

		shouldStartBattle = enemy != null;

		if (shouldDrawMap)
			drawMap(!shouldStartBattle);

		if (shouldStartBattle)
			startBattle();
	}

// -----------------------> Private methods

	private void			tryResolve()
	{
		if (map.isOnBorder(hero.getPosition()))
		{
			Session.setMap(null);
			requestResolution();
		}
	}

	private boolean			tryMoveHero(Commands.Abstract command)
	{
		Point				oldPosition;
		Point				newPosition;

		oldPosition = hero.getPosition();

		if (command instanceof Commands.GoNorth)
			newPosition = oldPosition.add(new Point(0, 1));
		else if (command instanceof Commands.GoEast)
			newPosition = oldPosition.add(new Point(1, 0));
		else if (command instanceof Commands.GoSouth)
			newPosition = oldPosition.add(new Point(0, -1));
		else if (command instanceof Commands.GoWest)
			newPosition = oldPosition.add(new Point(-1, 0));
		else
			throw new UnrecognizedCommandException(command);

		if (map.isInside(newPosition))
		{
			hero.setPosition(newPosition);
			return true;
		}

		return false;
	}

	private Enemy			checkCollisionWithEnemy()
	{
		List<Creature>		creatures;

		creatures = map.getCreaturesAt(hero.getPosition());

		for (Creature creature : creatures)
		{
			if (creature instanceof Enemy)
				return (Enemy)creature;
		}

		return null;
	}

	private void			drawMap(boolean allowHeroMovement)
	{
		sendRequest(new Requests.Map(map, hero.getPosition(), allowHeroMovement));
	}

	private void			startBattle()
	{
		linkChild(new BattleDelegateA(enemy));
	}
}