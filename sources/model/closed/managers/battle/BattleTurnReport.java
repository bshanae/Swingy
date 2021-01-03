package model.closed.managers.battle;

import model.closed.objects.Attack;
import model.closed.objects.creatures.Creature;

public class				BattleTurnReport
{
	public final Creature	attacker;
	public final Creature	attackee;
	public final Attack		attack;
	public final boolean	isCritical;
	public final int		damage;
	public final boolean	isBattleFinished;


	public					BattleTurnReport
							(
								Creature attacker,
								Creature attackee,
								Attack attack,
								boolean isCritical,
								int damage,
								boolean isBattleFinished
							)
	{
		this.attacker = attacker;
		this.attackee = attackee;
		this.attack = attack;
		this.isCritical = isCritical;
		this.damage = damage;
		this.isBattleFinished = isBattleFinished;
	}
}
