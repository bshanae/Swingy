package application.utils;

import java.io.*;

public abstract class			ResourceManager
{
	public static String		getText(String pathToResource)
	{
		return getFile(pathToResource).toString();
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
}
