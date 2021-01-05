package view.closed.ui.gui;

import application.patterns.SingletonMap;
import application.service.Exceptions;
import model.open.Requests;
import view.closed.ui.UiMode;
import view.closed.ui.UiWorkerFactory;
import view.closed.ui.console.ConsoleWorkerFactory;
import view.closed.ui.gui.workers.*;

public class						GuiWorkerFactory extends UiWorkerFactory
{
// ------------------------------->	Attributes

	private Requests.Abstract		previousRequest;
	private boolean					isLastRequestWasSame;

// ------------------------------->	Properties

	public static GuiWorkerFactory	getInstance()
	{
		return SingletonMap.getInstanceOf(GuiWorkerFactory.class);
	}

	public boolean					isLastRequestWasSame()
	{
		return isLastRequestWasSame;
	}

// ------------------------------->	Public methods

	@Override
	public GuiWorker				build(Requests.Abstract request)
	{
		updateRequestHistory(request);

		if (request instanceof Requests.Info)
			return new GuiWorkerOnInfo();
		if (request instanceof Requests.Question)
			return new GuiWorkerOnQuestion();

		if (request instanceof Requests.HeroSelector)
			return new GuiWorkerOnHeroSelector();
		if (request instanceof Requests.HeroInfo)
			return new GuiWorkerOnHeroInfo();
		if (request instanceof Requests.NameEntry)
			return new GuiWorkerOnNameEntry();
		if (request instanceof Requests.ClassSelector)
			return new GuiWorkerOnClassSelector();
		if (request instanceof Requests.Map)
			return new GuiWorkerOnMap();
		if (request instanceof Requests.Battle)
			return new GuiWorkerOnBattle();

		throw new Exceptions.UnexpectedCodeBranch();
	}

// ------------------------------->	Private methods

	private void					updateRequestHistory(Requests.Abstract currentRequest)
	{
		if (previousRequest != null)
			isLastRequestWasSame = previousRequest.getClass() == currentRequest.getClass();
		else
			isLastRequestWasSame = false;

		previousRequest = currentRequest;
	}
}
