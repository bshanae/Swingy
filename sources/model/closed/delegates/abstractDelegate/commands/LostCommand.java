package model.closed.delegates.abstractDelegate.commands;

import controller.open.Commands;
import lombok.Getter;

public class			LostCommand
{
	@Getter
	private final
	Commands.Abstract	command;

	@Getter
	private boolean		isCaught;

	public				LostCommand(ExecutableCommand command)
	{
		this.command = command.getCommand();
	}

	public void			markCaught()
	{
		isCaught = true;
	}
}
