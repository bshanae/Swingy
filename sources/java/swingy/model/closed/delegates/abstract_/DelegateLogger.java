package swingy.model.closed.delegates.abstract_;

import swingy.application.service.Debug;
import swingy.application.service.LogGroup;
import swingy.controller.open.Commands;

abstract class			DelegateLogger
{
	public static void	logChildStacking(AbstractDelegate parent, AbstractDelegate child)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Stacking delegate of type '%s' onto delegate of type '%s'",
			child.getClass(),
			parent.getClass()
		);
	}

	public static void	logActivating(AbstractDelegate delegate)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Activating delegate of type '%s'",
			delegate.getClass()
		);
	}

	public static void	logDeactivating(AbstractDelegate delegate)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Deactivating delegate of type '%s'",
			delegate.getClass()
		);
	}

	public static void	logTryingToExecuteCommand(AbstractDelegate delegate, Commands.Abstract command)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Trying to execute command of type '%s' in delegate of type '%s'",
			command.getClass(),
			delegate.getClass()
		);
	}

	public static void	logTryingToExecuteCommandSilently(AbstractDelegate delegate, Commands.Abstract command)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Trying to silently execute command of type '%s' in delegate of type '%s'",
			command.getClass(),
			delegate.getClass()
		);
	}

	public static void	logResolving(AbstractDelegate delegate)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Resolving delegate of type '%s'",
			delegate.getClass()
		);
	}
}
