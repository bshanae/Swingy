package swingy.model.closed.battle;

import swingy.application.utils.RandomGenerator;

import java.util.LinkedList;
import java.util.List;

public abstract class			AttackGenerator
{
// ---------------------------> Constants

	public static final float	CRITICAL_MULTIPLIER = 2.f;

// ---------------------------> Public methods

	public static Attack		generateAttack(List<Attack> possibleAttacks)
	{
		List<Float>				weights;
		int						randomIndex;

		weights = new LinkedList<>();
		for (Attack possibleAttack : possibleAttacks)
			weights.add(possibleAttack.weight);

		randomIndex = generateIndex(weights);
		return possibleAttacks.get(randomIndex);
	}

	public static boolean		generateIsCritical(Attack attack)
	{
		return RandomGenerator.random() < attack.criticalChance;
	}

	public static int			generateDamage(Attack attack, boolean isCritical)
	{
		int						damageMin;
		int						damageMax;
		int						baseDamage;
		int						finalDamage;

		damageMin = attack.damageRange.getMin();
		damageMax = attack.damageRange.getMax();

		baseDamage = RandomGenerator.randomBetween(RandomGenerator.Function.SINE, damageMin, damageMax);
		finalDamage = isCritical ? (int)(baseDamage * CRITICAL_MULTIPLIER) : baseDamage;

		return finalDamage;
	}

// ---------------------------> Private methods

	private static int			generateIndex(List<Float> weights)
	{
		float					totalWeight;
		float					random;
		float					sumOfProcessedWeight;

		totalWeight = 0f;
		for (Float weight : weights)
			totalWeight += weight;

		random = RandomGenerator.random() * totalWeight;

		sumOfProcessedWeight = 0;
		for (int i = 0; i < weights.size(); i++)
		{
			sumOfProcessedWeight += weights.get(i);

			if (random < sumOfProcessedWeight)
				return i;
		}

		throw new RuntimeException();
	}
}
