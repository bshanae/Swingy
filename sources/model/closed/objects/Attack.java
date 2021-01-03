package model.closed.objects;

import application.utils.Range;

public class						Attack
{
	public final String				name;
	public final float				weight;
	public final Range<Integer>		damageRange;
	public final float				criticalChance;

	public 							Attack
									(
										String name,
										float weight,
										Range<Integer> damageRange,
										float criticalChance
									)
	{
		this.name = name;
		this.weight = weight;
		this.damageRange = damageRange;
		this.criticalChance = criticalChance;
	}

	public 							Attack(String name, float weight, Range<Integer> damageRange)
	{
		this.name = name;
		this.weight = weight;
		this.damageRange = damageRange;
		this.criticalChance = 0.f;
	}

	public Attack					applyGain(int gain)
	{
		return new Attack
		(
			name,
			weight,
			new Range<>(damageRange.min + gain, damageRange.max + gain),
			criticalChance
		);
	}
}
