package model.closed.creatures.hero;

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
