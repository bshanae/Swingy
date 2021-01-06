package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSettings;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;
import view.open.Signals;
import view.open.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class					GuiWorkerOnClassSelector extends GuiWorker
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
		LayoutManager			layout;

		layout = new MigLayout("fill, wrap 1", "[center]", "push[]push[]push");

		panel = new JPanel();
		panel.setLayout(layout);

		panel.add(buildTitle());
		panel.add(buildClassesPanel(), "grow");

		return panel;
	}

	private JComponent			buildTitle()
	{
		JLabel					title;

		title = new JLabel("Select hero class");
		title.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 40));

		return title;
	}

	private JPanel				buildClassesPanel()
	{
		final String			ITEM_CONFIG = "grow, width 450!, height 60!";

		JPanel					panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, wrap 1", "[center]"));

		panel.add(buildClassButton("Warrior"), ITEM_CONFIG);
		panel.add(buildClassButton("Swordsman"), ITEM_CONFIG);
		panel.add(buildClassButton("Assassin"), ITEM_CONFIG);
		panel.add(buildClassButton("Mage"), ITEM_CONFIG);

		return panel;
	}

	private JComponent			buildClassButton(String className)
	{
		JPanel					panel;
		JLabel					label;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 5 10 2 10", "[]push[]", "[center]"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		label = new JLabel(className);
		label.setFont(new Font(GuiSettings.FONT_NAME, Font.PLAIN, 22));

		button = new JButton("Select");
		button.addActionListener(new GuiSignalSender(ButtonId.CLASS_SELECTOR_SELECT, className));

		panel.add(label);
		panel.add(button);

		return panel;
	}
}
