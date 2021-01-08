package view.open;

import application.service.Exceptions;
import model.open.Requests;

import java.util.HashMap;
import java.util.Map;

public enum					Context
{
	// Common
	INFO,
	ERROR,
	QUESTION,

	// Hero selector
	HERO_SELECTOR,
	HERO_INFO,
	NAME_ENTRY,
	CLASS_SELECTOR,

	// Game
	MAP,
	HERO_STATS,
	HERO_INVENTORY,
	BATTLE;

	private static final
	Map<Class<?>, Context>	requestToContext = new HashMap<Class<?>, Context>()
	{{
		put(Requests.Info.class, INFO);
		put(Requests.Error.class, ERROR);
		put(Requests.Question.class, QUESTION);
		put(Requests.HeroSelector.class, HERO_SELECTOR);
		put(Requests.HeroInfo.class, HERO_INFO);
		put(Requests.NameEntry.class, NAME_ENTRY);
		put(Requests.ClassSelector.class, HERO_SELECTOR);
		put(Requests.Map.class, MAP);
		put(Requests.HeroStats.class, HERO_STATS);
		put(Requests.HeroInventory.class, HERO_INVENTORY);
		put(Requests.Battle.class, BATTLE);
	}};

	public static Context	parse(Requests.Abstract request)
	{
		if (!requestToContext.containsKey(request.getClass()))
			throw new Exceptions.ObjectNotFound();

		return requestToContext.get(request.getClass());
	}
}
