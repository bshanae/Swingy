package model.closed.delegates.concreteDelegates.common;

import controller.open.Commands;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.open.Requests;

public class        		ErrorDelegate extends AbstractDelegate
{
// -----------------------> Nested types

	public static class		ResolutionObject implements AbstractResolutionObject {}

// -----------------------> Attributes

	private final String	message;

// -----------------------> Constructor

	public          		ErrorDelegate(String message)
	{
		this.message = message;
	}

// -----------------------> Implementations

	@Override
	public void				whenActivated()
	{
		sendRequest(new Requests.Error(message));
	}

	@Override
	public void				tryToExecuteCommand(ExecutableCommand command)
	{
		command.lock();

		if (command.getCommand() instanceof Commands.Ok)
		{
			resolveLater(new ResolutionObject());
			command.markExecuted();
		}
	}
}
