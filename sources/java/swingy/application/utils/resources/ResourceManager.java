package swingy.application.utils.resources;

import swingy.application.patterns.SingletonMap;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class					ResourceManager
{
// ---------------------------> Exceptions

	public static class			CantGetResourceFile extends RuntimeException
	{
		public 					CantGetResourceFile(String path)
		{
			super("Can't get resource file " + path);
		}
	}

// ---------------------------> Properties

	public static
	ResourceManager				getInstance()
	{
		return SingletonMap.getInstanceOf(ResourceManager.class);
	}

// ---------------------------> Public methods

	public List<String>			readLines(String pathToResource)
	{
		ClassLoader				classLoader;
		InputStream				inputStream;
		List<String>			lines;
		String					line;

		classLoader = Thread.currentThread().getContextClassLoader();
		inputStream = classLoader.getResourceAsStream(pathToResource);

		if (inputStream == null)
			throw new CantGetResourceFile(pathToResource);

		lines = new LinkedList<>();
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream)))
		{
			while ((line = bufferReader.readLine()) != null)
				lines.add(line);
		}
		catch (IOException exception)
		{
			throw new CantGetResourceFile(pathToResource);
		}

		return lines;
	}

	public String				readText(String pathToResource)
	{
		List<String>			lines;
		StringBuilder			stringBuilder;

		lines = readLines(pathToResource);
		stringBuilder = new StringBuilder();

		for (int i = 0; i < lines.size(); i++)
		{
			stringBuilder.append(lines.get(i));

			if (i < lines.size() - 1)
				stringBuilder.append('\n');
		}

		return stringBuilder.toString();
	}
}
