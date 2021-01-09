package swingy.view.closed.ui.gui.workers;

import swingy.model.open.Requests;
import net.miginfocom.swing.MigLayout;
import swingy.view.closed.ui.gui.GuiWorker;
import swingy.view.closed.ui.gui.utils.FontRedactor;
import swingy.view.open.ButtonId;
import swingy.view.open.Signals;
import swingy.view.open.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class					GuiWorkerOnNameEntry extends GuiWorker
{
// ---------------------------> Public methods

	@Override
	public void					execute(Requests.Abstract request)
	{
		showInFrame(buildMainPanel());
	}

// ---------------------------> Private methods

	private JPanel				buildMainPanel()
	{
		JPanel					panel;
		JLabel					title;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fillx", "[center]", "push[]push[]push"));

		title = new JLabel("Enter hero name");
		title.setFont(new FontRedactor(title.getFont()).changeStyle(Font.BOLD).changeSize(40).get());

		panel.add(title, "wrap");
		panel.add(buildContent(), "width 400px!, height 150px!");

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
		textField.setFont(new FontRedactor(textField.getFont()).changeSize(22).get());

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
