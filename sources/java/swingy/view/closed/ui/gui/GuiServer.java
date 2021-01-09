package swingy.view.closed.ui.gui;

import swingy.application.patterns.SingletonMap;
import swingy.application.patterns.Server;
import com.formdev.flatlaf.FlatIntelliJLaf;
import swingy.view.closed.ui.gui.utils.GuiSettings;
import swingy.view.closed.utils.UiTerminator;
import swingy.view.open.ButtonId;
import swingy.view.open.Signals;
import swingy.view.open.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

public class							GuiServer extends Server<GuiTasks.Abstract>
{
// -----------------------------------> Exceptions

	public static class					CantInitializeTheme extends RuntimeException {}

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

	private void						execute(GuiTasks.Terminate task)
	{
		EventQueue.invokeLater(new Terminator());
	}

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

	private class						Terminator implements Runnable
	{
		@Override
		public void						run()
		{
			if (dialog != null)
				dialog.dispose();

			frame.dispose();
			UiTerminator.getInstance().markGuiTerminated();
		}
	}

	private class						Enabler implements Runnable
	{
		@Override
		public void						run()
		{
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
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
		private class					ExitListener extends WindowAdapter
		{
			@Override
			public void					windowClosing(java.awt.event.WindowEvent event)
			{
				View.getInstance().sendSignal(new Signals.Gui(ButtonId.FRAME_EXIT));
				event.getWindow().dispose();
			}
		}

		public							Constructor()
		{
			if (!FlatIntelliJLaf.install())
				throw new CantInitializeTheme();
		}

		@Override
		public void						run()
		{
			frame = new JFrame();

			frame.setTitle(GuiSettings.WINDOW_TITLE);
			frame.setSize(GuiSettings.WINDOW_WIDTH, GuiSettings.WINDOW_HEIGHT);
			frame.setResizable(false);

			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new ExitListener());

			frame.setLayout(null);
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

		private boolean					didRebuild;

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

				didRebuild = true;
			}
		}

		private void					setTitle()
		{
			dialog.setTitle(task.getTitle());
		}

		private void					setSize()
		{
			dialog.setSize(task.getSize().x, task.getSize().y);
		}

		private void					setPanel()
		{
			dialog.setContentPane(task.getPanel());
		}

		private void					center()
		{
			if (didRebuild)
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
