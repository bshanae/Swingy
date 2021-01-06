package application.service;

import application.ApplicationOptions;

public enum								LogGroup
{
// -----------------------------------> Values

	MVC(ApplicationOptions.LOG_MVC),
	DELEGATE(ApplicationOptions.LOG_DELEGATE),
	GAME(ApplicationOptions.LOG_GAME);

// -----------------------------------> Attributes

	private final ApplicationOptions	option;

// -----------------------------------> Constructor

	private								LogGroup(ApplicationOptions option)
	{
		this.option = option;
	}

// -----------------------------------> Properties

	public boolean						isEnabled()
	{
		return ApplicationOptions.get(option) || ApplicationOptions.get(ApplicationOptions.LOG_FULL);
	}
}
