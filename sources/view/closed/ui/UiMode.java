package view.closed.ui;

public enum					UiMode
{
	CONSOLE,
	GUI;

	private static UiMode	currentMode;

	public static UiMode	getCurrentMode()
	{
		return currentMode;
	}

	public static void		setCurrentMode(UiMode currentMode)
	{
		UiMode.currentMode = currentMode;
	}
}
