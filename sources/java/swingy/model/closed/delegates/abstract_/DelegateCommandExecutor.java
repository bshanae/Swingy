package swingy.model.closed.delegates.abstract_;

import swingy.controller.open.Commands;
import swingy.model.closed.delegates.abstract_.commands.ExecutableCommand;
import swingy.model.closed.delegates.abstract_.commands.LostCommand;

class								DelegateCommandExecutor
{
// -----------------------------------> Exceptions

	public static class					CommandIsNotExecutedAndNotCaught extends RuntimeException
	{
		public							CommandIsNotExecutedAndNotCaught(Commands.Abstract command)
		{
			super("Command of type '" + command.getClass() + "' was not executed");
		}
	}

// -------------------------------> Attributes

	private final ExecutableCommand command;

// -------------------------------> Constructor

	private							DelegateCommandExecutor(Commands.Abstract command)
	{
		this.command = new ExecutableCommand(command);
	}

// -------------------------------> Public methods

	public static void				tryToExecuteCommandRecursively(AbstractDelegate delegate, Commands.Abstract command)
	{
		DelegateCommandExecutor		executor;

		executor = new DelegateCommandExecutor(command);
		executor.tryToExecuteCommandRecursivelyWithCheck(delegate);
	}

// -------------------------------> Private methods : Executing

	private void					tryToExecuteCommandRecursivelyWithCheck(AbstractDelegate delegate)
	{
		DelegateVerifier.checkNotResolved(delegate);

		tryToExecuteCommandRecursivelyWithoutCheck(delegate);

		if (!command.isExecuted())
		{
			if (!throwLostCommand(delegate, command).isCaught())
				throw new CommandIsNotExecutedAndNotCaught(command.getCommand());
		}
	}

	private void					tryToExecuteCommandRecursivelyWithoutCheck(AbstractDelegate delegate)
	{
		DelegateVerifier.checkNotResolved(delegate);

		if (delegate.hasChild())
			tryToExecuteCommandRecursivelyWithoutCheck(delegate.getChild());

		if (!command.isExecuted() && !command.isLocked())
			tryToExecuteCommand(delegate, command);
	}

	private void 					tryToExecuteCommand(AbstractDelegate delegate, ExecutableCommand command)
	{
		if (delegate.isActivated())
		{
			DelegateLogger.logTryingToExecuteCommand(delegate, command.getCommand());
			delegate.tryToExecuteCommand(command);
		}
		else
		{
			DelegateLogger.logTryingToExecuteCommandSilently(delegate, command.getCommand());
			delegate.tryToExecuteCommandSilently(command);
		}
	}

// -------------------------------> Private methods : Throwing

	private LostCommand				throwLostCommand(AbstractDelegate delegate, ExecutableCommand command)
	{
		LostCommand					lostCommand;

		lostCommand = new LostCommand(command);
		throwLostCommandRecursively(delegate, lostCommand);

		return lostCommand;
	}

	private void					throwLostCommandRecursively(AbstractDelegate delegate, LostCommand command)
	{
		DelegateVerifier.checkNotResolved(delegate);

		delegate.tryCatchLostCommand(command);

		if (delegate.hasChild() && !command.isCaught())
			throwLostCommandRecursively(delegate.getChild(), command);
	}
}
