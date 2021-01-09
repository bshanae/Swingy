package swingy.view.closed.utils;

import swingy.application.patterns.SingletonMap;
import swingy.view.open.Signals;
import swingy.view.open.View;

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
