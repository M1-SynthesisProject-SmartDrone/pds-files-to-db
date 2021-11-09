package pds.aqane.pds_files_to_db.data.headers;

public enum BatteryHeaders {
	TIMESTAMP("timestamp"),

	CURRENT_CONSUMED("current_consumed"),
	ENERGY_CONSUMED("energy_consumed"),
	TEMPERATURE("temperature"),
	CURRENT_BATTERY("current_battery"),
	BATTERY_REMAINING("battery_remaining"),
	TIME_REMAINING("time_remaining");

	private String headerName;

	private BatteryHeaders(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderName() {
		return headerName;
	}
}
