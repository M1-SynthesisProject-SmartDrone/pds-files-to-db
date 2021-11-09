package pds.aqane.pds_files_to_db.data.converter;

import java.util.Map;
import java.util.Optional;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.MavlinkStructName;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkCSVFileReader;

/**
 * Contains a single method that converts a line read by
 * {@link MavlinkCSVFileReader} to a {@link BaseMavlinkStructData} instance
 * (this is globally a simple factory).
 * 
 * @author Aldric Vitali Silvestre
 */
public class MavlinkStructConverter {

	/**
	 * All known converter strategies are stored here.
	 */
	private Map<MavlinkStructName, MavlinkConverter> converters = Map.of(
			MavlinkStructName.ALTITUDE, new AltitudeMavlinkConverter(),
			MavlinkStructName.BATTERY_STATUS, new BatteryMavlinkConverter(),
			MavlinkStructName.HIGHRES, new HighresMavlinkConverter());

	public Optional<BaseMavlinkStructData> convertLineWithHeaders(Map<String, String> lineWithHeaders, MavlinkStructName structName) {
		if (!converters.containsKey(structName)) {
			throw new IllegalArgumentException("name " + structName.name() + " unrecognized (struct name : " + structName.getStructName() + ")");
		}
		return converters.get(structName).convert(lineWithHeaders);
	}
	
	public BaseMavlinkStructData convertLineWithHeadersOrThrow(Map<String, String> lineWithHeaders, MavlinkStructName structName) throws IllegalArgumentException, NumberFormatException {
		if (!converters.containsKey(structName)) {
			throw new IllegalArgumentException("name " + structName.name() + " unrecognized (struct name : " + structName.getStructName() + ")");
		}
		return converters.get(structName).convertWithPotentialThrow(lineWithHeaders);
	}
}
