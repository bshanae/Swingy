package view.closed.system.workers;

import model.open.Requests;
import view.closed.system.SystemWorker;
import view.closed.system.utils.UiState;
import view.closed.ui.UiMode;
import view.closed.ui.console.ConsoleServer;
import view.closed.ui.console.ConsoleTasks;
import view.closed.ui.gui.GuiServer;
import view.closed.ui.gui.GuiTasks;

public class SystemWorkerOnSwitchToConsole extends SystemWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		if (UiState.getInstance().wasGuiUsed())
			GuiServer.getInstance().execute(new GuiTasks.Disable());

		UiState.getInstance().markConsoleUsed();
		UiMode.setCurrentMode(UiMode.CONSOLE);
	}
}
