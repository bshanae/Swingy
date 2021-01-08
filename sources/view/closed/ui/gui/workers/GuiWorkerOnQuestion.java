package view.closed.ui.gui.workers;

import application.utils.Point;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;

public class					GuiWorkerOnQuestion extends GuiWorker
{
// ---------------------------> Attributes

	private Requests.Question	request;

// ---------------------------> Public methods

	@Override
	public void					execute(Requests.Abstract request)
	{
		parseRequest(request);
		showInNewDialog("Question", new Point(380, 140), buildPanel());
	}

// --------------------------->	Private methods : UI

	private JPanel				buildPanel()
	{
		JPanel					panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, wrap 1", "", "[]push[]"));

		panel.add(buildText(), "grow");
		panel.add(buildButtons(), "grow");

		return panel;
	}

	private JComponent			buildText()
	{
		JLabel					label;

		label = new JLabel();
		label.setText(String.format("<HTML>%s</HTML>", request.getQuestion()));
		label.setHorizontalAlignment(JLabel.CENTER);

		return label;
	}

	private JComponent			buildButtons()
	{
		JPanel					panel;
		JButton					buttonAnswerA;
		JButton					buttonAnswerB;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 2"));

		buttonAnswerA = new JButton(request.getAnswerA());
		buttonAnswerA.addActionListener(new GuiSignalSender(ButtonId.QUESTION_ANSWER_A));

		buttonAnswerB = new JButton(request.getAnswerB());
		buttonAnswerB.addActionListener(new GuiSignalSender(ButtonId.QUESTION_ANSWER_B));

		panel.add(buttonAnswerA, "push");
		panel.add(buttonAnswerB);

		return panel;
	}

// --------------------------->	Private methods : Service

	private void				parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Question)request;
	}
}
