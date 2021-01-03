package model.closed.objects.artefacts;

public abstract class				WeaponsCategories
{
	public static abstract class	Abstract
	{
		@Override
		public boolean				equals(Object other)
		{
			return this.getClass() == other.getClass();
		}
	}

	public static class				Hammer extends Abstract {}
	public static class				Sword extends Abstract {}
	public static class				Rapier extends Abstract {}
	public static class				Scimitar extends Abstract {}
	public static class				Dagger extends Abstract {}
	public static class				Stuff extends Abstract {}
}