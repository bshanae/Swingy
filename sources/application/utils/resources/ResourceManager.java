package application.utils.resources;

import java.io.*;

public abstract class			ResourceManager
{
// ---------------------------> Exceptions

	public static class			CantGetResourceFile extends RuntimeException
	{
		public 					CantGetResourceFile(String path)
		{
			super("Can't get resource file " + path);
		}
	}

// ---------------------------> Public methods

	public static String		getText(String pathToResource)
	{
		ClassLoader				classLoader;
		InputStream				inputStream;
		StringBuilder			stringBuilder;
		String					line;

		classLoader = Thread.currentThread().getContextClassLoader();
		inputStream = classLoader.getResourceAsStream(pathToResource);

		if (inputStream == null)
			throw new CantGetResourceFile(pathToResource);

		stringBuilder = new StringBuilder();
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream)))
		{
			while ((line = bufferReader.readLine()) != null)
				stringBuilder.append(line).append("\n");
		}
		catch (IOException exception)
		{
			throw new CantGetResourceFile(pathToResource);
		}

		return stringBuilder.toString();
	}

	public static File			getFile(String pathToResource)
	{
		ClassLoader				classLoader;
		File					file;

		classLoader = Thread.currentThread().getContextClassLoader();
		file = new File(classLoader.getResource(pathToResource).getFile());

		return file;
	}

	public static File[]		getFiles(String pathToResourceFolder)
	{
		ClassLoader				loader;
		String					path;

		loader = Thread.currentThread().getContextClassLoader();
		path = loader.getResource(pathToResourceFolder).getPath();

		return new File(path).listFiles();
	}

	public static Template		getTemplate(String pathToTemplate)
	{
		return new Template(getText(pathToTemplate));
	}
}
