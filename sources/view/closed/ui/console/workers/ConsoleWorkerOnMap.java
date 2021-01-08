package view.closed.ui.console.workers;

import application.utils.Point;
import model.open.Requests;
import view.closed.ui.console.utils.ConsoleTemplate;
import view.closed.ui.utils.MapGenerator;
import application.utils.resources.ResourceManager;
import application.utils.resources.Template;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

public class							ConsoleWorkerOnMap extends ConsoleWorker
{
	private static final Point			CANVAS_SIZE = new Point(33, 8);
	private static final MapGenerator	mapGenerator;

	private ConsoleTemplate				template;
	private Requests.Map				request;

	static
	{
		mapGenerator = new MapGenerator(CANVAS_SIZE);
	}

	@Override
	public void							execute(Requests.Abstract request)
	{
		parseRequest(request);

		if (!this.request.isMandatory())
			return;

		clean();
		write(getText());
		promptInput(Context.parse(request));
	}

	private void						parseRequest(Requests.Abstract request)
	{
		this.request = (Requests.Map)request;
	}

	private String						getText()
	{
		prepareTemplate();
		writeMapToTemplate();

		return template.toString();
	}

	private void						prepareTemplate()
	{
		template = new ConsoleTemplate("view/console/Template-Map.txt");
		template.put("COMMANDS", "Commands : ", ConsoleTemplate.Style.BOLD);
	}

	private void						writeMapToTemplate()
	{
		char[][]						map;
		StringBuilder					stringBuilder;

		map = mapGenerator.generate(request);
		for (int y = 0; y < CANVAS_SIZE.y; y++)
		{
			stringBuilder = new StringBuilder();
			for (int x = 0; x < CANVAS_SIZE.x; x++)
			{
				stringBuilder.append(map[y][x]);

				if (x < CANVAS_SIZE.x - 1)
					stringBuilder.append(" ");
			}

			template.put("LINE" + y, stringBuilder.toString());
		}
	}
}
