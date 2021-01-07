package view.closed.ui.gui.utils;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class			GuiDictionaryPanelBuilder
{
// -------------------> Attributes

	@Getter
	private JPanel		panel;

// -------------------> Constructor

	public 				GuiDictionaryPanelBuilder()
	{
		initializePanel();
	}

// -------------------> Public methods

	public void			put(String key, String value)
	{
		panel.add(buildLabelWithStyle(key + " :", Font.BOLD));
		panel.add(buildLabelWithStyle(value, Font.PLAIN), "wrap");
	}

	public void			put(String key, int value)
	{
		panel.add(buildLabelWithStyle(key + " :", Font.BOLD));
		panel.add(buildLabelWithStyle(((Integer)value).toString(), Font.PLAIN), "wrap");
	}

// -------------------> Private methods

	private void		initializePanel()
	{
		panel = new JPanel();
		panel.setLayout(new MigLayout("insets 5", "[50%!]10[50%!]"));
	}

	private JLabel		buildLabelWithStyle(String text, int style)
	{
		JLabel			label;

		label = new JLabel();
		label.setText(text);
		label.setFont(new FontRedactor(label.getFont()).changeStyle(style).get());

		return label;
	}
}
