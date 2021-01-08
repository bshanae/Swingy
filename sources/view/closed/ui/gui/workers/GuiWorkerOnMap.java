package view.closed.ui.gui.workers;

import application.utils.Point;
import model.open.Requests;
import net.miginfocom.swing.MigLayout;
import view.closed.ui.gui.GuiTasks;
import view.closed.ui.gui.GuiWorker;
import view.closed.ui.gui.utils.FontRedactor;
import view.closed.ui.gui.utils.GuiSignalSender;
import view.closed.ui.utils.MapGenerator;
import view.open.ButtonId;

import javax.swing.*;

public class							GuiWorkerOnMap extends GuiWorker
{
// -----------------------------------> Constants

	private static final Point			CANVAS_SIZE = new Point(21, 15);

// -----------------------------------> Attributes

	private static final MapGenerator	mapGenerator;

// -----------------------------------> Static initializer

	static
	{
		mapGenerator = new MapGenerator(CANVAS_SIZE);
	}

// -----------------------------------> Public methods

	@Override
	public void							execute(Requests.Abstract request)
	{
		showInFrame(getMainPanel(request));
	}

// -----------------------------------> Private methods

	public JPanel						getMainPanel(Requests.Abstract request)
	{
		JPanel							panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill", "", "10[]10[]10"));

		panel.add(buildMap(request), "grow, wrap, width 800!, center");
		panel.add(buildMenu(), "center");

		return panel;
	}

	private JComponent					buildMap(Requests.Abstract request)
	{
		String							layoutConfig;
		JPanel							panel;
		char[][]						map;

		layoutConfig = String.format("insets 5, fill, wrap %d", CANVAS_SIZE.x);

		panel = new JPanel();
		panel.setLayout(new MigLayout(layoutConfig));
		panel.setBorder(BorderFactory.createTitledBorder("Map"));

		map = mapGenerator.generate((Requests.Map)request);

		for (int y = 0; y < CANVAS_SIZE.y; y++)
			for (int x = 0; x < CANVAS_SIZE.x; x++)
				panel.add(buildCell(map[y][x]), "grow, center");

		return panel;
	}

	private JComponent					buildCell(char character)
	{
		JLabel							label;

		label = new JLabel();
		label.setFont(new FontRedactor(label.getFont()).changeSize(13).get());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("" + character);

		return label;
	}

	private JComponent					buildMenu()
	{
		JPanel							panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 5", "push[]30[]30[]push"));

		panel.add(buildHeroButtons(), "growy, width 200!");
		panel.add(buildControlButtons());
		panel.add(buildSystemButtons(), "growy, width 200!");

		return panel;
	}

	private JComponent					buildControlButtons()
	{
		JPanel							panel;
		JButton[]						buttons;

		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 8 15 8 15"));
		panel.setBorder(BorderFactory.createTitledBorder("Controls"));

		buttons = new JButton[4];

		for (int i = 0; i < 4; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setFont(new FontRedactor(buttons[i].getFont()).changeSize(10).get());
		}

		buttons[0].setText("▲");
		buttons[1].setText("◀");
		buttons[2].setText("▶");
		buttons[3].setText("▼");

		buttons[0].addActionListener(new GuiSignalSender(ButtonId.MAP_ARROW_UP));
		buttons[1].addActionListener(new GuiSignalSender(ButtonId.MAP_ARROW_LEFT));
		buttons[2].addActionListener(new GuiSignalSender(ButtonId.MAP_ARROW_RIGHT));
		buttons[3].addActionListener(new GuiSignalSender(ButtonId.MAP_ARROW_DOWN));

		panel.add(buttons[0], "width 30!, height 30!, cell 1 0");
		panel.add(buttons[1], "width 30!, height 30!, cell 0 1");
		panel.add(buttons[2], "width 30!, height 30!, cell 2 1");
		panel.add(buttons[3], "width 30!, height 30!, cell 1 2");

		return panel;
	}

	private JComponent					buildHeroButtons()
	{
		JButton							buttonStats;
		JButton							buttonInventory;

		buttonStats = new JButton("Stats");
		buttonStats.addActionListener(new GuiSignalSender(ButtonId.MAP_HERO_STATS));

		buttonInventory = new JButton("Inventory");
		buttonInventory.addActionListener(new GuiSignalSender(ButtonId.MAP_HERO_INVENTORY));

		return buildSideMenu("Hero", buttonStats, buttonInventory);
	}

	private JComponent					buildSystemButtons()
	{
		JButton							buttonConsole;
		JButton							buttonExit;

		buttonConsole = new JButton("Console");
		buttonConsole.addActionListener(new GuiSignalSender(ButtonId.MAP_CONSOLE));

		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new GuiSignalSender(ButtonId.MAP_EXIT));

		return buildSideMenu("System", buttonConsole, buttonExit);
	}

	private JComponent					buildSideMenu(String title, JButton upperButton, JButton lowerButton)
	{
		JPanel							panel;

		panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 5", "", "push[]20[]push"));
		panel.setBorder(BorderFactory.createTitledBorder(title));

		panel.add(upperButton, "wrap, center, width 100!");
		panel.add(lowerButton, "center center, width 100!");

		return panel;
	}
}
