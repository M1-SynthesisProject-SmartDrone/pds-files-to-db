package pds.aqane.pds_files_to_db.data.converter;

import java.util.Arrays;
import java.util.Map;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;
import pds.aqane.pds_files_to_db.data.headers.HighresHeaders;

class HighresMavlinkConverter extends MavlinkConverter {

	@Override
	public boolean hasAllFields(Map<String, String> toConvert) {
		return Arrays.stream(HighresHeaders.values())
				.allMatch(header -> toConvert.containsKey(header.getHeaderName()));
	}

	@Override
	public BaseMavlinkStructData convertWithPotentialThrow(Map<String, String> toConvert) throws IllegalArgumentException, NumberFormatException {
		if (!hasAllFields(toConvert)) {
			throw new IllegalArgumentException("Fields missing in the line read : " + toConvert.toString());
		}
		toConvert = ConverterUtility.convertCppNans(toConvert);
		HighresStructData data = new HighresStructData();
		data.setTimestamp(Long.parseLong(toConvert.get(HighresHeaders.TIMESTAMP.getHeaderName())));
		
		data.setAbsPressure(Float.parseFloat(toConvert.get(HighresHeaders.ABS_PRESSURE.getHeaderName())));
		data.setDiffPressure(Float.parseFloat(toConvert.get(HighresHeaders.DIFF_PRESSURE.getHeaderName())));
		data.setPressureAlt(Float.parseFloat(toConvert.get(HighresHeaders.PRESSURE_ALT.getHeaderName())));
		data.setTemperature(Float.parseFloat(toConvert.get(HighresHeaders.TEMPERATURE.getHeaderName())));

		data.setXacc(Float.parseFloat(toConvert.get(HighresHeaders.X_ACC.getHeaderName())));
		data.setYacc(Float.parseFloat(toConvert.get(HighresHeaders.Y_ACC.getHeaderName())));
		data.setZacc(Float.parseFloat(toConvert.get(HighresHeaders.Z_ACC.getHeaderName())));

		data.setXgyro(Float.parseFloat(toConvert.get(HighresHeaders.X_GYRO.getHeaderName())));
		data.setYgyro(Float.parseFloat(toConvert.get(HighresHeaders.Y_GYRO.getHeaderName())));
		data.setZgyro(Float.parseFloat(toConvert.get(HighresHeaders.Z_GYRO.getHeaderName())));

		data.setXmag(Float.parseFloat(toConvert.get(HighresHeaders.X_MAG.getHeaderName())));
		data.setYmag(Float.parseFloat(toConvert.get(HighresHeaders.Y_MAG.getHeaderName())));
		data.setZmag(Float.parseFloat(toConvert.get(HighresHeaders.Z_MAG.getHeaderName())));

		return data;
	}
}
