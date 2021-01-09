package swingy.model.closed.delegates.concrete.common;

import swingy.controller.open.Commands;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.open.Requests;

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
