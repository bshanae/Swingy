package view.closed.ui.gui.utils;

import lombok.Getter;

public enum				GuiDialogSize
{
// ------------------->	Values

	SMALL(GuiSettings.DIALOG_SMALL_WIDTH, GuiSettings.DIALOG_SMALL_HEIGHT),
	BIG(GuiSettings.DIALOG_BIG_WIDTH, GuiSettings.DIALOG_BIG_HEIGHT);

// ------------------->	Attributes

	@Getter
	private final int	width;

	@Getter
	private final int	height;

	private				GuiDialogSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
}
