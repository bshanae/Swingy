package view.closed.ui.gui.workers;

import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.open.ButtonId;

import javax.swing.*;
import java.awt.*;

public class					GuiWorkerOnQuestion extends GuiWorker
{
// ---------------------------> Attributes

	private Requests.Question		request;

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
		JButton					buttonAnswerA;
		JButton					buttonAnswerB;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		buttonAnswerA = new JButton(request.answerA);
		buttonAnswerA.addActionListener(new GuiSignalSender(ButtonId.QUESTION_ANSWER_A));

		buttonAnswerB = new JButton(request.answerB);
		buttonAnswerB.addActionListener(new GuiSignalSender(ButtonId.QUESTION_ANSWER_B));

		panel.add(new JLabel(request.question), "wrap");
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
