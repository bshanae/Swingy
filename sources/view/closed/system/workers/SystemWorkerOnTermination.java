package view.closed.system.workers;

import model.open.Requests;
import view.closed.system.SystemWorker;
import view.closed.ui.UiMode;
import view.closed.ui.console.ConsoleServer;
import view.closed.ui.console.ConsoleTasks;
import view.closed.ui.gui.GuiServer;
import view.closed.ui.gui.GuiTasks;

public class		SystemWorkerOnTermination extends SystemWorker
{
	@Override
	public void		execute(Requests.Abstract request)
	{
		switch (UiMode.getCurrentMode())
		{
			case CONSOLE:
				ConsoleServer.getInstance().execute(new ConsoleTasks.Terminate());
				break;

			case GUI:
				GuiServer.getInstance().execute(new GuiTasks.Terminate());
				break;
		}
	}
}
