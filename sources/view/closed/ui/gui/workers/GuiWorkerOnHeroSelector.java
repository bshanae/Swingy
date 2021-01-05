package view.closed.ui.gui.workers;

import model.open.Pockets;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSettings;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class					GuiWorkerOnHeroSelector extends GuiWorker
{
// ---------------------------> Constants

	private static final int	GAP_FROM_TOP_TO_TITLE = 60;
	private static final int	GAP_FROM_TITLE_TO_HERO_PANELS = 60;
	private static final int	GAP_BETWEEN_HERO_PANELS = 5;
	private static final int	GAP_FROM_HERO_PANELS_TO_BOTTOM = 50;

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
		String					layoutConfig;
		String					columnConfig;
		String					rowConfig;
		String					heroPanelConfig;

		JPanel					panel;

		layoutConfig = "fill, wrap 1";
		columnConfig = "[center]";

		rowConfig = String.format
			(
				"%d[]%d[]%d[]%d[]%d[]%d",
				GAP_FROM_TOP_TO_TITLE,
				GAP_FROM_TITLE_TO_HERO_PANELS,
				GAP_BETWEEN_HERO_PANELS,
				GAP_BETWEEN_HERO_PANELS,
				GAP_BETWEEN_HERO_PANELS,
				GAP_FROM_HERO_PANELS_TO_BOTTOM
			);

		heroPanelConfig = "growx, width ::600, height 60";

		panel = new JPanel();
		panel.setLayout(new MigLayout(layoutConfig, columnConfig, rowConfig));

		{
			panel.add(buildTitle());
			panel.add(buildHeroPanel(0), heroPanelConfig);
			panel.add(buildHeroPanel(1), heroPanelConfig);
			panel.add(buildHeroPanel(2), heroPanelConfig);
			panel.add(buildHeroPanel(3), heroPanelConfig);
		}

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

	private JPanel				buildHeroPanel(int heroIndex)
	{
		Pockets.Hero			hero;

		hero = getHeroAt(heroIndex);
		return hero != null ? buildExistingHeroPanel(hero, heroIndex) : buildEmptyHeroPanel(heroIndex);
	}

	private JPanel				buildExistingHeroPanel(Pockets.Hero hero, int heroIndex)
	{
		JPanel					panel;

		JLabel					nameLabel;

		ButtonId				infoButtonId;
		JButton					infoButton;

		ButtonId				deleteButtonId;
		JButton					deleteButton;

		ButtonId				selectButtonId;
		JButton					selectButton;

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 8 10 8 10"));
		panel.setBorder(LineBorder.createGrayLineBorder());

		nameLabel = new JLabel(hero.getName());
		nameLabel.setFont(new Font(GuiSettings.FONT_NAME, Font.BOLD, 25));

		infoButtonId = ButtonId.HERO_SELECTOR_INFO_0.applyOffset(heroIndex);
		infoButton = new JButton("Info");
		infoButton.addActionListener(new GuiSignalSender(infoButtonId));

		deleteButtonId = ButtonId.HERO_SELECTOR_DELETE_0.applyOffset(heroIndex);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new GuiSignalSender(deleteButtonId));

		selectButtonId = ButtonId.HERO_SELECTOR_SELECT_0.applyOffset(heroIndex);
		selectButton = new JButton("Select");
		selectButton.addActionListener(new GuiSignalSender(selectButtonId));

		panel.add(nameLabel, "wrap");
		panel.add(infoButton, "push, aligny 50%");
		panel.add(deleteButton);
		panel.add(selectButton);

		return panel;
	}

	private JPanel				buildEmptyHeroPanel(int heroIndex)
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

		buttonId = ButtonId.HERO_SELECTOR_CREATE_0.applyOffset(heroIndex);

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
