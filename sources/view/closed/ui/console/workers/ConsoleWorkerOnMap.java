package view.closed.ui.console.workers;

import application.utils.Point;
import model.open.Requests;
import view.closed.ui.utils.MapGenerator;
import application.utils.ResourceManager;
import view.closed.ui.console.utils.Template;
import view.closed.ui.console.ConsoleWorker;
import view.open.Context;

public class							ConsoleWorkerOnMap extends ConsoleWorker
{
	private static final Point			CANVAS_SIZE = new Point(65, 7);
	private static final MapGenerator	mapGenerator;

	private Template					mapTemplate;
	private Requests.Map				request;

	static
	{
		mapGenerator = new MapGenerator(CANVAS_SIZE);
	}

	@Override
	public void							execute(Requests.Abstract request)
	{
		parseRequest(request);

		clean();
		write(getText());

		if (this.request.allowHeroMovement)
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

		return mapTemplate.toString();
	}

	private void						prepareTemplate()
	{
		String							rawTemplate;

		rawTemplate = ResourceManager.getText("/view/console/templates/Map.txt");
		mapTemplate = new Template(rawTemplate);
	}

	private void						writeMapToTemplate()
	{
		char[][]						map;

		map = mapGenerator.generate(request);
		for (int y = 0; y < CANVAS_SIZE.y; y++)
			mapTemplate.replace("LINE" + y, new String(map[y]));
	}
}
