package view.closed.ui.gui;

import application.patterns.server.ServerTask;

import javax.swing.*;

public abstract class		GuiTasks
{
	public interface		Abstract extends ServerTask {}

	public static class		Enable implements Abstract {}

	public static class		Disable implements Abstract {}

	public static class		ShowInFrame implements Abstract
	{
		public final JPanel	panel;

		public 				ShowInFrame(JPanel panel)
		{
			this.panel = panel;
		}
	}

	public static class		ShowInDialog implements Abstract
	{
		public final JPanel	panel;

		public 				ShowInDialog(JPanel panel)
		{
			this.panel = panel;
		}
	}
}
