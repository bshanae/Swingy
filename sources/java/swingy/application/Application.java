package swingy.application;

import swingy.application.options.ApplicationOption;
import swingy.application.options.ApplicationOptionsParser;
import swingy.application.options.ApplicationOptionsValidator;
import swingy.application.utils.resources.ResourceManager;
import swingy.controller.open.Controller;
import swingy.model.open.Model;
import swingy.view.open.View;

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
			System.exit(1);
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

			System.exit(1);
		}

		System.exit(0);
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