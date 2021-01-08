package model.closed.delegates.concreteDelegates.heroSelection.heroCreation;

import model.closed.creatures.hero.Hero;
import model.closed.creatures.hero.HeroClass;
import model.closed.creatures.hero.heroStorage.HeroStorageFactory;
import model.closed.creatures.hero.heroTemplate.HeroTemplate;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;

public class						HeroCreationDelegate extends AbstractDelegate
{
// ------------------------------->	Nested types

	public static class				ResolutionObject implements AbstractResolutionObject {}

// ------------------------------->	Attributes

	private String					heroName;
	private HeroClass				heroClass;

// -------------------------------> Implementations

	@Override
	public void						whenActivated()
	{
		if (heroName == null)
			getHeroName();
	}

	@Override
	public void						whenChildResolved(AbstractResolutionObject object)
	{
		if (object instanceof NameEntryDelegate.ResolutionObject)
		{
			heroName = ((NameEntryDelegate.ResolutionObject)object).getName();
			getHeroClass();
		}
		else if (object instanceof ClassSelectionDelegate.ResolutionObject)
		{
			heroClass = ((ClassSelectionDelegate.ResolutionObject)object).getHeroClass();

			createHero();
			resolveLater(new ResolutionObject());
		}
	}

// ------------------------------->	Private methods

	private void					getHeroName()
	{
		stackChildLater(new NameEntryDelegate());
	}

	private void					getHeroClass()
	{
		stackChildLater(new ClassSelectionDelegate());
	}

	private void					createHero()
	{
		HeroTemplate				template;

		template = HeroTemplateStorage.getInstance().find(heroClass);
		HeroStorageFactory.buildInstance().add(Hero.create(template, heroName));
	}
}