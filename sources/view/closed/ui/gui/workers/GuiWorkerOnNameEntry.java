package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSettings;
import view.open.ButtonId;
import view.open.Signals;
import view.open.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class					GuiWorkerOnNameEntry extends GuiWorker
{
// ---------------------------> Constants

	private static final int	GAP_FROM_TOP_TO_TITLE = 90;
	private static final int	GAP_FROM_TITLE_TO_CONTENT = 90;

// ---------------------------> Public methods

	@Override
	public void					execute(Requests.Abstract request)
	{
		showInFrame(buildMainPanel());
	}

// ---------------------------> Private methods

	private JPanel				buildMainPanel()
	{
		String					layoutConfig;
		String					columnConfig;
		String					rowConfig;

		JPanel					panel;
		JLabel					title;

		layoutConfig = "fillx";
		columnConfig = "[center]";
		rowConfig = String.format("%d[]%d[]", GAP_FROM_TOP_TO_TITLE, GAP_FROM_TITLE_TO_CONTENT);

		panel = new JPanel();
		panel.setLayout(new MigLayout(layoutConfig, columnConfig, rowConfig));

		title = new JLabel("Enter hero name");
		title.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 40));

		panel.add(title, "wrap");
		panel.add(buildContent(), "width 400px!, height 200px!");

		return panel;
	}

	private JComponent			buildContent()
	{
		JPanel					panel;
		JTextField				textField;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 25 15 25 15", "", "[]push[]"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		textField = new JTextField();
		textField.setFont(new Font(GuiSettings.FONT_NAME, Font.PLAIN, 22));
		textField.setBorder
		(
			BorderFactory.createCompoundBorder
			(
				BorderFactory.createEmptyBorder(),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)
			)
		);

		button = new JButton("Enter");
		button.addActionListener
		(
			new					ActionListener()
			{
				@Override
				public void		actionPerformed(ActionEvent event)
				{
					Signals.Gui	signal;

					signal = new Signals.Gui(ButtonId.NAME_ENTRY_ENTER, textField.getText());
					View.getInstance().sendSignal(signal);
				}
			}
		);

		panel.add(textField, "growx, height 45px!, wrap");
		panel.add(button, "right");

		return panel;
	}
}
