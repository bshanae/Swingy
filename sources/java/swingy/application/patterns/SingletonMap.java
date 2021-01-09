package swingy.application.patterns;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class		SingletonMap
{
// -----------------------> Exceptions

	public static class		CantGetInstanceException extends RuntimeException {}
	public static class		CantBuildInstanceException extends RuntimeException {}

// -----------------------> Attributes

	private static final
	Map<Type, Object>		map = new HashMap<>();

// -----------------------> Public methods

	public static <T> T		getInstanceOf(Class<T> class_)
	{
		final Object		object = findOrAdd(class_);

		if (!class_.isInstance(object))
			throw new CantGetInstanceException();

		return (T)object;
	}

// -----------------------> Private methods

	private static Object	findOrAdd(Class<?> class_)
	{
		if (!map.containsKey(class_))
		{
			try
			{
				map.put(class_, class_.newInstance());
			}
			catch (InstantiationException | IllegalAccessException exception)
			{
				throw new CantBuildInstanceException();
			}
		}			

		return map.get(class_);		
	}
}