package model.closed.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class						YamlParser
{
// -------------------------------> Attributes

	private static final
	ObjectMapper					objectMapper;

// -------------------------------> Static initializer

	static
	{
		objectMapper = new ObjectMapper(new YAMLFactory());
	}

// -------------------------------> Public methods

	public static <T> T				parse(String text, Class<T> dataClass)
	{
		try
		{
			return objectMapper.readValue(text, dataClass);
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
			throw new RuntimeException("Can't parse YAML file : " + exception.getMessage());
		}
	}
}
