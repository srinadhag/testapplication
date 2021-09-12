package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public static Properties properties;

	public PropertyReader(String propertiesPath) {
		load(propertiesPath);
	}

	public void load(String propertiesPath) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertiesPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertiesPath);
		}

	}

	public String getProperties(String key) {
		return properties.getProperty(key);
	}
}
