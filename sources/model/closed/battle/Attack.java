package model.closed.battle;

import application.utils.Range;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class						Attack
{
	public final String				name;
	public final float				weight;
	public final Range<Integer>		damageRange;
	public final float				criticalChance;

	@JsonCreator
	public 							Attack
									(
										@JsonProperty("name") String name,
										@JsonProperty("weight") float weight,
										@JsonProperty("damage") Range<Integer> damageRange,
										@JsonProperty("critical chance") float criticalChance
									)
	{
		this.name = name;
		this.weight = weight;
		this.damageRange = damageRange;
		this.criticalChance = criticalChance;
	}

	public Attack					applyGain(int gain)
	{
		return new Attack
		(
			name,
			weight,
			new Range<>(damageRange.getMin() + gain, damageRange.getMax() + gain),
			criticalChance
		);
	}
}
