package model.closed.delegates.game.map;

import application.service.Exceptions;
import application.utils.Point;
import controller.open.Commands;
import lombok.Getter;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.game.battle.BattleDelegateA;
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
// -----------------------> Nested types

	private static class	CommandParser
	{
	// -------------------> Nested types

		public enum			CommandType
		{
			UNKNOWN,
			MOVE,
			OPEN_STATS,
			OPEN_INVENTORY
		}

	// -------------------> Attributes

		private final
		Commands.Abstract	command;

		@Getter
		private CommandType	commandType;

		private Point		shift;

	// -------------------> Constructor

		public 				CommandParser(Commands.Abstract command)
		{
			this.command = command;
			this.commandType = CommandType.UNKNOWN;

			tryParseMoveCommands();
			tryParseOther();

			validateCommandType();
		}

	// -------------------> Properties

		public Point		getShift()
		{
			if (commandType != CommandType.MOVE)
				throw new Exceptions.InvalidUsage();

			return shift;
		}

	// -------------------> Private methods

		private void		tryParseMoveCommands()
		{
			if (command instanceof Commands.GoNorth)
				shift = new Point(0, 1);
			else if (command instanceof Commands.GoEast)
				shift = new Point(1, 0);
			else if (command instanceof Commands.GoSouth)
				shift = new Point(0, -1);
			else if (command instanceof Commands.GoWest)
				shift = new Point(-1, 0);
			else
				return;

			commandType = CommandType.MOVE;
		}

		private void		tryParseOther()
		{
			if (command instanceof Commands.Stats)
				commandType = CommandType.OPEN_STATS;
			else if (command instanceof Commands.Inventory)
				commandType = CommandType.OPEN_INVENTORY;
		}

		private void		validateCommandType()
		{
			if (commandType == CommandType.UNKNOWN)
				throw new UnexpectedCommandException(command);
		}
	}

// -----------------------> Attributes

	private final Hero		hero;
	private final Map		map;

// -----------------------> Public methods

	public					MapDelegate()
	{
		hero = Session.getHero();
		map = MapGenerator.getInstance().generate();

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
		CommandParser		parser;

		parser = new CommandParser(command);
		switch (parser.getCommandType())
		{
			case MOVE:
				performMove(parser.getShift());
				break;

			case OPEN_STATS:
				linkChild(new HeroStatsDelegate());
				break;

			case OPEN_INVENTORY:
				linkChild(new HeroInventoryDelegate());
				break;
		}
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

	private void			performMove(Point shift)
	{
		Enemy				enemy;
		boolean				shouldStartBattle;

		if (!tryMoveHero(shift))
			return;

		enemy = checkCollisionWithEnemy();
		shouldStartBattle = enemy != null;

		drawMap(!shouldStartBattle);

		if (shouldStartBattle)
			startBattle(enemy);
	}

	private boolean			tryMoveHero(Point shift)
	{
		Point				oldPosition;
		Point				newPosition;

		oldPosition = hero.getPosition();
		newPosition = oldPosition.add(shift);

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

	private void			drawMap(boolean heroMovementAllowed)
	{
		sendRequest(new Requests.Map(map, hero.getPosition(), heroMovementAllowed));
	}

	private void			startBattle(Enemy enemy)
	{
		linkChild(new BattleDelegateA(enemy));
	}
}