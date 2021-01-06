package view.closed.ui.gui.utils;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class			GuiDictionaryPanelBuilder
{
// -------------------> Attributes

	private Font		fontForKey;
	private Font		fontForValue;

	@Getter
	private JPanel		panel;

// -------------------> Constructor

	public 				GuiDictionaryPanelBuilder()
	{
		initializeFonts();
		initializePanel();
	}

// -------------------> Public methods

	public void			put(String key, String value)
	{
		panel.add(buildLabelWithFont(key + " :", fontForKey));
		panel.add(buildLabelWithFont(value, fontForValue), "wrap");
	}

	public void			put(String key, int value)
	{
		panel.add(buildLabelWithFont(key + " :", fontForKey));
		panel.add(buildLabelWithFont(((Integer)value).toString(), fontForValue), "wrap");
	}

// -------------------> Private methods

	private void		initializeFonts()
	{
		fontForKey = new Font(GuiSettings.FONT_NAME, Font.BOLD, 15);
		fontForValue = new Font(GuiSettings.FONT_NAME, Font.PLAIN, 15);
	}

	private void		initializePanel()
	{
		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 5", "[50%!]10[50%!]"));
	}

	private JLabel		buildLabelWithFont(String text, Font font)
	{
		JLabel			label;

		label = new JLabel();
		label.setText(text);
		label.setFont(font);

		return label;
	}
}
