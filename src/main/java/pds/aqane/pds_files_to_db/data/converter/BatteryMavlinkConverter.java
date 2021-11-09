package pds.aqane.pds_files_to_db.data.converter;

import java.util.Arrays;
import java.util.Map;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.headers.BatteryHeaders;

class BatteryMavlinkConverter extends MavlinkConverter {

	@Override
	protected boolean hasAllFields(Map<String, String> toConvert) {
		return Arrays.stream(BatteryHeaders.values())
				.allMatch(header -> toConvert.containsKey(header.getHeaderName()));
	}

	@Override
	public BaseMavlinkStructData convertWithPotentialThrow(Map<String, String> toConvert) throws IllegalArgumentException, NumberFormatException {
		if (!hasAllFields(toConvert)) {
			throw new IllegalArgumentException("Fields missing in the line read : " + toConvert.toString());
		}
		BatteryStructData data = new BatteryStructData();

		data.setTimestamp(Long.parseLong(toConvert.get(BatteryHeaders.TIMESTAMP.getHeaderName())));

		data.setBatteryRemaining(Integer.parseInt(toConvert.get(BatteryHeaders.BATTERY_REMAINING.getHeaderName())));
		data.setCurrentBattery(Integer.parseInt(toConvert.get(BatteryHeaders.CURRENT_BATTERY.getHeaderName())));
		data.setEnergyConsumed(Integer.parseInt(toConvert.get(BatteryHeaders.ENERGY_CONSUMED.getHeaderName())));
		data.setTemperature(Integer.parseInt(toConvert.get(BatteryHeaders.TEMPERATURE.getHeaderName())));
		data.setTimeRemaining(Integer.parseInt(toConvert.get(BatteryHeaders.TIME_REMAINING.getHeaderName())));
		
		return data;
	}
}
