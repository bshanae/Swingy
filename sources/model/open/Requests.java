package model.open;

import application.utils.Point;
import model.closed.managers.battle.BattleLogger;

import java.util.List;

public abstract class						Requests
{
	public interface						Abstract {}

	public interface						System extends  Abstract {}

	public interface						Ui extends  Abstract {}

// --------------------------------------->	Empty

	public static class						SwitchToConsole implements System
	{}

	public static class						SwitchToGui implements System
	{}

	public static class						HeroSelector implements Ui
	{
		public final List<Pockets.Hero>		heroes;

		public 								HeroSelector(List<Pockets.Hero> heroes)
		{
			this.heroes = heroes;
		}
	}

	public static class						NameEntry implements Ui {}

	public static class						ClassSelector implements Ui {}


// --------------------------------------->	With string

	private static abstract class			WithMessage implements Ui
	{
		public final String					message;

		public								WithMessage(String message)
		{
			this.message = message;
		}
	}

	public static class						Error extends WithMessage
	{
		public 								Error(String message)
		{
			super(message);
		}
	}

	public static class						Info extends WithMessage
	{
		public 								Info(String message)
		{
			super(message);
		}
	}

// --------------------------------------->	With complex structure

	public static class						Question implements Ui
	{
		public final String					question;
		public final String					answerA;
		public final String					answerB;

		public 								Question(String question, String answerA, String answerB)
		{
			this.question = question;
			this.answerA = answerA;
			this.answerB = answerB;
		}
	}

	public static class						Map implements Ui
	{
		public final Pockets.Map			map;
		public final Point					pivot;
		public final boolean				allowHeroMovement;

		public								Map
											(
												model.closed.managers.Map map,
												Point pivot,
												boolean allowHeroMovement
											)
		{
			this.map = new Pockets.Map(map);
			this.pivot = pivot;
			this.allowHeroMovement = allowHeroMovement;
		}
	}

	public static class						Battle implements Ui
	{
		public final Pockets.BattleLog		log;
		public final boolean				isBattleFinished;

		public								Battle(model.closed.managers.battle.Battle battle)
		{
			log = new Pockets.BattleLog(battle.getLogger());
			isBattleFinished = battle.isFinished();
		}
	}
}
