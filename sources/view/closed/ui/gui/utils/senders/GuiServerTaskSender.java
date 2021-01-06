package view.closed.ui.gui.utils.senders;

import view.closed.ui.gui.GuiServer;
import view.closed.ui.gui.GuiTasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class						GuiServerTaskSender implements ActionListener
{
	private final GuiTasks.Abstract	task;

	public							GuiServerTaskSender(GuiTasks.Abstract task)
	{
		this.task = task;
	}

	@Override
	public void						actionPerformed(ActionEvent event)
	{
		GuiServer.getInstance().execute(task);
	}
}
