package swingy.application.utils;

public class					RandomGenerator
{
	public static float			random()
	{
		return (float)Math.random();
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
}
