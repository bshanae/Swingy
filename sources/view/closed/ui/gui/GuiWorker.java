package view.closed.ui.gui;

import view.closed.ui.UiWorker;

import javax.swing.*;

public abstract class		GuiWorker extends UiWorker
{
	protected static void	showInFrame(JPanel panel)
	{
		GuiServer.getInstance().execute(new GuiTasks.ShowInFrame(panel));
	}

	protected static void	showInDialog(JPanel panel)
	{
		GuiServer.getInstance().execute(new GuiTasks.ShowInDialog(panel));
	}
}
