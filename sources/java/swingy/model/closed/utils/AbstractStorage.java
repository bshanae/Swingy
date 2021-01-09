package swingy.model.closed.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class		AbstractStorage<T> implements Iterable<T>
{
// -----------------------> Exceptions

	public static class		DataIsNotLoadedException extends RuntimeException {}
	public static class		StorageIsNotModifiableException extends RuntimeException {}

// -----------------------> Attributes

	private final boolean	isModifiable;
	private boolean			isLoaded;

	protected final List<T>	data;

// -----------------------> Constructor

	public 					AbstractStorage(boolean isModifiable)
	{
		this.isModifiable = isModifiable;
		this.data = new LinkedList<>();
	}

// -----------------------> Public methods

	@Override
	public Iterator<T>		iterator()
	{
		return data.iterator();
	}

	public abstract void	download();

	public int				size()
	{
		return data.size();
	}

	public T				get(int index)
	{
		checkIfLoaded();
		return data.get(index);
	}

	public void				add(T value)
	{
		checkIfModifiable();
		checkIfLoaded();
		data.add(value);
	}

	public void				delete(int index)
	{
		checkIfModifiable();
		checkIfLoaded();
		data.remove(index);
	}

	public void				delete(T value)
	{
		checkIfModifiable();
		checkIfLoaded();
		data.remove(value);
	}

// -----------------------> Protected methods

	protected void 			markLoaded()
	{
		isLoaded = true;
	}

	protected void 			checkIfModifiable()
	{
		if (!isModifiable)
			throw new StorageIsNotModifiableException();
	}

	protected void			checkIfLoaded()
	{
		if (!isLoaded)
			throw new DataIsNotLoadedException();
	}
}
