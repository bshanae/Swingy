package view.closed.ui.utils;

import application.utils.Point;
import model.open.Pockets;
import model.open.Requests;

import java.util.List;

public class						MapGenerator
{
	private static final char		NULL_CHARACTER = ' ';
	private static final char		DEFAULT_CHARACTER = '·';
	private static final char		HERO_CHARACTER = 'H';
	private static final char		ENEMY_CHARACTER = 'E';

	private final Point				canvasSize;

	private Point					mapSize;
	private List<Pockets.Creature>	mapCreatures;

	private Point					pivot;

	private Point					offset;

	public							MapGenerator(Point canvasSize)
	{
		this.canvasSize = canvasSize;
	}

	public char[][]					generate(Requests.Map request)
	{
		loadInfo(request);
		calculateOffset();

		return generateArray();
	}

	private void					loadInfo(Requests.Map request)
	{
		mapSize = request.getMap().getSize();
		mapCreatures = request.getMap().getCreatures();

		pivot = request.getPivot();
	}

	private void					calculateOffset()
	{
		Point						mapOverCanvasOffset;
		Point						pivotOverMapOffset;

		mapOverCanvasOffset = canvasSize.subtract(new Point(1)).divide(2);

		pivotOverMapOffset = new Point();
		pivotOverMapOffset.x = pivot.x * -1;
		pivotOverMapOffset.y = pivot.y * -1;

		offset = mapOverCanvasOffset.add(pivotOverMapOffset);
	}

	private char[][]				generateArray()
	{
		char[][]					array;
		int							invertedY;

		array = new char[canvasSize.y][canvasSize.x];
		for (int y = 0; y < canvasSize.y; y++)
		{
			invertedY = canvasSize.y - y - 1;

			for (int x = 0; x < canvasSize.x; x++)
				array[invertedY][x] = getCharacterForCoordinate(new Point(x, y));
		}

		return array;
	}

	private char					getCharacterForCoordinate(Point canvasCoordinate)
	{
		Point						mapCoordinate;
		Pockets.Creature			creature;

		mapCoordinate = canvasCoordinate.subtract(offset);

		if (!isCoordinateInMap(mapCoordinate))
			return NULL_CHARACTER;

		creature = getCreatureForCoordinate(mapCoordinate);

		if (creature instanceof Pockets.Hero)
			return HERO_CHARACTER;
		else if (creature instanceof Pockets.Enemy)
			return ENEMY_CHARACTER;

		return DEFAULT_CHARACTER;
	}

	private Pockets.Creature		getCreatureForCoordinate(Point coordinate)
	{
		for (Pockets.Creature creature : mapCreatures)
			if (creature.getPosition().equals(coordinate))
				return creature;

		return null;
	}

	private boolean					isCoordinateInMap(Point coordinate)
	{
		return coordinate.isGreaterOrEquals(new Point()) && coordinate.isLess(mapSize);
	}
}
