package application.patterns.uniqueNotifier;

public class					UniqueNotifier<T>
{
	private UniqueListener<T>	listener;
	
	public void					setListener(UniqueListener<T> listener)
	{
		this.listener = listener;
	}
	
	public void					notifyListener(T object)
	{
		assert listener != null;
		listener.listen(object);
	}
}
