package view.closed.utils;

import application.patterns.SingletonMap;
import view.open.Signals;
import view.open.View;

public class					UiTerminator
{
// ---------------------------> Attributes

	private boolean				isConsoleTerminated;
	private boolean				isGuiTerminated;

// ---------------------------> Properties

	public static UiTerminator	getInstance()
	{
		return SingletonMap.getInstanceOf(UiTerminator.class);
	}

// ---------------------------> Public methods

	public void 				markConsoleTerminated()
	{
		isConsoleTerminated = true;
		sendSignalIfAllTerminated();
	}

	public void					markGuiTerminated()
	{
		isGuiTerminated = true;
		sendSignalIfAllTerminated();
	}

// ---------------------------> Private methods

	private void				sendSignalIfAllTerminated()
	{
		if (isConsoleTerminated && isGuiTerminated)
			View.getInstance().sendSignal(new Signals.FinishedTermination());
	}
}
