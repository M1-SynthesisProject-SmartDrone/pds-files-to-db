package pds.aqane.pds_files_to_db.data.converter;

import java.util.Arrays;
import java.util.Map;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;
import pds.aqane.pds_files_to_db.data.headers.AltitudeHeaders;
import pds.aqane.pds_files_to_db.data.headers.BatteryHeaders;
import pds.aqane.pds_files_to_db.data.headers.HighresHeaders;
import pds.aqane.pds_files_to_db.data.headers.IMavlinkHeaders;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

public class CheckConversionVisitor implements MavlinkStructVisitor<Void> {

	private Map<String, String> toConvert;
	
	public Map<String, String> getToConvert() {
		return toConvert;
	}

	public void setToConvert(Map<String, String> toConvert) {
		this.toConvert = toConvert;
	}

	@Override
	public Void visit(AltitudeStructData data) {
		hasAllFieldsOrThrow(toConvert, AltitudeHeaders.values());
		return null;
	}

	@Override
	public Void visit(BatteryStructData data) {
		hasAllFieldsOrThrow(toConvert, BatteryHeaders.values());
		return null;
	}

	@Override
	public Void visit(HighresStructData data) {
		hasAllFieldsOrThrow(toConvert, HighresHeaders.values());
		return null;
	}

	private void hasAllFieldsOrThrow(Map<String, String> toConvert, IMavlinkHeaders[] headersEnumArray) throws IllegalArgumentException {
		boolean hasAllFields = Arrays.stream(headersEnumArray)
				.allMatch(header -> toConvert.containsKey(header.getHeaderName()));
		if (!hasAllFields) {
			throw new IllegalArgumentException("Fields missing in the line read : " + toConvert.toString());
		}
	}
}
