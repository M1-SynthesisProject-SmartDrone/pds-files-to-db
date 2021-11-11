package pds.aqane.pds_files_to_db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import pds.aqane.pds_files_to_db.config.PropertiesHandler;

/**
 * A singleton class permitting the connection with the database.
 * The init method must be called before connecting to database
 * 
 * @author Aldric Vitali Silvestre
 */
public class DatabaseConnection {

	private static DatabaseConnection instance = new DatabaseConnection();

	private Connection connection;

	private String driver;
	private String dbName;
	private String host;
	private String user;
	private String password;
	
	private boolean isInitialized = false;

	/**
	 * Init the connection parameters with the parameters in the
	 * "application.properties" file.
	 */
	public static void init() {
		PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();
		instance.driver = propertiesHandler.getProperty("database.driver");
		instance.dbName = propertiesHandler.getProperty("database.name");
		instance.host = propertiesHandler.getProperty("database.host");
		instance.user = propertiesHandler.getProperty("database.user");
		instance.password = propertiesHandler.getProperty("database.password");
		instance.isInitialized = true;
	}

	/**
	 * Init the connection parameters with custom parameters.
	 */
	public static void init(String driver, String dbName, String host, String user, String password) {
		instance.driver = Objects.requireNonNull(driver);
		instance.dbName = Objects.requireNonNull(dbName);
		instance.host = Objects.requireNonNull(host);
		instance.user = Objects.requireNonNull(user);
		instance.password = Objects.requireNonNull(password);
		instance.isInitialized = true;
	}

	/**
	 * Create the connection instance if it is the first time we try to access this
	 * class
	 */
	public static Connection getConnection() {
		return instance.getInstanceConnection();
	}

	private Connection getInstanceConnection() {
		// Only a single thread accessing it, no need double check method
		if (connection == null) {
			if(!isInitialized) {
				init();
			}
			try {
				String url = "jdbc:" + driver + "://" + host + "/" + dbName;
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// Cannot do more here, we have to stop the program
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return connection;
	}
}
