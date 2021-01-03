package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;

public class					GuiWorkerOnInfo extends GuiWorker
{
// ---------------------------> Attributes

	private Requests.Info		request;

// ---------------------------> Public methods

	@Override
	public void					execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInDialog(buildPanel());
	}

// --------------------------->	Private methods : UI

	private JPanel				buildPanel()
	{
		JPanel					panel;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.INFO_OK));

		panel.add(new JLabel(request.message), "wrap");
		panel.add(button);

		return panel;
	}

// --------------------------->	Private methods : Service

	private void				parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Info)request;
	}
}
