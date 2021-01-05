package view.closed.ui.gui;

import application.utils.Point;
import view.closed.ui.UiWorker;

import javax.swing.*;

public abstract class		GuiWorker extends UiWorker
{
// -----------------------> Protected methods

	protected static void	showInFrame(JPanel panel)
	{
		GuiServer.getInstance().execute(new GuiTasks.ShowInFrame(panel));
	}

	protected static void	showInDialog(String title, Point size, JPanel panel)
	{
		boolean				buildNewDialog;

		buildNewDialog = !GuiWorkerFactory.getInstance().isLastRequestWasSame();
		showInDialog(title, buildNewDialog, size, panel);
	}

	protected static void	showInNewDialog(String title, Point size, JPanel panel)
	{
		showInDialog(title, true, size, panel);
	}

// -----------------------> Private methods

	private static void		showInDialog
							(
								String title,
								boolean buildNewDialog,
								Point size,
								JPanel panel
							)
	{
		GuiServer.getInstance().execute(new GuiTasks.ShowInDialog(title, buildNewDialog, size, panel));
	}
}