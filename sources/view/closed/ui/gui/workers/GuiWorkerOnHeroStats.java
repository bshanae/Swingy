package view.closed.ui.gui.workers;

import application.utils.Point;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiDictionaryPanelBuilder;
import view.closed.ui.gui.utils.senders.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;

public class						GuiWorkerOnHeroStats extends GuiWorker
{
// -------------------------------> Attributes

	private Requests.HeroStats		request;

// -------------------------------> Public

	@Override
	public void						execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog("Hero stats", new Point(260, 180), buildMainPanel());
	}

// -------------------------------> Private : UI

	private JPanel					buildMainPanel()
	{
		JPanel						panel;
		JButton						button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill", "", "[]push[]"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.HERO_STATS_OK));

		panel.add(buildInfoPanel(), "width 230!, center, wrap");
		panel.add(button, "center");

		return panel;
	}

	private JPanel					buildInfoPanel()
	{
		GuiDictionaryPanelBuilder	builder;

		builder = new GuiDictionaryPanelBuilder();

		builder.put("Class", request.getHero().getHeroClass());
		builder.put("Level", request.getHero().getLevel());
		builder.put("Experience", request.getHero().getExperience());

		return builder.getPanel();
	}

// -------------------------------> Private : Service

	private void					parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.HeroStats)request;
	}
}
