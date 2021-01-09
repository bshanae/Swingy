package swingy.view.closed.ui.gui.workers;

import swingy.model.open.Pockets;
import swingy.model.open.Requests;
import net.miginfocom.swing.MigLayout;
import swingy.view.closed.ui.gui.GuiWorker;
import swingy.view.closed.ui.gui.utils.FontRedactor;
import swingy.view.closed.ui.gui.utils.GuiSignalSender;
import swingy.view.open.ButtonId;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class					GuiWorkerOnHeroSelector extends GuiWorker
{
// ---------------------------> Attributes

	private List<Pockets.Hero> heroes;

// ---------------------------> Public

	@Override
	public void					execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInFrame(buildMainPanel());
	}

// ---------------------------> Private : UI

	private JPanel				buildMainPanel()
	{
		JPanel					panel;
		LayoutManager			layout;

		layout = new MigLayout("fill, wrap 1", "center", "push[]push[]push");

		panel = new JPanel();
		panel.setLayout(layout);

		panel.add(buildTitle());
		panel.add(buildHeroesPanel(), "growx");

		return panel;
	}

	private JComponent			buildTitle()
	{
		JLabel					label;

		label = new JLabel("Select hero");
		label.setFont(new FontRedactor(label.getFont()).changeStyle(Font.BOLD).changeSize(40).get());
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		return label;
	}

	private JPanel				buildHeroesPanel()
	{
		final String			ITEM_CONFIG = "center, growx, width ::600";

		JPanel					panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fillx, wrap 1"));

		for (Pockets.Hero hero : heroes)
			panel.add(buildHeroPanel(hero), ITEM_CONFIG);

		if (heroes.size() < 4)
			panel.add(buildEmptyHeroPanel(), ITEM_CONFIG);

		return panel;
	}

	private JPanel				buildHeroPanel(Pockets.Hero hero)
	{
		JPanel					panel;
		JLabel					nameLabel;
		JButton					infoButton;
		JButton					deleteButton;
		JButton					selectButton;

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 8 10 8 10"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		nameLabel = new JLabel(hero.getName());
		nameLabel.setFont(new FontRedactor(nameLabel.getFont()).changeStyle(Font.BOLD).changeSize(25).get());

		infoButton = new JButton("Info");
		infoButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_INFO, hero.getName()));

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_DELETE, hero.getName()));

		selectButton = new JButton("Select");
		selectButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_SELECT, hero.getName()));
		selectButton.setEnabled(!hero.didFinishGame());

		panel.add(nameLabel, "wrap");
		panel.add(infoButton, "push, aligny 50%");
		panel.add(deleteButton);
		panel.add(selectButton);

		return panel;
	}

	private JPanel				buildEmptyHeroPanel()
	{
		JPanel					panel;
		JLabel					nameLabel;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 8 10 8 10"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		nameLabel = new JLabel("Empty");
		nameLabel.setFont(new FontRedactor(nameLabel.getFont()).changeStyle(Font.BOLD).changeSize(25).get());

		button = new JButton("Create");
		button.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_CREATE));

		panel.add(nameLabel, "push");
		panel.add(button);

		return panel;
	}

// ---------------------------> Private : Service

	private void				parseRequest(Requests.Abstract request)
	{
		heroes = ((Requests.HeroSelector)request).getHeroes();
	}
}
