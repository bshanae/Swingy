package swingy.application.utils;

public class			Point
{
	public int			x;
	public int			y;

	public static Point	random(Point min, Point max)
	{
		Point			point;

		point = new Point();
		point.x = RandomGenerator.randomBetween(min.x, max.x);
		point.y = RandomGenerator.randomBetween(min.y, max.y);

		return point;
	}

	public 				Point()
	{
		this(0, 0);
	}

	public 				Point(int value)
	{
		this(value, value);
	}

	public 				Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String		toString()
	{
		return String.format("{%d, %d}", x, y);
	}

	public boolean		equals(Point other)
	{
		return this.x == other.x && this.y == other.y;
	}

	public Point		add(Point other)
	{
		return new Point(this.x + other.x, this.y + other.y);
	}

	public Point		subtract(Point other)
	{
		return new Point(this.x - other.x, this.y - other.y);
	}

	public Point		multiply(int value)
	{
		return new Point(this.x * value, this.y * value);
	}

	public Point		divide(int value)
	{
		return new Point(this.x / value, this.y / value);
	}

	public Point		abs()
	{
		return new Point(Math.abs(this.x), Math.abs(this.y));
	}

	public boolean		isLess(Point other)
	{
		return this.x < other.x && this.y < other.y;
	}

	public boolean		isLessOrEquals(Point other)
	{
		return this.x <= other.x && this.y <= other.y;
	}

	public boolean		isGreater(Point other)
	{
		return this.x > other.x && this.y > other.y;
	}

	public boolean		isGreaterOrEquals(Point other)
	{
		return this.x >= other.x && this.y >= other.y;
	}
}
