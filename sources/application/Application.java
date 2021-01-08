package application;

import application.applicationOptions.ApplicationOption;
import application.applicationOptions.ApplicationOptionsParser;
import application.applicationOptions.ApplicationOptionsValidator;
import application.utils.resources.ResourceManager;
import controller.open.Controller;
import model.open.Model;
import view.open.View;

public class			Application
{
// -------------------> Public methods

	public static void	main(String[] arguments)
	{
		try
		{
			ApplicationOptionsParser.parse(arguments);
			ApplicationOptionsValidator.validate();

			execute();
		}
		catch (ApplicationOptionsValidator.InvalidOptionsException exception)
		{
			printUsage();
		}
		catch (Exception exception)
		{
			if (!ApplicationOption.DEBUG.isDefined())
				System.out.println("Terminating with exception, enable debug mode for more info");
			else
			{
				System.out.println("Terminating with exception :" + exception.toString());
				exception.printStackTrace();
			}
		}
	}

// -------------------> Private methods

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

	private static void	printUsage()
	{
		String			string;

		string = ResourceManager.getInstance().readText("application/Usage.txt");
		System.out.println(string);
	}
}