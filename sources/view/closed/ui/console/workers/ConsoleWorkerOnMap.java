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
	private static final Point			CANVAS_SIZE = new Point(67, 8);
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

		map = mapGenerator.generate(request);
		for (int y = 0; y < CANVAS_SIZE.y; y++)
			template.put("LINE" + y, new String(map[y]));
	}
}
