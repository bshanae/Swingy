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

public class					GuiWorkerOnClassSelector extends GuiWorker
{
// ---------------------------> Constants

	private static final int	GAP_FROM_TOP_TO_TITLE = 90;
	private static final int	GAP_FROM_TITLE_TO_CONTENT = 90;
	private static final int	GAP_BETWEEN_CLASSES = 10;
	private static final int	GAP_FROM_CONTENT_TO_BOTTOM = 100;

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

		layoutConfig = "fill, wrap 1";
		columnConfig = "[center]";
		rowConfig = String.format
		(
			"%d[]%d[]%d[]%d[]%d[]%d",
			GAP_FROM_TOP_TO_TITLE,
			GAP_FROM_TITLE_TO_CONTENT,
			GAP_BETWEEN_CLASSES,
			GAP_BETWEEN_CLASSES,
			GAP_BETWEEN_CLASSES,
			GAP_FROM_CONTENT_TO_BOTTOM
		);

		panel = new JPanel();
		panel.setLayout(new MigLayout(layoutConfig, columnConfig, rowConfig));

		title = new JLabel("Select hero class");
		title.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 40));

		panel.add(title);
		panel.add(buildHeroClass("Warrior"), "grow, width ::450, height ::60");
		panel.add(buildHeroClass("Swordsman"), "grow, width ::450, height ::60");
		panel.add(buildHeroClass("Assassin"), "grow, width ::450, height ::60");
		panel.add(buildHeroClass("Mage"), "grow, width ::450, height ::60");

		return panel;
	}

	private JComponent			buildHeroClass(String className)
	{
		JPanel					panel;
		JLabel					label;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 5 10 2 10"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		label = new JLabel(className);
		label.setFont(new Font(GuiSettings.FONT_NAME, Font.PLAIN, 22));

		panel.add(label, "push");
		panel.add(new JButton("Select"));

		return panel;
	}
}
