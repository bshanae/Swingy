package model.closed.delegates.abstractDelegate.commands;

import controller.open.Commands;
import lombok.Getter;

public class			ExecutableCommand
{
	@Getter
	private final
	Commands.Abstract	command;

	@Getter
	private boolean		isExecuted;

	@Getter
	private boolean		isLocked;

	public				ExecutableCommand(Commands.Abstract command)
	{
		this.command = command;
	}

	public void			markExecuted()
	{
		isExecuted = true;
	}

	public void 		lock()
	{
		isLocked = true;
	}
}
