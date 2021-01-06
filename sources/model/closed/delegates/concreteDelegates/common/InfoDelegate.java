package model.closed.delegates.concreteDelegates.common;

import controller.open.Commands;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.ExecutableCommand;
import model.open.Requests;

public class				InfoDelegate extends AbstractDelegate
{
// -----------------------> Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// -----------------------> Attributes

	private final String	message;

// -----------------------> Constructor

	public					InfoDelegate(String message)
	{
		this.message = message;
	}

// -----------------------> Implementations

	@Override
	public void				whenActivated(boolean isFirstTime)
	{
		if (isFirstTime)
			sendRequest(new Requests.Info(message));
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		if (command.getCommand() instanceof Commands.Ok)
		{
			resolveLater(new ResolutionObject());
			command.markExecuted();
		}
	}
}
