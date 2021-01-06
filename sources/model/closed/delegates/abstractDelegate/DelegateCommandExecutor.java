package model.closed.delegates.abstractDelegate;

import controller.open.Commands;

class								DelegateCommandExecutor
{
// -------------------------------> Attributes

	private final ExecutableCommand	command;

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

// -------------------------------> Private methods

	private void					tryToExecuteCommandRecursivelyWithCheck(AbstractDelegate delegate)
	{
		DelegateVerifier.checkNotResolved(delegate);

		tryToExecuteCommandRecursivelyWithoutCheck(delegate);

		if (!command.isExecuted())
			throw new AbstractDelegate.CommandIsNotExecuted(command.getCommand());
	}

	private void					tryToExecuteCommandRecursivelyWithoutCheck(AbstractDelegate delegate)
	{
		DelegateVerifier.checkNotResolved(delegate);
		tryToExecuteCommand(delegate, command);

		if (!command.isExecuted() && delegate.hasChild())
			tryToExecuteCommandRecursivelyWithoutCheck(delegate.getChild());
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
}
