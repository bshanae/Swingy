package swingy.model.closed.battle;

import swingy.application.options.ApplicationOption;
import swingy.model.closed.Session;
import swingy.model.closed.creatures.Creature;
import swingy.model.closed.creatures.enemy.Enemy;
import swingy.model.closed.creatures.hero.Hero;

public class					Battle
{
// ---------------------------> Attributes

	private final Hero			hero;
	private final Enemy			opponent;

	private BattleTurn			turn;
	private BattleLogger		logger;

// ---------------------------> Properties

	public Hero					getHero()
	{
		return hero;
	}

	public Enemy				getOpponent()
	{
		return opponent;
	}

	public BattleLogger			getLogger()
	{
		return logger;
	}

	public void 				setLogger(BattleLogger logger)
	{
		if (this.logger != null)
			throw new RuntimeException("Logger is already set");

		this.logger = logger;
		this.logger.logAwait();
	}

// ---------------------------> Constructor

	public						Battle(Enemy opponent)
	{
		this.hero = Session.getInstance().getHero();
		this.opponent = opponent;

		hero.resetHealth();
		opponent.resetHealth();

		turn = BattleTurn.HERO;
	}

// ---------------------------> Public methods


	public void					executeTurn()
	{
		BattleTurnReport		report = null;

		if (isFinished())
			throw new RuntimeException();

		switch (turn)
		{
			case HERO:
				report = performAttack(hero, opponent);
				break;

			case OPPONENT:
				report = performAttack(opponent, hero);
				break;
		}

		if (logger != null)
			logger.logAttack(report);

		turn = turn.next();
	}

	public boolean				isFinished()
	{
		return hero.isDead() || opponent.isDead();
	}

// ---------------------------> Private methods

	private BattleTurnReport	performAttack(Creature attacker, Creature attackee)
	{
		Attack					attack;
		boolean					isCritical;
		int						damage;
		Integer					specialDamage;

		attack = AttackGenerator.generateAttack(attacker.getAttacks());
		isCritical = AttackGenerator.generateIsCritical(attack);
		damage = AttackGenerator.generateDamage(attack, isCritical);

		if ((specialDamage = getDamageForSpecialCases(attacker)) != null)
			damage = specialDamage;

		attackee.hit(damage);

		if (attackee.isDead())
			finish(attacker, attackee);

		return new BattleTurnReport(attacker, attackee, attack, isCritical, damage, isFinished());
	}

	private void				finish(Creature winner, Creature loser)
	{
		Hero					hero;
		Enemy					enemy;

		if (winner instanceof Hero)
		{
			hero = (Hero)winner;
			enemy = (Enemy)loser;

			hero.addExperience(enemy.getExperienceForDefeating());
		}

		Session.getInstance().getMap().getCreatures().remove(loser);
	}

	private Integer				getDamageForSpecialCases(Creature attacker)
	{
		if (ApplicationOption.ALWAYS_WIN.isDefined())
			return attacker instanceof Hero ? 10000 : 0;
		else if (ApplicationOption.ALWAYS_LOSE.isDefined())
			return attacker instanceof Enemy ? 10000 : 0;

		return null;
	}

}
