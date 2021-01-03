package application;

import controller.open.Controller;
import model.open.Model;
import view.open.View;

public class			Application
{
	public static void	main(String[] arguments)
	{
		try
		{
			ApplicationOptions.parse(arguments);
			execute();
		}
		catch (Exception exception)
		{
			if (!ApplicationOptions.get(ApplicationOptions.DEBUG))
				System.out.println("Terminating with exception, enable debug mode for more info");
			else
			{
				System.out.println("Terminating with exception :" + exception.toString());
				exception.printStackTrace();
			}
		}
	}

	private static void	execute()
	{
		Model			model;
		View			view;
		Controller		controller;

		model = Model.getInstance();
		view = View.getInstance();
		controller = Controller.getInstance();

		model.setListener(view);
		view.setListener(controller);
		controller.setListener(model);

		model.run();

		while (!model.isTerminated())
			model.update();
	}
}