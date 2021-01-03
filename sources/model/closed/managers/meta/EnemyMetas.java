package model.closed.managers.meta;

import model.closed.objects.creatures.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;

public class								EnemyMetas
{
	public interface 						Abstract
	{
		int									getAppearanceLevel();

		float								getAppearanceProbability();

		List<ArtefactMetas.Abstract>		getWeaponMetas();

		Enemy								getInstance();
	}

	public static class						Hollow implements Abstract
	{
		@Override
		public int							getAppearanceLevel()
		{
			return 0;
		}

		@Override
		public float						getAppearanceProbability()
		{
			return 1.f;
		}

		@Override
		public List<ArtefactMetas.Abstract>	getWeaponMetas()
		{
			List<ArtefactMetas.Abstract>	list;

			list = new LinkedList<>();

			list.add(new ArtefactMetas.OldArmor(0.2f));
			list.add(new ArtefactMetas.OldHelm(0.2f));

			list.add(new ArtefactMetas.OldSword(0.15f));
			list.add(new ArtefactMetas.OldScimitar(0.15f));
			list.add(new ArtefactMetas.OldDagger(0.15f));
			list.add(new ArtefactMetas.BrokenStuff(0.15f));

			list.add(new ArtefactMetas.Hammer(0.05f));
			list.add(new ArtefactMetas.Rapier(0.05f));

			return list;
		}

		@Override
		public Enemy						getInstance()
		{
			return new model.closed.objects.creatures.enemies.Hollow();
		}
	}

	public static class						HollowSoldier implements Abstract
	{
		@Override
		public int							getAppearanceLevel()
		{
			return 1;
		}

		@Override
		public float						getAppearanceProbability()
		{
			return 0.7f;
		}

		@Override
		public List<ArtefactMetas.Abstract>	getWeaponMetas()
		{
			List<ArtefactMetas.Abstract>	list;

			list = new LinkedList<>();

			list.add(new ArtefactMetas.OldArmor(0.4f));
			list.add(new ArtefactMetas.OldHelm(0.4f));

			list.add(new ArtefactMetas.ChainArmor(0.1f));
			list.add(new ArtefactMetas.SteelHelm(0.1f));

			list.add(new ArtefactMetas.OldSword(0.25f));
			list.add(new ArtefactMetas.OldScimitar(0.25f));
			list.add(new ArtefactMetas.OldDagger(0.25f));
			list.add(new ArtefactMetas.BrokenStuff(0.25f));

			list.add(new ArtefactMetas.Hammer(0.2f));
			list.add(new ArtefactMetas.Rapier(0.2f));

			return list;
		}

		@Override
		public Enemy						getInstance()
		{
			return new model.closed.objects.creatures.enemies.HollowSoldier();
		}
	}

	public static class						Ogre implements Abstract
	{
		@Override
		public int							getAppearanceLevel()
		{
			return 1;
		}

		@Override
		public float						getAppearanceProbability()
		{
			return 0.5f;
		}

		@Override
		public List<ArtefactMetas.Abstract>	getWeaponMetas()
		{
			List<ArtefactMetas.Abstract>	list;

			list = new LinkedList<>();

			list.add(new ArtefactMetas.OldArmor(0.5f));
			list.add(new ArtefactMetas.OldHelm(0.5f));

			list.add(new ArtefactMetas.ChainArmor(0.4f));
			list.add(new ArtefactMetas.SteelHelm(0.4f));

			list.add(new ArtefactMetas.OldSword(0.5f));
			list.add(new ArtefactMetas.OldScimitar(0.5f));
			list.add(new ArtefactMetas.OldDagger(0.5f));
			list.add(new ArtefactMetas.BrokenStuff(0.5f));

			list.add(new ArtefactMetas.Hammer(0.4f));
			list.add(new ArtefactMetas.Rapier(0.4f));

			list.add(new ArtefactMetas.SteelSword(0.15f));
			list.add(new ArtefactMetas.SteelScimitar(0.15f));
			list.add(new ArtefactMetas.SteelDagger(0.15f));

			return list;
		}

		@Override
		public Enemy						getInstance()
		{
			return new model.closed.objects.creatures.enemies.Ogre();
		}
	}
}
