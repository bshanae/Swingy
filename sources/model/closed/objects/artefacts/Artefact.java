package model.closed.objects.artefacts;

public abstract class		Artefact
{
	private final String	name;

	protected 				Artefact(String name)
	{
		this.name = name;
	}

	public final String		getName()
	{
		return name;
	}
}
