package view.closed.ui.gui.workers;

import application.utils.Point;
import lombok.Getter;
import model.open.Pockets;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSettings;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;
import java.awt.*;

public class					GuiWorkerOnHeroInfo extends GuiWorker
{
// ---------------------------> Nested types

	private static class		DictionaryPanelBuilder
	{
		// -------------------> Attributes

		private Font			fontForKey;
		private Font			fontForValue;

		@Getter
		private JPanel			panel;

		// -------------------> Constructor

		public 					DictionaryPanelBuilder()
		{
			initializeFonts();
			initializePanel();
		}

		// -------------------> Public methods

		private void			put(String key, String value)
		{
			panel.add(buildLabelWithFont(key + " :", fontForKey), "push");
			panel.add(buildLabelWithFont(value, fontForValue), "wrap");
		}

		private void			put(String key, int value)
		{
			panel.add(buildLabelWithFont(key + " :", fontForKey), "push");
			panel.add(buildLabelWithFont(((Integer)value).toString(), fontForValue), "wrap");
		}

		// -------------------> Private methods

		private void			initializeFonts()
		{
			fontForKey = new Font(GuiSettings.FONT_NAME, Font.BOLD, 15);
			fontForValue = new Font(GuiSettings.FONT_NAME, Font.PLAIN, 15);
		}

		private void			initializePanel()
		{
			panel = new JPanel();
			panel.setLayout(new MigLayout());
		}

		private JLabel			buildLabelWithFont(String text, Font font)
		{
			JLabel				label;

			label = new JLabel();
			label.setText(text);
			label.setFont(font);

			return label;
		}
	}

// ---------------------------> Attributes

	private Requests.HeroInfo	request;

// ---------------------------> Public

	@Override
	public void					execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog("Hero info", new Point(280, 320), buildMainPanel());
	}

// ---------------------------> Private : UI

	private JPanel				buildMainPanel()
	{
		JPanel					panel;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill", "", "[]push[]"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.HERO_INFO_OK));

		panel.add(buildInfoPanel(), "width 250!, height 200!, center, wrap");
		panel.add(button, "center");

		return panel;
	}

	private JPanel				buildInfoPanel()
	{
		DictionaryPanelBuilder	builder;

		builder = new DictionaryPanelBuilder();

		builder.put("Name", request.getHero().getName());
		builder.put("Class", request.getHero().getHeroClass());
		builder.put("Level", request.getHero().getLevel());
		builder.put("Experience", request.getHero().getExperience());

		builder.put("Helm", getArtefactName(request.getInventory().getHelm()));
		builder.put("Armor", getArtefactName(request.getInventory().getArmor()));
		builder.put("Weapon", getArtefactName(request.getInventory().getWeapon()));

		return builder.getPanel();
	}

	private String				getArtefactName(Pockets.Artefact artefact)
	{
		return artefact != null ? artefact.getName() : "none";
	}

// ---------------------------> Private : Service

	private void				parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.HeroInfo)request;
	}
}
