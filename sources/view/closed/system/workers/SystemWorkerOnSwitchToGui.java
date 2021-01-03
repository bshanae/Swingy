package view.closed.system.workers;

import model.open.Requests;
import view.closed.system.SystemWorker;
import view.closed.ui.UiMode;
import view.closed.ui.console.ConsoleServer;
import view.closed.ui.console.ConsoleTasks;
import view.closed.ui.gui.GuiServer;
import view.closed.ui.gui.GuiTasks;

public class		SystemWorkerOnSwitchToGui extends SystemWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		ConsoleServer.getInstance().execute(new ConsoleTasks.Clean());
		GuiServer.getInstance().execute(new GuiTasks.Enable());

		UiMode.setCurrentMode(UiMode.GUI);
	}
}
