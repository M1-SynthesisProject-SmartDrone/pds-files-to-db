package pds.aqane.pds_files_to_db.file.mavlink;

import pds.aqane.pds_files_to_db.data.MavlinkStructs;
import pds.aqane.pds_files_to_db.file.CSVFileReader;

/**
 * The CSVFileReader with more functions for treating specific files for the
 * project.
 *
 * @author Aldric Vitali Silvestre
 */
public class MavlinkCSVFileReader extends CSVFileReader {

	private MavlinkStructs mavlinkStructName;
	
	private long id;
	
	public MavlinkCSVFileReader(String filename, String separator, MavlinkStructs structName, long id) {
		super(filename, separator);
		this.mavlinkStructName = structName;
		this.id = id;
	}

	public MavlinkStructs getMavlinkStructName() {
		return mavlinkStructName;
	}

	public long getId() {
		return id;
	}
}
