package view.closed.ui.console.utils;

import java.io.*;

public abstract class			ResourceManager
{
	public static String		getText(String path)
	{
		return getFileContent(path);
	}

	private static String		getFileContent(String path)
	{
		StringBuilder			builder = new StringBuilder();

		try
		{
			InputStream			inputStream;
			BufferedReader		reader;

			inputStream = ResourceManager.class.getResourceAsStream(path);
			reader = new BufferedReader(new InputStreamReader(inputStream));

			String				line;
			
			while ((line = reader.readLine()) != null)
			{
				if (builder.length() > 0)
					builder.append("\n");

				builder.append(line);
			}

			inputStream.close();
			reader.close();
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception.getMessage());
		}

		return builder.toString();
	}
}
