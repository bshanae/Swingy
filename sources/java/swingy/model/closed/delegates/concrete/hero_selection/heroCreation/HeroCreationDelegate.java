package swingy.model.closed.delegates.concrete.hero_selection.heroCreation;

import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.HeroClass;
import swingy.model.closed.creatures.hero.storage.HeroStorageFactory;
import swingy.model.closed.creatures.hero.template.HeroTemplate;
import swingy.model.closed.creatures.hero.template.HeroTemplateStorage;
import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;

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
		HeroStorageFactory.buildInstance().add(Hero.create(heroName, template));
	}
}