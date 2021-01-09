package swingy.model.closed.creatures.hero;

import swingy.application.service.Exceptions;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum					HeroClass
{
// -----------------------> Values

	@JsonProperty("Warrior")
	WARRIOR("Warrior", 0b0001),

	@JsonProperty("Swordsman")
	SWORDSMAN("Swordsman", 0b0010),

	@JsonProperty("Assassin")
	ASSASSIN("Assassin", 0b0100),

	@JsonProperty("Mage")
	MAGE("Mage", 0b1000);


// -----------------------> Attributes

	private final String	string;
	private final int		flag;

// -----------------------> Constructor

	private					HeroClass(String string, int flag)
	{
		this.string = string;
		this.flag = flag;
	}

	public static HeroClass	fromString(String string)
	{
		if (string.equalsIgnoreCase(WARRIOR.toString()))
			return WARRIOR;
		else if (string.equalsIgnoreCase(SWORDSMAN.toString()))
			return SWORDSMAN;
		else if (string.equalsIgnoreCase(ASSASSIN.toString()))
			return ASSASSIN;
		else if (string.equalsIgnoreCase(MAGE.toString()))
			return MAGE;
		else
			throw new Exceptions.ObjectNotFound();
	}

// -----------------------> Public methods

	@Override
	public String			toString()
	{
		return string;
	}

	public int				toFlag()
	{
		return flag;
	}
}
