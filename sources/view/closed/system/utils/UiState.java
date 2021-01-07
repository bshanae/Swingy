package view.closed.system.utils;

import application.patterns.SingletonMap;

public class				UiState
{
// -----------------------> Attributes

	private boolean			wasConsoleUsed;
	private boolean			wasGuiUsed;

// -----------------------> Properties

	public static UiState	getInstance()
	{
		return SingletonMap.getInstanceOf(UiState.class);
	}

// -----------------------> Public methods

	public boolean			wasConsoleUsed()
	{
		return wasConsoleUsed;
	}

	public boolean			wasGuiUsed()
	{
		return wasGuiUsed;
	}

	public void				markConsoleUsed()
	{
		wasConsoleUsed = true;
	}

	public void				markGuiUsed()
	{
		wasGuiUsed = true;
	}
}