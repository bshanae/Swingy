package swingy.model.closed;

import swingy.application.patterns.SingletonMap;
import lombok.Getter;
import lombok.Setter;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.HeroBackup;
import swingy.model.closed.map.Map;

public class				Session
{
// -----------------------> Attributes

	@Getter @Setter
	private Hero			hero;
	private HeroBackup		heroBackup;

	@Getter @Setter
	private Map				map;

// -----------------------> Properties

	public static Session	getInstance()
	{
		return SingletonMap.getInstanceOf(Session.class);
	}

// -----------------------> Public methods

	public void 			createHeroBackup()
	{
		heroBackup = new HeroBackup(hero);
	}

	public void 			applyHeroBackup()
	{
		heroBackup.apply();
	}
}
