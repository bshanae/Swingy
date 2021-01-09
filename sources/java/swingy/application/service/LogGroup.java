package swingy.application.service;

import swingy.application.options.ApplicationOption;

public enum								LogGroup
{
// -----------------------------------> Values

	MVC(ApplicationOption.LOG_MVC),
	DELEGATE(ApplicationOption.LOG_DELEGATE),
	GAME(ApplicationOption.LOG_GAME);

// -----------------------------------> Attributes

	private final ApplicationOption option;

// -----------------------------------> Constructor

	private								LogGroup(ApplicationOption option)
	{
		this.option = option;
	}

// -----------------------------------> Properties

	public boolean						isEnabled()
	{
		return option.isDefined() || ApplicationOption.LOG_FULL.isDefined();
	}
}
