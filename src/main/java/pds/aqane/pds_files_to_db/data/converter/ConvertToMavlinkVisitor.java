package pds.aqane.pds_files_to_db.data.converter;

import java.util.Map;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;
import pds.aqane.pds_files_to_db.data.headers.AltitudeHeaders;
import pds.aqane.pds_files_to_db.data.headers.BatteryHeaders;
import pds.aqane.pds_files_to_db.data.headers.HighresHeaders;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

/**
 * Each conversion from a line mapped with headers to the mavlink data object.
 * 
 * Before each use, the "toConvert" map must be updated.
 * 
 * @author Aldric Vitali Silvestre
 */
public class ConvertToMavlinkVisitor implements MavlinkStructVisitor<BaseMavlinkStructData> {

	private Map<String, String> toConvert;

	public Map<String, String> getToConvert() {
		return toConvert;
	}

	public void setToConvert(Map<String, String> toConvert) {
		this.toConvert = toConvert;
	}

	@Override
	public BaseMavlinkStructData visit(AltitudeStructData data) {
		toConvert = ConverterUtility.convertCppNans(toConvert);

		data.setTimestamp(Long.parseLong(toConvert.get(AltitudeHeaders.TIMESTAMP.getHeaderName())));
		data.setAltitudeAmsl(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_AMSL.getHeaderName())));
		data.setAltitudeMonotonic(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_MONOTONIC.getHeaderName())));
		data.setAltitudeLocal(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_LOCAL.getHeaderName())));
		data.setAltitudeRelative(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_RELATIVE.getHeaderName())));
		data.setAltitudeTerrain(Float.parseFloat(toConvert.get(AltitudeHeaders.ALTITUDE_TERRAIN.getHeaderName())));
		data.setBottomClearance(Float.parseFloat(toConvert.get(AltitudeHeaders.BOTTOM_CLEARANCE.getHeaderName())));

		return data;
	}

	@Override
	public BaseMavlinkStructData visit(BatteryStructData data) {
		toConvert = ConverterUtility.convertCppNans(toConvert);

		data.setTimestamp(Long.parseLong(toConvert.get(BatteryHeaders.TIMESTAMP.getHeaderName())));
		data.setBatteryRemaining(Integer.parseInt(toConvert.get(BatteryHeaders.BATTERY_REMAINING.getHeaderName())));
		data.setCurrentBattery(Integer.parseInt(toConvert.get(BatteryHeaders.CURRENT_BATTERY.getHeaderName())));
		data.setEnergyConsumed(Integer.parseInt(toConvert.get(BatteryHeaders.ENERGY_CONSUMED.getHeaderName())));
		data.setCurrentConsumed(Integer.parseInt(toConvert.get(BatteryHeaders.CURRENT_CONSUMED.getHeaderName())));
		data.setTemperature(Integer.parseInt(toConvert.get(BatteryHeaders.TEMPERATURE.getHeaderName())));
		data.setTimeRemaining(Integer.parseInt(toConvert.get(BatteryHeaders.TIME_REMAINING.getHeaderName())));

		return data;
	}

	@Override
	public BaseMavlinkStructData visit(HighresStructData data) {
		toConvert = ConverterUtility.convertCppNans(toConvert);
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
