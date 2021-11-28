package pds.aqane.pds_files_to_db.data.headers;

public enum HighresHeaders implements IMavlinkHeaders {
	TIMESTAMP("timestamp"),

	X_ACC("xacc"),
	Y_ACC("yacc"),
	Z_ACC("zacc"),

	X_GYRO("xgyro"),
	Y_GYRO("ygyro"),
	Z_GYRO("zgyro"),

	X_MAG("xmag"),
	Y_MAG("ymag"),
	Z_MAG("zmag"),

	ABS_PRESSURE("abs_pressure"),
	DIFF_PRESSURE("diff_pressure"),
	PRESSURE_ALT("pressure_alt"),
	TEMPERATURE("temperature");

	private String headerName;

	private HighresHeaders(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderName() {
		return headerName;
	}
}
