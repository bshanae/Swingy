package view.closed.ui.gui;

import application.patterns.SingletonMap;
import application.patterns.server.Server;
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
		EventQueue.invokeLater(new FrameUpdater(task.panel));
	}

	private void						execute(GuiTasks.ShowInDialog task)
	{
		EventQueue.invokeLater(new DialogUpdater(task.panel));
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
			buildFrame();
			buildDialog();
		}

		private void					buildFrame()
		{
			frame = new JFrame();

			frame.setTitle(GuiSettings.WINDOW_TITLE);
			frame.setSize(GuiSettings.WINDOW_WIDTH, GuiSettings.WINDOW_HEIGHT);
			frame.setResizable(false);

			frame.setLayout(null);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		}

		private void					buildDialog()
		{
			dialog = new JDialog(frame, "", Dialog.ModalityType.DOCUMENT_MODAL);

			dialog.setResizable(false);
			dialog.setSize(GuiSettings.DIALOG_WIDTH, GuiSettings.DIALOG_HEIGHT);
			dialog.setLocationRelativeTo(frame);
		}
	}

	private class						FrameUpdater implements Runnable
	{
		private final JPanel			panel;

		public 							FrameUpdater(JPanel panel)
		{
			this.panel = panel;
		}

		@Override
		public void						run()
		{
			frame.setContentPane(panel);
			dialog.setVisible(false);

			frame.revalidate();
			frame.repaint();
		}
	}

	private class						DialogUpdater implements Runnable
	{
		private final JPanel			panel;

		public 							DialogUpdater(JPanel panel)
		{
			this.panel = panel;
		}

		@Override
		public void						run()
		{
			dialog.setContentPane(panel);
			dialog.setVisible(true);

			frame.revalidate();
			frame.repaint();
		}
	}
}
