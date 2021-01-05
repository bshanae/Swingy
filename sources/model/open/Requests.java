package model.open;

import application.utils.Point;
import lombok.Getter;

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
		@Getter
		private final List<Pockets.Hero>	heroes;

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
		@Getter
		private final String				message;

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
		@Getter
		private final String				question;

		@Getter
		private final String				answerA;

		@Getter
		private final String				answerB;

		public 								Question(String question, String answerA, String answerB)
		{
			this.question = question;
			this.answerA = answerA;
			this.answerB = answerB;
		}
	}

	public static class						HeroInfo implements Ui
	{
		@Getter
		private final Pockets.Hero			hero;

		@Getter
		private final Pockets.HeroInventory	inventory;

		public 								HeroInfo(model.closed.creatures.hero.Hero hero)
		{
			this.hero = new Pockets.Hero(hero);
			this.inventory = new Pockets.HeroInventory(hero.getInventory());
		}
	}

	public static class						Map implements Ui
	{
		@Getter
		private final Pockets.Map			map;

		@Getter
		private final Point					pivot;

		@Getter
		private final boolean				heroMovementAllowed;

		public								Map
											(
												model.closed.map.Map map,
												Point pivot,
												boolean heroMovementAllowed
											)
		{
			this.map = new Pockets.Map(map);
			this.pivot = pivot;
			this.heroMovementAllowed = heroMovementAllowed;
		}
	}

	public static class						Battle implements Ui
	{
		@Getter
		private final Pockets.BattleLog		log;

		@Getter
		private final boolean				isBattleFinished;

		public								Battle(model.closed.battle.Battle battle)
		{
			log = new Pockets.BattleLog(battle.getLogger());
			isBattleFinished = battle.isFinished();
		}
	}
}
