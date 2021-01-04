package model.closed.battle;

import model.closed.creatures.Creature;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class					BattleLogger
{
// --------------------------->	Attributes

	private final List<String>	lines;
	private int					turnCount;

// --------------------------->	Public methods

	public						BattleLogger()
	{
		lines = new LinkedList<>();
		turnCount = 0;
	}

	public List<String>			getLines()
	{
		return Collections.unmodifiableList(lines);
	}

	public void					logAwait()
	{
		log("Battle will start soon...");
	}

	public void					logAttack(BattleTurnReport report)
	{
		if (++turnCount % 2 == 1)
			log();

		logFormat
		(
			"'%s' hit '%s' with %s '%s' and dealt %d damage. Now '%s' has %d hp.",
			report.attacker.getName(),
			report.attackee.getName(),
			report.isCritical ? "critical" : "",
			report.attack.name,
			report.damage,
			report.attackee.getName(),
			report.attackee.getCurrentHealth()
		);

		if (report.isBattleFinished)
			logEndOfBattle(report);
	}

// --------------------------->	Private methods

	private void				logEndOfBattle(BattleTurnReport report)
	{
		final Creature			winner = report.attacker;
		final Creature			loser = report.attackee;

		logFormat("'%s' killed '%s' and won the battle.", winner.getName(), loser.getName());
	}

	private void				log(String string)
	{
		lines.add(string);
	}

	private void				log()
	{
		log("");
	}

	private void				logFormat(String format, Object... arguments)
	{
		log(String.format(format, arguments));
	}
}
