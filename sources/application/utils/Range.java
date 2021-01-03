package application.utils;

public class			Range<T extends Comparable<T>>
{
	public final T		min;
	public final T		max;

	public				Range(T value)
	{
		min = value;
		max = value;
	}

	public				Range(T min, T max)
	{
		this.min = min;
		this.max = max;
	}

	public boolean		isInRange(T value)
	{
		return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
	}

	public boolean		isStrictlyInRange(T value)
	{
		return value.compareTo(min) > 0 && value.compareTo(max) < 0;
	}
}
