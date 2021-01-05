package view.closed.ui.gui;

import application.patterns.SingletonMap;
import application.patterns.server.Server;
import application.service.Debug;
import application.service.Exceptions;
import application.service.LogGroup;
import view.closed.ui.gui.utils.GuiDialogSize;
import view.closed.ui.gui.utils.GuiSettings;

import javax.swing.*;
import java.awt.*;

public class							GuiServer extends Server<GuiTasks.Abstract>
{
// -----------------------------------> Attributes

	private JFrame						frame;
	private JDialog						dialog;

// -----------------------------------> Public methods

	public static GuiServer				getInstance()
	{
		return SingletonMap.getInstanceOf(GuiServer.class);
	}

	public								GuiServer()
	{
		EventQueue.invokeLater(new Constructor());
	}

// -----------------------------------> Private methods : Execute

	private void						execute(GuiTasks.Enable task)
	{
		EventQueue.invokeLater(new Enabler());
	}

	private void						execute(GuiTasks.Disable task)
	{
		EventQueue.invokeLater(new Disabler());
	}

	private void						execute(GuiTasks.ShowInFrame task)
	{
		EventQueue.invokeLater(new FrameUpdater(task));
	}

	private void						execute(GuiTasks.ShowInDialog task)
	{
		EventQueue.invokeLater(new DialogUpdater(task));
	}

// -----------------------------------> Nested classes for GUI queuing

	private class						Enabler implements Runnable
	{
		@Override
		public void						run()
		{
			frame.setVisible(true);
		}
	}

	private class						Disabler implements Runnable
	{
		@Override
		public void						run()
		{
			frame.setVisible(false);
		}
	}

	private class						Constructor implements Runnable
	{
		@Override
		public void						run()
		{
			frame = new JFrame();

			frame.setTitle(GuiSettings.WINDOW_TITLE);
			frame.setSize(GuiSettings.WINDOW_WIDTH, GuiSettings.WINDOW_HEIGHT);
			frame.setResizable(false);

			frame.setLayout(null);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		}
	}

	private class						FrameUpdater implements Runnable
	{
		private final
		GuiTasks.ShowInFrame			task;

		public 							FrameUpdater(GuiTasks.ShowInFrame task)
		{
			this.task = task;
		}

		@Override
		public void						run()
		{
			frame.setContentPane(task.getPanel());
			if (dialog != null)
				dialog.setVisible(false);

			frame.revalidate();
			frame.repaint();
		}
	}

	private class						DialogUpdater implements Runnable
	{
		// ---------------------------> Attributes

		private final
		GuiTasks.ShowInDialog			task;

		// ---------------------------> Constructor

		public 							DialogUpdater(GuiTasks.ShowInDialog task)
		{
			this.task = task;
		}

		// ---------------------------> Public methods

		@Override
		public void						run()
		{
			rebuildIfNeeded();

			setTitle();
			setSize();
			setPanel();

			center();
			redraw();
		}

		// ---------------------------> Private methods

		private void					rebuildIfNeeded()
		{
			if (task.isBuildNewDialog() || dialog == null)
			{
				if (dialog != null)
					dialog.dispose();

				dialog = new JDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL);
				dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				dialog.setResizable(false);
			}
		}

		private void					setTitle()
		{
			dialog.setTitle(task.getTitle());
		}

		private void					setSize()
		{
			dialog.setSize(task.getSize().getWidth(), task.getSize().getHeight());
		}

		private void					setPanel()
		{
			dialog.setContentPane(task.getPanel());
		}

		private void					center()
		{
			dialog.setLocationRelativeTo(frame);
		}

		private void					redraw()
		{
			dialog.setVisible(true);
			frame.revalidate();
			frame.repaint();
		}
	}
}
