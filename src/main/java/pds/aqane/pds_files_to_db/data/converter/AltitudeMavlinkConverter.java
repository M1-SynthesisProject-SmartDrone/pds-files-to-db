package pds.aqane.pds_files_to_db.data.converter;

import java.util.Arrays;
import java.util.Map;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.headers.AltitudeHeaders;

class AltitudeMavlinkConverter extends MavlinkConverter {

	@Override
	protected boolean hasAllFields(Map<String, String> toConvert) {
		return Arrays.stream(AltitudeHeaders.values())
				.allMatch(header -> toConvert.containsKey(header.getHeaderName()));
	}

	@Override
	public BaseMavlinkStructData convertWithPotentialThrow(Map<String, String> toConvert) throws IllegalArgumentException, NumberFormatException {
		if (!hasAllFields(toConvert)) {
			throw new IllegalArgumentException("Fields missing in the line read : " + toConvert.toString());
		}
		AltitudeStructData data = new AltitudeStructData();

		data.setTimestamp(Long.parseLong(toConvert.get(AltitudeHeaders.TIMESTAMP.getHeaderName())));
		
		data.setAltitudeAmsl(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_AMSL.getHeaderName())));
		data.setAltitudeMonotonic(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_MONOTONIC.getHeaderName())));
		data.setAltitudeLocal(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_LOCAL.getHeaderName())));
		data.setAltitudeRelative(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_RELATIVE.getHeaderName())));
		data.setAltitudeTerrain(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_TERRAIN.getHeaderName())));
		data.setBottomClearance(Float.parseFloat(toConvert.get(AltitudeHeaders.BOTTOM_CLEARANCE.getHeaderName())));
		
		return data;
	}
}
