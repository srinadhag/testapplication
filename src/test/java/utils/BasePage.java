package utils;

public class BasePage {
	
	private final static String propertyFilePath = "src/test/resources/configuration.properties";

	static PropertyReader propertiesReader = new PropertyReader(propertyFilePath);

	public static String getProperty(String key) {
		return propertiesReader.getProperties(key);
	}

}
