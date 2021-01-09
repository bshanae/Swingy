package swingy.view.closed.system.workers;

import swingy.model.open.Requests;
import swingy.view.closed.system.SystemWorker;
import swingy.view.closed.system.utils.UiState;
import swingy.view.closed.ui.UiMode;
import swingy.view.closed.ui.console.ConsoleServer;
import swingy.view.closed.ui.console.ConsoleTasks;
import swingy.view.closed.ui.gui.GuiServer;
import swingy.view.closed.ui.gui.GuiTasks;

public class		SystemWorkerOnSwitchToGui extends SystemWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		if (UiState.getInstance().wasConsoleUsed())
			ConsoleServer.getInstance().execute(new ConsoleTasks.Clean());

		GuiServer.getInstance().execute(new GuiTasks.Enable());
		UiState.getInstance().markGuiUsed();

		UiMode.setCurrentMode(UiMode.GUI);
	}
}
