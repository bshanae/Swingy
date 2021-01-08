package view.closed.system.workers;

import model.open.Requests;
import view.closed.system.SystemWorker;
import view.closed.system.utils.UiState;
import view.closed.ui.console.ConsoleServer;
import view.closed.ui.console.ConsoleTasks;
import view.closed.ui.gui.GuiServer;
import view.closed.ui.gui.GuiTasks;
import view.closed.utils.UiTerminator;

public class		SystemWorkerOnTermination extends SystemWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		if (UiState.getInstance().wasConsoleUsed())
			ConsoleServer.getInstance().execute(new ConsoleTasks.Terminate());
		else
			UiTerminator.getInstance().markConsoleTerminated();

		if (UiState.getInstance().wasGuiUsed())
			GuiServer.getInstance().execute(new GuiTasks.Terminate());
		else
			UiTerminator.getInstance().markGuiTerminated();
	}
}
