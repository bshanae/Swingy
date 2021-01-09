package swingy.application.patterns;

import swingy.application.service.Exceptions;

public class					UniqueNotifier<T>
{
	private UniqueListener<T>	listener;
	
	public void					setListener(UniqueListener<T> listener)
	{
		this.listener = listener;
	}
	
	public void					notifyListener(T object)
	{
		if (listener == null)
			throw new Exceptions.InvalidUsage();

		listener.listen(object);
	}
}
