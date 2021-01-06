package model.closed.delegates.abstractDelegate;

import controller.open.Commands;
import lombok.Getter;

public class			ExecutableCommand
{
	@Getter
	private final
	Commands.Abstract	command;

	@Getter
	private boolean		isExecuted;

	public				ExecutableCommand(Commands.Abstract command)
	{
		this.command = command;
	}

	public void			markExecuted()
	{
		isExecuted = true;
	}
}
