package pds.aqane.pds_files_to_db.file.mavlink;

import pds.aqane.pds_files_to_db.data.MavlinkStructName;
import pds.aqane.pds_files_to_db.file.CSVFileReader;

/**
 * The CSVFileReader with more functions for treating specific files for the
 * project.
 *
 * @author Aldric Vitali Silvestre
 */
public class MavlinkCSVFileReader extends CSVFileReader {

	private MavlinkStructName mavlinkStructName;
	
	private long id;
	
	public MavlinkCSVFileReader(String filename, String separator, MavlinkStructName structName, long id) {
		super(filename, separator);
		this.mavlinkStructName = structName;
		this.id = id;
	}

	public MavlinkStructName getMavlinkStructName() {
		return mavlinkStructName;
	}

	public long getId() {
		return id;
	}
}
