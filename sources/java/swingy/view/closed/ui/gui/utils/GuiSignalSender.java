package swingy.view.closed.ui.gui.utils;

import swingy.view.open.ButtonId;
import swingy.view.open.Signals;
import swingy.view.open.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class		GuiSignalSender implements ActionListener
{
	Signals.Gui		signal;

	public			GuiSignalSender(ButtonId buttonId)
	{
		signal = new Signals.Gui(buttonId);
	}

	public			GuiSignalSender(ButtonId buttonId, String string)
	{
		signal = new Signals.Gui(buttonId, string);
	}

	@Override
	public void		actionPerformed(ActionEvent event)
	{
		View.getInstance().sendSignal(signal);
	}
}
