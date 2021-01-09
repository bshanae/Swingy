package swingy.view.closed.ui.gui.workers;

import swingy.application.utils.Point;
import swingy.model.open.Pockets;
import swingy.model.open.Requests;
import net.miginfocom.swing.MigLayout;
import swingy.view.closed.ui.gui.GuiWorker;
import swingy.view.closed.ui.gui.utils.GuiDictionaryPanelBuilder;
import swingy.view.closed.ui.gui.utils.GuiSignalSender;
import swingy.view.open.ButtonId;

import javax.swing.*;

public class						GuiWorkerOnHeroInventory extends GuiWorker
{
// -------------------------------> Attributes

	private Requests.HeroInventory	request;

// -------------------------------> Public

	@Override
	public void						execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog("Hero inventory", new Point(260, 180), buildMainPanel());
	}

// -------------------------------> Private : UI

	private JPanel					buildMainPanel()
	{
		JPanel						panel;
		JButton						button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill", "", "[]push[]"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.HERO_INVENTORY_OK));

		panel.add(buildInfoPanel(), "width 230!, center, wrap");
		panel.add(button, "center");

		return panel;
	}

	private JPanel					buildInfoPanel()
	{
		GuiDictionaryPanelBuilder	builder;

		builder = new GuiDictionaryPanelBuilder();

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
		this.request = (Requests.HeroInventory)request;
	}
}
