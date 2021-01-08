package view.closed.ui.gui.utils;

import view.open.ButtonId;
import view.open.Signals;
import view.open.View;

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
