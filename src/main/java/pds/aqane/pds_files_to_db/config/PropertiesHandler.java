package pds.aqane.pds_files_to_db.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

/**
 * A singleton used to load the properties file and permits to retrieve easily
 * the properties wanted.
 *
 * @author Aldric Vitali Silvestre
 *
 */
public class PropertiesHandler {

	private static final String PROPERTIES_FILE_PATH = "application.properties";

	private Properties properties;

	private static PropertiesHandler instance = new PropertiesHandler();

	private PropertiesHandler() {
		properties = new Properties();
		InputStream inputStream = PropertiesHandler.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// We have to end the application here
			throw new RuntimeException(e);
		}
	}

	public static PropertiesHandler getInstance() {
		return instance;
	}

	public String getProperty(String name) throws NoSuchElementException {
		return Optional.ofNullable(properties.getProperty(name))
				.orElseThrow(() -> new NoSuchElementException(name + " is not in properties file"));
	}
}
