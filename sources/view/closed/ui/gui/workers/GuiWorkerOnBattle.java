package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;
import java.awt.*;

public class					GuiWorkerOnBattle extends GuiWorker
{
// ---------------------------> Attributes

	private Requests.Battle		request;

// ---------------------------> Public methods

	@Override
	public void					execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog("Battle", new application.utils.Point(700, 400), buildMainPanel());
	}

// --------------------------->	Private methods : UI

	private JPanel				buildMainPanel()
	{
		JPanel					panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		panel.add(buildLog(), "center, center, width 630!, height 300!, wrap");
		panel.add(buildButton(), "center");

		return panel;
	}

	private JComponent			buildLog()
	{
		JTextArea				textArea;
		JScrollPane				scrollPane;

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setMargin(new Insets(2,5,2,5));
		textArea.setText(getLogText());

		scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		scrollPane.getViewport().setViewPosition(new Point(0, textArea.getDocument().getLength()));

		if (!request.isBattleFinished())
		{
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		else
		{
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		}

		return scrollPane;
	}

	private JComponent			buildButton()
	{
		JButton					button;

		button = new JButton("Continue");
		button.setEnabled(request.isBattleFinished());
		button.addActionListener(new GuiSignalSender(ButtonId.BATTLE_CONTINUE));

		return button;
	}

// --------------------------->	Private methods : Service

	private void				parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Battle)request;
	}

	private String				getLogText()
	{
		StringBuilder			stringBuilder;

		stringBuilder = new StringBuilder();

		for (String line : request.getLog().lines)
			stringBuilder.append(line).append("\n");

		return stringBuilder.toString();
	}
}
