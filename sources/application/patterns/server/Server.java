package application.patterns.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class		Server<T extends ServerTask>
{
	public static class		NoExecuteMethodForTaskType extends RuntimeException
	{
		public 				NoExecuteMethodForTaskType(ServerTask task)
		{
			super
			(
				String.format
				(
					"Can't find 'execute' method for task type '%s'",
					task.getClass()
				)
			);
		}
	}

	public static class		CantInvokeExecutionMethod extends RuntimeException {}

	public final void		execute(T task)
	{
		Method				method;

		try
		{
			method = this.getClass().getDeclaredMethod("execute", task.getClass());
			method.setAccessible(true);
			method.invoke(this, task);
		}
		catch (NoSuchMethodException exception)
		{
			throw new NoExecuteMethodForTaskType(task);
		}
		catch (IllegalAccessException | InvocationTargetException exception)
		{
			exception.printStackTrace();
			throw new CantInvokeExecutionMethod();
		}
	}
}
