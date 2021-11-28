package pds.aqane.pds_files_to_db.data.converter;

import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.MavlinkStructs;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkCSVFileReader;

/**
 * Contains a single method that converts a line read by
 * {@link MavlinkCSVFileReader} to a {@link BaseMavlinkStructData} instance
 * (this is globally a simple factory).
 * 
 * @author Aldric Vitali Silvestre
 */
public class MavlinkStructConverter {
	
	protected static Logger LOGGER = Logger.getLogger(MavlinkStructConverter.class);

	// We use 2 differents visitors in order to proceed operations depending on type
	private CheckConversionVisitor checkConversionVisitor = new CheckConversionVisitor();
	private ConvertToMavlinkVisitor convertToMavlinkVisitor = new ConvertToMavlinkVisitor();

	public Optional<BaseMavlinkStructData> convertLineWithHeaders(Map<String, String> lineWithHeaders, MavlinkStructs structName) {
		try {
			BaseMavlinkStructData converted = convertLineWithHeadersOrThrow(lineWithHeaders, structName);
			return Optional.of(converted);
		} catch (Exception exception) {
			LOGGER.warn("Cannot convert " + lineWithHeaders.toString(), exception);
			return Optional.empty();
		}
	}
	
	public BaseMavlinkStructData convertLineWithHeadersOrThrow(Map<String, String> lineWithHeaders, MavlinkStructs structName) throws IllegalArgumentException, NumberFormatException {
		BaseMavlinkStructData structData = structName.createEmpty();
		checkConversionVisitor.setToConvert(lineWithHeaders);
		structData.accept(checkConversionVisitor);
		
		convertToMavlinkVisitor.setToConvert(lineWithHeaders);
		return structData.accept(convertToMavlinkVisitor);
	}
}
