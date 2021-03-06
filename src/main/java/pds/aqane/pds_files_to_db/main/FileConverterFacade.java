package pds.aqane.pds_files_to_db.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import pds.aqane.pds_files_to_db.config.CommandLineHandler;
import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.MavlinkStructs;
import pds.aqane.pds_files_to_db.data.converter.MavlinkStructConverter;
import pds.aqane.pds_files_to_db.database.DatabaseConnection;
import pds.aqane.pds_files_to_db.database.MavlinkDbInserter;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkCSVFileReader;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkFolderCrawler;

/**
 * The entry point for all the application logic.
 * 
 * @author Aldric Vitali Silvestre
 */
public class FileConverterFacade {

	private static Logger LOGGER = Logger.getLogger(FileConverterFacade.class);

	private static final int BATCH_SIZE = 100;

	private static final int LOG_UPDATE_FREQUENCY = 2000;

	private CommandLineHandler commandLineHandler = new CommandLineHandler();

	private MavlinkStructConverter structConverter = new MavlinkStructConverter();

	private MavlinkDbInserter dbInserter = new MavlinkDbInserter();

	public void init(String[] args) {
		commandLineHandler.parseCommandLine(args);
		DatabaseConnection.init(commandLineHandler.getDbDriver(), commandLineHandler.getDbName(), commandLineHandler.getDbHost(),
				commandLineHandler.getUserName(), commandLineHandler.getUserPassword());
		// Create the connection here
		DatabaseConnection.getConnection();
	}

	/**
	 * Search the files and insert them into the database.
	 * 
	 * @param folderName the folder to search in (the search is done only in this
	 *                   folder, not recursive)
	 * @param fromId     the file id to begin with (we will not process files with
	 *                   number minor than fromId)
	 */
	public void processFolder() {
		String folderName = commandLineHandler.getFolderName();
		Long fromId = commandLineHandler.getIdBegin();

		MavlinkFolderCrawler crawler = new MavlinkFolderCrawler(folderName, fromId);
		LOGGER.info("Start crawl in folder " + folderName);
		Map<MavlinkStructs, List<MavlinkCSVFileReader>> wantedFiles = crawler.findAllWantedFiles();

		if (wantedFiles.entrySet().size() == 0) {
			LOGGER.warn("No file found starting by id n??" + fromId);
		}

		for (var entry : wantedFiles.entrySet()) {
			try {
				processEntry(entry);
			} catch (Exception exception) {
				// We don't want to continue
				LOGGER.error("An error occured while processing entry : ", exception);
				break;
			}
		}
		LOGGER.info("End of process");
	}

	private void processEntry(Entry<MavlinkStructs, List<MavlinkCSVFileReader>> entry) throws IOException, SQLException {
		MavlinkStructs structName = entry.getKey();
		List<MavlinkCSVFileReader> readers = entry.getValue();
		LOGGER.info("Process " + structName.getStructName() + " files");
		// Just to be sure, we will sort file ids here (it is probably made while
		// crawling, but this is important)
		readers.sort(Comparator.comparing(MavlinkCSVFileReader::getId));
		int counter = 0;
		for (var reader : readers) {
			LOGGER.info("Process file n??" + reader.getId() + " : " + reader.getBaseFilename());
			reader.openFileAndReadHeaders();
			while (reader.hasMoreLinesToRead()) {
				List<BaseMavlinkStructData> dataList = reader.readBatchMappedToHeaders(BATCH_SIZE)
						.stream()
						.map(convertToStructData(structName))
						.flatMap(Optional::stream)
						.collect(Collectors.toList());
				if (!dataList.isEmpty()) {
					dbInserter.insertBatch(dataList);

					counter += dataList.size();
					if (counter != 0 && counter % LOG_UPDATE_FREQUENCY == 0) {
						LOGGER.info(counter + " rows inserted");
					}
				}
			}
			LOGGER.info("Inserted " + counter + " rows from file " + reader.getBaseFilename());
		}
	}

	private Function<Map<String, String>, Optional<BaseMavlinkStructData>> convertToStructData(MavlinkStructs structName) {
		return m -> structConverter.convertLineWithHeaders(m, structName);
	}
}
