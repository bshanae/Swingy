package model.closed.managers.generators;

public class					RandomGenerator
{
	public enum					Function
	{
		LINEAR,
		SINE;
	}

	public static float			random()
	{
		return random(Function.LINEAR);
	}

	public static float			random(Function function)
	{
		switch (function)
		{
			case SINE:
				return (float)Math.sin(randomBetween(Function.LINEAR, 0.f, (float)Math.PI / 2.f));

			default:
				return (float)Math.random();
		}
	}

	public static boolean		randomWithProbability(float probability)
	{
		return random() < probability;
	}

	public static float			randomBetween(float min, float max)
	{
		return (random() * (max + 1 - min)) + min;
	}

	public static int			randomBetween(int min, int max)
	{
		return (int)randomBetween((float)min, (float)max);
	}

	public static float			randomBetween(Function function, float min, float max)
	{
		return (random(function) * (max + 1 - min)) + min;
	}

	public static int			randomBetween(Function function, int min, int max)
	{
		return (int)randomBetween(function, (float)min, (float)max);
	}
}
