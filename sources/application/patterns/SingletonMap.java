package application.patterns;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class		SingletonMap
{
	private static final
	Map<Type, Object>		map = new HashMap<>();

	public static <T> T		getInstanceOf(Class<T> class_)
	{
		final Object		object = findOrAdd(class_);

		assert class_.isInstance(object);
		return (T)object;
	}
	
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
				exception.printStackTrace();
				assert false;
			}
		}			

		return map.get(class_);		
	}
}