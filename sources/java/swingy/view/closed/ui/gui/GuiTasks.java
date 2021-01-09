package swingy.view.closed.ui.gui;

import swingy.application.patterns.ServerTask;
import swingy.application.utils.Point;
import lombok.Getter;

import javax.swing.*;

public abstract class				GuiTasks
{
	public interface				Abstract extends ServerTask {}

	public static class				Terminate implements Abstract {}

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
		private final Point			size;

		@Getter
		private final JPanel		panel;

		public 						ShowInDialog
									(
										String title,
										boolean buildNewDialog,
										Point size,
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
