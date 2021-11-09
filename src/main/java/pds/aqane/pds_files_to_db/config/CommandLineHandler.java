package pds.aqane.pds_files_to_db.config;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * A wrapper class that will parse cli and permit easy access to wanted infos.
 * 
 * @author Aldric Vitali Silvestre
 */
public class CommandLineHandler {

	private CommandLine commandLine;
	
	private String folderName;
	
	private Long idBegin;
	
	private String dbDriver;
	
	private String dbName;
	
	private String userName;
	
	private String dbHost;
	
	private String userPassword;
	
	public void parseCommandLine(String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = generateOptions();
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException parseException) {
			// The program have to end here
			throw new RuntimeException("Cannot parse the command line with arguments "
					+ Arrays.toString(args) + " : " + parseException);
		}
		assingValues();
	}

	private void assingValues() {
		this.folderName = commandLine.getOptionValue("f");
		String idString = commandLine.getOptionValue("i", "1");
		this.idBegin = Long.parseLong(idString);
		
		// Default values for bdd are in application.properties
		PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();
		this.dbDriver = commandLine.getOptionValue("d", propertiesHandler.getProperty("database.driver"));
		this.dbHost = commandLine.getOptionValue("h", propertiesHandler.getProperty("database.host"));
		this.dbName = commandLine.getOptionValue("n", propertiesHandler.getProperty("database.name"));
		this.userName = commandLine.getOptionValue("u", propertiesHandler.getProperty("database.user"));
		this.userPassword = commandLine.getOptionValue("p", propertiesHandler.getProperty("database.password"));
	}
	
	/**
	 * Get the folder name from the args previously parsed
	 * @return
	 */
	public String getFolderName() {
		Objects.requireNonNull(folderName, "The 'parseCommandLine' function must be called before this");
		return folderName;
	}
	
	public long getIdBegin() {
		Objects.requireNonNull(idBegin, "The 'parseCommandLine' function must be called before this");
		return idBegin;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbName() {
		return dbName;
	}

	public String getUserName() {
		return userName;
	}

	public String getDbHost() {
		return dbHost;
	}

	public String getUserPassword() {
		return userPassword;
	}

	private Options generateOptions() {
		Option optionDirectory = Option.builder("f")
				.required()
				.hasArg()
				.longOpt("folderName")
				.desc("The Folder to be parsed")
				.build();
		
		Option optionId = Option.builder("i")
				.required(false)
				.hasArg()
				.longOpt("idBegin")
				.type(Long.class)
				.desc("The first id of all files in the folder to proceed. Any file with an id lower than this will not be treated")
				.build();
		
		Option optionDriver = Option.builder("d")
				.required(false)
				.hasArg()
				.longOpt("driver")
				.desc("The database jdbc driver name")
				.build();
		
		Option optionDBname = Option.builder("n")
				.required(false)
				.hasArg()
				.longOpt("database")
				.desc("The name of the database")
				.build();
		
		Option optionUser = Option.builder("u")
				.required(false)
				.hasArg()
				.longOpt("user")
				.desc("The username that owns the database")
				.build();
		
		Option optionPassword = Option.builder("p")
				.required(false)
				.hasArg()
				.longOpt("password")
				.desc("The user's password")
				.build();
		
		Option optionHost = Option.builder("h")
				.required(false)
				.hasArg()
				.longOpt("host")
				.desc("The database's IP address. This can be 'localhost'")
				.build();
				
		Options options = new Options();
		options.addOption(optionDirectory);
		options.addOption(optionId);
		options.addOption(optionDriver);
		options.addOption(optionDBname);
		options.addOption(optionUser);
		options.addOption(optionPassword);
		options.addOption(optionHost);
		return options;
	}
}
