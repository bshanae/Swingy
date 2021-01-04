package application.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class			Range<T extends Comparable<T>>
{
	@Getter
	private final T		min;

	@Getter
	private final T		max;

	public				Range(T value)
	{
		min = value;
		max = value;
	}

	@JsonCreator
	public				Range(@JsonProperty("min") T min, @JsonProperty("max") T max)
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
