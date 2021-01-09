package swingy.model.closed.creatures.hero.storage.concrete.database;

import swingy.application.patterns.SingletonMap;

import java.sql.*;

public class				DatabaseManager
{
// -----------------------> Exceptions

	public static class		CantRegisterDriver extends RuntimeException
	{
		public				CantRegisterDriver(String message)
		{
			super(message);
		}
	}

	public static class		CantOpenConnection extends RuntimeException
	{
		public				CantOpenConnection(String message)
		{
			super(message);
		}
	}

	public static class		CantCloseConnection extends RuntimeException
	{
		public				CantCloseConnection(String message)
		{
			super(message);
		}
	}

	public static class		CantExecuteCommand extends RuntimeException
	{
		public				CantExecuteCommand(String message)
		{
			super(message);
		}
	}


// -----------------------> Constants

	static final String		JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String		DB_URL = "jdbc:mysql://localhost/";
	static final String		USER = "root";
	static final String		PASS = "root";

// -----------------------> Attributes

	private Connection		connection;
	private Statement		statement;

// -----------------------> Properties

	public static
	DatabaseManager			getInstance()
	{
		return SingletonMap.getInstanceOf(DatabaseManager.class);
	}

// -----------------------> Public methods : Initialization

	public void				registerDriver()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException exception)
		{
			exception.printStackTrace();
			throw new CantRegisterDriver(exception.getMessage());
		}
	}

	public void				openConnection()
	{
		try
		{
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
		}
		catch (SQLException exception)
		{
			throw new CantOpenConnection(exception.getMessage());
		}
	}

	public void				closeConnection()
	{
		try
		{
			if (connection != null) connection.close();

			if (statement != null) statement.close();
		}
		catch (SQLException exception)
		{
			throw new CantCloseConnection(exception.getMessage());

		}
	}

// -----------------------> Public methods : Request

	public void 			executeUpdate(String command)
	{
		try
		{
			statement.executeUpdate(command);
		}
		catch (SQLException exception)
		{
			throw new CantExecuteCommand(exception.getMessage());
		}
	}

	public ResultSet		executeQuery(String command)
	{
		try
		{
			return statement.executeQuery(command);
		}
		catch (SQLException exception)
		{
			throw new CantExecuteCommand(exception.getMessage());
		}
	}
 }
