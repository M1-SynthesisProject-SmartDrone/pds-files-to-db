package pds.aqane.pds_files_to_db.data.converter;

import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;

/**
 * 
 * @author Aldric Vitali Silvestre
 */
abstract class MavlinkConverter {

	protected static Logger LOGGER = Logger.getLogger(MavlinkConverter.class);

	/**
	 * Try to convert the map, but returns an empty optional if an error occurs
	 * 
	 * @param toConvert
	 * @return
	 */
	Optional<BaseMavlinkStructData> convert(Map<String, String> toConvert) {
		try {
			return Optional.of(convertWithPotentialThrow(toConvert));
		} catch (Exception exception) {
			LOGGER.warn("Cannot convert " + toConvert.toString(), exception);
			return Optional.empty();
		}
	}

	/**
	 * Try to convert the map, but throw a runtime exception if an error occurs.
	 * 
	 * @param toConvert
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public abstract BaseMavlinkStructData convertWithPotentialThrow(Map<String, String> toConvert) throws IllegalArgumentException, NumberFormatException;

	protected abstract boolean hasAllFields(Map<String, String> toConvert);
}
