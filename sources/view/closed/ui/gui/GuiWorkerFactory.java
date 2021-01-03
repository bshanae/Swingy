package view.closed.ui.gui;

import application.service.Exceptions;
import model.open.Requests;
import view.closed.ui.UiWorkerFactory;
import view.closed.ui.gui.workers.*;

public class			GuiWorkerFactory extends UiWorkerFactory
{
	@Override
	public GuiWorker	build(Requests.Abstract request)
	{
		if (request instanceof Requests.Info)
			return new GuiWorkerOnInfo();
		if (request instanceof Requests.Question)
			return new GuiWorkerOnQuestion();
		if (request instanceof Requests.HeroSelector)
			return new GuiWorkerOnHeroSelector();
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
}
