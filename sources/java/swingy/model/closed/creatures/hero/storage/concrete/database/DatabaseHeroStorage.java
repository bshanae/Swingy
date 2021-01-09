package swingy.model.closed.creatures.hero.storage.concrete.database;

import swingy.application.utils.resources.ResourceManager;
import swingy.application.utils.resources.Template;
import swingy.model.closed.artefacts.armor.Armor;
import swingy.model.closed.artefacts.artefact.Artefact;
import swingy.model.closed.artefacts.artefact.ArtefactAlias;
import swingy.model.closed.artefacts.helm.Helm;
import swingy.model.closed.artefacts.weapon.Weapon;
import swingy.model.closed.creatures.hero.Hero;
import swingy.model.closed.creatures.hero.HeroClass;
import swingy.model.closed.creatures.hero.storage.AbstractHeroStorage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class							DatabaseHeroStorage extends AbstractHeroStorage
{
// -----------------------------------> Nested classes

	public static class 				CantDownloadHeroesException extends RuntimeException
	{
		public 							CantDownloadHeroesException(String message)
		{
			super(message);
		}
	}
	
	private static class 				HeroData
	{
		public String					name;
		public String					heroClass;
		public int						level;
		public int						experience;
		public String					helm;
		public String					armor;
		public String					weapon;
	}

// -----------------------------------> Constants

	private static final String			PATH_TO_COMMAND_CREATE_DATABASE;
	private static final String			PATH_TO_COMMAND_CREATE_TABLE;
	private static final String			PATH_TO_COMMAND_TRUNCATE_TABLE;
	private static final String			PATH_TO_COMMAND_SELECT_HEROES;
	private static final String			PATH_TO_COMMAND_INSERT_HERO;

// -----------------------------------> Attributes

	private String						commandCreateDatabase;
	private String						commandCreateTable;
	private String						commandTruncateTable;
	private String						commandSelectHeroes;
	private Template					commandInsertHero;

// -----------------------------------> Static initializer

	static
	{
		PATH_TO_COMMAND_CREATE_DATABASE = "model/database/Command-CreateDatabase.txt";
		PATH_TO_COMMAND_CREATE_TABLE = "model/database/Command-CreateTable.txt";
		PATH_TO_COMMAND_TRUNCATE_TABLE = "model/database/Command-TruncateTable.txt";
		PATH_TO_COMMAND_SELECT_HEROES = "model/database/Command-SelectHeroes.txt";
		PATH_TO_COMMAND_INSERT_HERO = "model/database/Command-InsertHero.txt";
	}

// -----------------------------------> Public methods

	@Override
	public void							download()
	{
		loadCommandsFromTemplates();

		connectToDatabase();
		createDatabase();
		createTable();
		downloadHeroes();

		super.markLoaded();
	}

	@Override
	public void							upload()
	{
		uploadHeroes();
		disconnectFromDatabase();
	}

// -----------------------------------> Private methods : Commands

	private void						loadCommandsFromTemplates()
	{
		commandCreateDatabase = ResourceManager.getInstance().readText(PATH_TO_COMMAND_CREATE_DATABASE);
		commandCreateTable = ResourceManager.getInstance().readText(PATH_TO_COMMAND_CREATE_TABLE);
		commandTruncateTable = ResourceManager.getInstance().readText(PATH_TO_COMMAND_TRUNCATE_TABLE);
		commandSelectHeroes = ResourceManager.getInstance().readText(PATH_TO_COMMAND_SELECT_HEROES);
		commandInsertHero = new Template(PATH_TO_COMMAND_INSERT_HERO);
	}

// -----------------------------------> Private methods : Database connection

	private void 						connectToDatabase()
	{
		DatabaseManager.getInstance().registerDriver();
		DatabaseManager.getInstance().openConnection();
	}

	private void 						disconnectFromDatabase()
	{
		DatabaseManager.getInstance().closeConnection();
	}

// -----------------------------------> Private methods : Database commands

	private void 						createDatabase()
	{
		DatabaseManager.getInstance().executeUpdate(commandCreateDatabase);
	}

	private void 						createTable()
	{
		DatabaseManager.getInstance().executeUpdate(commandCreateTable);
	}

// -----------------------------------> Private methods : Database commands, Downloading

	private void						downloadHeroes()
	{
		ResultSet						set;
		HeroData						heroData;

		set = DatabaseManager.getInstance().executeQuery(commandSelectHeroes);
		heroData = new HeroData();

		try
		{
			while (set.next())
			{
				heroData.name = set.getString("name");
				heroData.heroClass = set.getString("class");
				heroData.level = set.getInt("level");
				heroData.experience = set.getInt("experience");
				heroData.helm = set.getString("helm");
				heroData.armor = set.getString("armor");
				heroData.weapon = set.getString("weapon");

				restoreHero(heroData);
			}
		}
		catch (SQLException exception)
		{
			throw new CantDownloadHeroesException(exception.getMessage());
		}
	}
	
	private void						restoreHero(HeroData heroData)
	{
		HeroClass						heroClass;
		Helm							helm;
		Armor							armor;
		Weapon							weapon;

		heroClass = HeroClass.fromString(heroData.heroClass);
		helm = (Helm)getArtefactFromName(heroData.helm);
		armor = (Armor)getArtefactFromName(heroData.armor);
		weapon = (Weapon)getArtefactFromName(heroData.weapon);

		data.add(Hero.restore(heroData.name, heroClass, heroData.level, heroData.experience, helm, armor, weapon));
	}

	private Artefact					getArtefactFromName(String name)
	{
		if (name.isEmpty())
			return null;

		return new ArtefactAlias(name).get();
	}

// -----------------------------------> Private methods : Database commands, Uploading

	private void						uploadHeroes()
	{
		DatabaseManager.getInstance().executeUpdate(commandTruncateTable);

		for (Hero hero : data)
			uploadHero(hero);
	}

	private void 						uploadHero(Hero hero)
	{
		commandInsertHero.reset();

		commandInsertHero.put("name", hero.getName());
		commandInsertHero.put("class", hero.getHeroClass().toString());
		commandInsertHero.put("level", String.valueOf(hero.getLevel()));
		commandInsertHero.put("experience", String.valueOf(hero.getExperience()));
		commandInsertHero.put("helm", getNameOfArtefact(hero.getInventory().getHelm()));
		commandInsertHero.put("armor", getNameOfArtefact(hero.getInventory().getArmor()));
		commandInsertHero.put("weapon", getNameOfArtefact(hero.getInventory().getWeapon()));

		DatabaseManager.getInstance().executeUpdate(commandInsertHero.toString());
	}

	private String						getNameOfArtefact(Artefact artefact)
	{
		return artefact != null ? artefact.getName() : "";
	}
}
