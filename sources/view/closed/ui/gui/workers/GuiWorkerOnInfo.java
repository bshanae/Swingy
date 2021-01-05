package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiTasks;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiDialogSize;
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
		showInNewDialog("Info", GuiDialogSize.SMALL, buildPanel());
	}

// --------------------------->	Private methods : UI

	private JPanel				buildPanel()
	{
		JPanel					panel;
		JButton					button;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, wrap 1", "", "[]push[]"));

		button = new JButton("Ok");
		button.addActionListener(new GuiSignalSender(ButtonId.INFO_OK));

		panel.add(buildText(), "grow");
		panel.add(button, "center");

		return panel;
	}

	private JComponent			buildText()
	{
		JLabel					label;

		label = new JLabel();
		label.setText(String.format("<HTML>%s</HTML>", request.getMessage()));
		label.setHorizontalAlignment(JLabel.CENTER);

		return label;
	}

// --------------------------->	Private methods : Service

	private void				parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Info)request;
	}
}
