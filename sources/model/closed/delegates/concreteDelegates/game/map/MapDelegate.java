package model.closed.delegates.concreteDelegates.game.map;

import application.service.Exceptions;
import application.utils.Point;
import controller.open.Commands;
import lombok.Getter;
import model.closed.creatures.enemy.Enemy;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.closed.delegates.concreteDelegates.game.battle.BattleDelegate;
import model.closed.delegates.concreteDelegates.game.battle.RunAwayDelegate;
import model.closed.map.Map;
import model.closed.Session;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.map.MapGenerator;
import model.closed.creatures.Creature;
import model.closed.creatures.hero.Hero;
import model.open.Requests;

import java.util.List;

public class				MapDelegate extends AbstractDelegate
{
// -----------------------> Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

	private static class	CommandInterpreter
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

		public 				CommandInterpreter(Commands.Abstract command)
		{
			this.command = command;
			this.commandType = CommandType.UNKNOWN;

			tryParseMoveCommands();
			tryParseOther();
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
	}

// -----------------------> Attributes

	private final Hero		hero;
	private final Map		map;

	private boolean			isProcessingBattle;
	private Enemy			opponent;

// -----------------------> Public methods

	public					MapDelegate()
	{
		hero = Session.getHero();
		map = MapGenerator.getInstance().generate();

		isProcessingBattle = false;
		opponent = null;

		Session.setMap(map);
	}

// -----------------------> Implementations

	@Override
	protected void			whenActivated(boolean isFirstTime)
	{
		drawMap(true);
	}

	@Override
	public void				whenUpdated()
	{
		if (!isProcessingBattle)
			tryResolve();
	}

	@Override
	public void				whenChildResolved(AbstractResolutionObject object)
	{
		if (object instanceof RunAwayDelegate.ResolutionObject)
		{
			if (((RunAwayDelegate.ResolutionObject)object).shouldStartBattle())
				startBattle();
		}
		else if (object instanceof BattleDelegate.ResolutionObject)
			isProcessingBattle = false;
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		CommandInterpreter	interpreter;

		if (isProcessingBattle)
			return;

		interpreter = new CommandInterpreter(command.getCommand());
		switch (interpreter.getCommandType())
		{
			case MOVE:
				performMove(interpreter.getShift());
				command.markExecuted();
				break;

			case OPEN_STATS:
				stackChildLater(new HeroStatsDelegate());
				command.markExecuted();
				break;

			case OPEN_INVENTORY:
				stackChildLater(new HeroInventoryDelegate());
				command.markExecuted();
				break;
		}
	}

// -----------------------> Private methods

	private void			tryResolve()
	{
		if (map.isOnBorder(hero.getPosition()))
		{
			Session.setMap(null);
			resolveLater(new ResolutionObject());
		}
	}

	private void			performMove(Point shift)
	{
		boolean				shouldStartBattle;

		if (!tryMoveHero(shift))
			return;

		opponent = checkCollisionWithEnemy();
		shouldStartBattle = opponent != null;

		drawMap(!shouldStartBattle);

		if (shouldStartBattle)
			tryRunAway();
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

	private void			tryRunAway()
	{
		isProcessingBattle = true;
		stackChildLater(new RunAwayDelegate(opponent));
	}

	private void			startBattle()
	{
		stackChildLater(new BattleDelegate(opponent));
	}
}