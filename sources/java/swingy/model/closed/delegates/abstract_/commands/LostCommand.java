package swingy.model.closed.delegates.abstract_.commands;

import swingy.controller.open.Commands;
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
