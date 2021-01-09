package swingy.view.closed.ui.gui.utils;

import java.awt.*;

public class			FontRedactor
{
// -------------------> Attributes

	private Font		font;

// -------------------> Constructor

	public				FontRedactor(Font font)
	{
		this.font = font;
	}

// -------------------> Public methods

	public FontRedactor	changeStyle(int style)
	{
		font = new Font(font.getName(), style, font.getSize());
		return this;
	}

	public FontRedactor	changeSize(int size)
	{
		font = new Font(font.getName(), font.getStyle(), size);
		return this;
	}

	public Font			get()
	{
		return font;
	}
}
