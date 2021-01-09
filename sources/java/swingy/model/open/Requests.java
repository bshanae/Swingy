package swingy.model.open;

import swingy.application.utils.Point;
import lombok.Getter;
import swingy.model.closed.battle.Battle;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.HeroInventory;
import swingy.model.closed.map.Map;

import java.util.List;

public abstract class						Requests
{
	public static abstract class			Abstract
	{
		@Getter
		private final boolean				isMandatory;

		protected							Abstract(boolean isMandatory)
		{
			this.isMandatory = isMandatory;
		}
	}

	public static abstract class			System extends Abstract
	{
		public								System()
		{
			super(true);
		}
	}

	public static abstract class			Ui extends Abstract
	{
		public								Ui(boolean isMandatory)
		{
			super(isMandatory);
		}
	}

// --------------------------------------->	Empty

	public static class						SwitchToConsole extends System {}

	public static class						SwitchToGui extends System {}

	public static class						Terminate extends System {}

	public static class						HeroSelector extends Ui
	{
		@Getter
		private final List<Pockets.Hero>	heroes;

		public 								HeroSelector(List<Pockets.Hero> heroes)
		{
			super(true);
			this.heroes = heroes;
		}
	}

	public static class						NameEntry extends Ui
	{
		public 								NameEntry()
		{
			super(true);
		}
	}

	public static class						ClassSelector extends Ui
	{
		public 								ClassSelector()
		{
			super(true);
		}
	}


// --------------------------------------->	With string

	private static abstract class			WithMessage extends Ui
	{
		@Getter
		private final String				message;

		public								WithMessage(boolean isMandatory, String message)
		{
			super(isMandatory);
			this.message = message;
		}
	}

	public static class						Info extends WithMessage
	{
		public 								Info(String message)
		{
			super(true, message);
		}
	}

	public static class						Error extends WithMessage
	{
		public 								Error(String message)
		{
			super(true, message);
		}
	}

// --------------------------------------->	With complex structure

	public static class						Question extends Ui
	{
		@Getter
		private final String				question;

		@Getter
		private final String				answerA;

		@Getter
		private final String				answerB;

		public 								Question(String question, String answerA, String answerB)
		{
			super(true);

			this.question = question;
			this.answerA = answerA;
			this.answerB = answerB;
		}
	}

	public static class						HeroInfo extends Ui
	{
		@Getter
		private final Pockets.Hero			hero;

		@Getter
		private final Pockets.HeroInventory	inventory;

		public 								HeroInfo(Hero hero)
		{
			super(true);

			this.hero = new Pockets.Hero(hero);
			this.inventory = new Pockets.HeroInventory(hero.getInventory());
		}
	}

	public static class						Map extends Ui
	{
		@Getter
		private final Pockets.Map			map;

		@Getter
		private final Point					pivot;

		public								Map
											(
												swingy.model.closed.map.Map map,
												Point pivot,
												boolean isMandatory
											)
		{
			super(isMandatory);
			this.map = new Pockets.Map(map);
			this.pivot = pivot;
		}
	}

	public static class						HeroStats extends Ui
	{
		@Getter
		private final Pockets.Hero			hero;

		public 								HeroStats(Hero hero)
		{
			super(true);
			this.hero = new Pockets.Hero(hero);
		}
	}

	public static class						HeroInventory extends Ui
	{
		@Getter
		private final Pockets.HeroInventory	inventory;

		public 								HeroInventory(swingy.model.closed.creatures.hero.HeroInventory inventory)
		{
			super(true);
			this.inventory = new Pockets.HeroInventory(inventory);
		}
	}

	public static class						Battle extends Ui
	{
		@Getter
		private final Pockets.BattleLog		log;

		@Getter
		private final boolean				isBattleFinished;

		public								Battle(swingy.model.closed.battle.Battle battle)
		{
			super(true);
			log = new Pockets.BattleLog(battle.getLogger());
			isBattleFinished = battle.isFinished();
		}
	}
}
