package view.closed.ui.gui.workers;

import model.open.Pockets;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSettings;
import view.closed.ui.gui.utils.senders.GuiSignalSender;
import view.open.ButtonId;

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
		Font					font;
		JLabel					label;

		font = new Font(GuiSettings.FONT_NAME, Font.BOLD, 40);

		label = new JLabel("Select hero");
		label.setFont(font);
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
		nameLabel.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 25));

		infoButton = new JButton("Info");
		infoButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_INFO, hero.getName()));

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_DELETE, hero.getName()));

		selectButton = new JButton("Select");
		selectButton.addActionListener(new GuiSignalSender(ButtonId.HERO_SELECTOR_SELECT, hero.getName()));

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
		ButtonId				buttonId;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 8 10 8 10"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		nameLabel = new JLabel("Empty");
		nameLabel.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 25));

		// TODO
		buttonId = ButtonId.HERO_SELECTOR_CREATE;

		button = new JButton("Create");
		button.addActionListener(new GuiSignalSender(buttonId));

		panel.add(nameLabel, "push");
		panel.add(button);

		return panel;
	}

// ---------------------------> Private : Service

	private void				parseRequest(Requests.Abstract request)
	{
		Requests.HeroSelector	heroSelectorRequest;

		assert request instanceof Requests.HeroSelector;
		heroSelectorRequest = (Requests.HeroSelector)request;

		heroes = heroSelectorRequest.getHeroes();
	}

	private Pockets.Hero		getHeroAt(int index)
	{
		if (index < heroes.size())
			return heroes.get(index);

		return null;
	}
}
