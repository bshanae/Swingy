package view.closed.ui.gui;

import application.patterns.server.ServerTask;
import lombok.Getter;
import view.closed.ui.gui.utils.GuiDialogSize;

import javax.swing.*;

public abstract class				GuiTasks
{
// -------------------------------> Nested types

	public interface				Abstract extends ServerTask {}

	public static class				Enable implements Abstract {}

	public static class				Disable implements Abstract {}

	public static class				ShowInFrame implements Abstract
	{
		@Getter
		private final JPanel		panel;

		public 						ShowInFrame(JPanel panel)
		{
			this.panel = panel;
		}
	}

	public static class				ShowInDialog implements Abstract
	{
		@Getter
		private final String		title;

		@Getter
		private final boolean		buildNewDialog;

		@Getter
		private final GuiDialogSize	size;

		@Getter
		private final JPanel		panel;

		public 						ShowInDialog
									(
										String title,
										boolean buildNewDialog,
										GuiDialogSize size,
										JPanel panel
									)
		{
			this.title = title;
			this.buildNewDialog = buildNewDialog;
			this.size = size;
			this.panel = panel;
		}
	}
}
