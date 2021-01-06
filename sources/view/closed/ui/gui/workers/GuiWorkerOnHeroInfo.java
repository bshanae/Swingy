package view.closed.ui.gui.workers;

import application.utils.Point;
import model.open.Pockets;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiDictionaryPanelBuilder;
import view.closed.ui.gui.utils.senders.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;

public class						GuiWorkerOnHeroInfo extends GuiWorker
{
// -------------------------------> Attributes

	private Requests.HeroInfo		request;

// -------------------------------> Public

	@Override
	public void						execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog("Hero info", new Point(300, 370), buildMainPanel());
	}

// -------------------------------> Private : UI

	private JPanel					buildMainPanel()
	{
		JPanel						panel;
		JButton						button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, wrap 1", "", "[][]push[]"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.HERO_INFO_OK));

		panel.add(buildStatsPanel(), "width 270!, center");
		panel.add(buildInventoryPanel(), "width 270!, center");
		panel.add(button, "center");

		return panel;
	}

	private JPanel					buildStatsPanel()
	{
		GuiDictionaryPanelBuilder	builder;

		builder = new GuiDictionaryPanelBuilder();
		builder.getPanel().setBorder(BorderFactory.createTitledBorder("Stats"));

		builder.put("Class", request.getHero().getHeroClass());
		builder.put("Level", request.getHero().getLevel());
		builder.put("Experience", request.getHero().getExperience());

		return builder.getPanel();
	}

	private JPanel					buildInventoryPanel()
	{
		GuiDictionaryPanelBuilder	builder;

		builder = new GuiDictionaryPanelBuilder();
		builder.getPanel().setBorder(BorderFactory.createTitledBorder("Inventory"));

		builder.put("Helm", getArtefactName(request.getInventory().getHelm()));
		builder.put("Armor", getArtefactName(request.getInventory().getArmor()));
		builder.put("Weapon", getArtefactName(request.getInventory().getWeapon()));

		return builder.getPanel();
	}

	private String					getArtefactName(Pockets.Artefact artefact)
	{
		return artefact != null ? artefact.getName() : "none";
	}

// -------------------------------> Private : Service

	private void					parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.HeroInfo)request;
	}
}
