package model.closed;

import application.patterns.SingletonMap;
import lombok.Getter;
import lombok.Setter;
import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroBackup;
import model.closed.map.Map;

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
