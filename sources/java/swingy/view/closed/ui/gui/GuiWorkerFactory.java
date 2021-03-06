package swingy.view.closed.ui.gui;

import swingy.application.patterns.SingletonMap;
import swingy.application.service.Exceptions;
import swingy.model.open.Requests;
import swingy.view.closed.ui.UiMode;
import swingy.view.closed.ui.UiWorkerFactory;
import swingy.view.closed.ui.console.ConsoleWorkerFactory;
import swingy.view.closed.ui.gui.workers.*;

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

		// Common
		if (request instanceof Requests.Info)
			return new GuiWorkerOnInfo();
		if (request instanceof Requests.Error)
			return new GuiWorkerOnError();
		if (request instanceof Requests.Question)
			return new GuiWorkerOnQuestion();

		// Hero selector
		if (request instanceof Requests.HeroSelector)
			return new GuiWorkerOnHeroSelector();
		if (request instanceof Requests.HeroInfo)
			return new GuiWorkerOnHeroInfo();
		if (request instanceof Requests.NameEntry)
			return new GuiWorkerOnNameEntry();
		if (request instanceof Requests.ClassSelector)
			return new GuiWorkerOnClassSelector();

		// Game
		if (request instanceof Requests.Map)
			return new GuiWorkerOnMap();
		if (request instanceof Requests.HeroStats)
			return new GuiWorkerOnHeroStats();
		if (request instanceof Requests.HeroInventory)
			return new GuiWorkerOnHeroInventory();
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
