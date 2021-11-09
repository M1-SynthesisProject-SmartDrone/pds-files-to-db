package pds.aqane.pds_files_to_db.file.mavlink;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import pds.aqane.pds_files_to_db.config.PropertiesHandler;
import pds.aqane.pds_files_to_db.data.MavlinkStructName;

/**
 * From a folder navigate through all files in it and create a CSVFileReader for
 * each wanted files.
 * 
 * A file is wanted if it starts with the good string and has an id superior or
 * equals to the minimum id wanted.
 *
 * @author Aldric Vitali Silvestre
 */
public class MavlinkFolderCrawler {

	File folder;

	long idBegin;

	String csvSeparator;

	/**
	 * @param folderName where files are stored
	 * @param fromId     the id where to begin collecting files
	 */
	public MavlinkFolderCrawler(String folderName, long fromId) {
		this.csvSeparator = PropertiesHandler.getInstance().getProperty("csv.separator");
		this.folder = new File(folderName);
		if (!this.folder.isDirectory()) {
			throw new IllegalArgumentException(String.format("'%s' is not a valid directory", folderName));
		}
		this.idBegin = fromId;
	}

	public Map<MavlinkStructName, List<MavlinkCSVFileReader>> findAllWantedFiles() {
		return Arrays.stream(folder.listFiles())
				.map(this::convertToFileReader)
				.flatMap(Optional::stream) // remove the non correct files (i.e the empty optionals)
				.sorted(Comparator.comparing(MavlinkCSVFileReader::getId))
				.collect(Collectors.groupingBy(MavlinkCSVFileReader::getMavlinkStructName));
	}

	/**
	 * Convert to Csv file reader only if the struct name is found and we have a
	 * correct id.
	 * 
	 * @param file
	 * @return An optional : empty if file not valid
	 */
	private Optional<MavlinkCSVFileReader> convertToFileReader(File file) {
		String baseFilename = file.getName();
		String completeFilename = file.getAbsolutePath();
		Optional<MavlinkStructName> structNameOpt = findStructName(baseFilename);
		if (structNameOpt.isPresent()) {
			Optional<Long> fileIdOpt = findFileId(baseFilename);
			if (fileIdOpt.isPresent()) {
				Long fileId = fileIdOpt.get();
				MavlinkStructName structName = structNameOpt.get();
				if (fileId >= this.idBegin) {
					return Optional.of(new MavlinkCSVFileReader(completeFilename, csvSeparator, structName, fileId));
				}
			}
		}
		return Optional.empty();
	}

	private Optional<MavlinkStructName> findStructName(String filename) {
		for (MavlinkStructName structName : MavlinkStructName.values()) {
			if (filename.startsWith(structName.getStructName())) {
				return Optional.of(structName);
			}
		}
		return Optional.empty();
	}

	private Optional<Long> findFileId(String filename) {
		String name = filename;
		// name is "[type]-[id].csv" normally
		// mavlink structs have only "_" characters
		String[] nameSplit = name.split("-");
		if (nameSplit.length == 2) {
			String part2 = nameSplit[1];
			// we don't have dots in the mavlink struct
			String[] splitExt = part2.split("\\.");
			if (splitExt.length > 1) {
				String idString = splitExt[0];
				try {
					return Optional.of(Long.parseLong(idString));
				} catch (NumberFormatException e) {
					// This is not a number, exit
					return Optional.empty();
				}
			}
		}
		return Optional.empty();
	}
}
