package model.closed.delegates.common;

import controller.open.Commands;
import model.closed.delegates.Delegate;
import model.open.Requests;

public class        		ErrorDelegate extends Delegate
{
	private final String	message;

	public          		ErrorDelegate(String message)
	{
		this.message = message;
	}

	@Override
	public void				whenActivated(boolean isFirstTimeActivated)
	{
		sendRequest(new Requests.Error(message));
	}

	@Override
	public void				whenResponded(Commands.Abstract command)
	{
		requestResolution();
	}
}
