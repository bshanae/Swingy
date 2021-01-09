package swingy.view.closed.ui.gui.workers;

import swingy.model.open.Requests;
import net.miginfocom.swing.MigLayout;
import swingy.view.closed.ui.gui.GuiWorker;
import swingy.view.closed.ui.gui.utils.FontRedactor;
import swingy.view.closed.ui.gui.utils.GuiSignalSender;
import swingy.view.open.ButtonId;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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
		title.setFont(new FontRedactor(title.getFont()).changeStyle(Font.BOLD).changeSize(40).get());
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
		label.setFont(new FontRedactor(label.getFont()).changeSize(22).get());

		button = new JButton("Select");
		button.addActionListener(new GuiSignalSender(ButtonId.CLASS_SELECTOR_SELECT, className));

		panel.add(label);
		panel.add(button);

		return panel;
	}
}
