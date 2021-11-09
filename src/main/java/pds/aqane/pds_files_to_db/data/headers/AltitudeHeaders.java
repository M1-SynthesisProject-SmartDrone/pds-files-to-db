package pds.aqane.pds_files_to_db.data.headers;

public enum AltitudeHeaders {
	TIMESTAMP("timestamp"),

	ALTITUDE_MONOTONIC("altitude_monotonic"),
	ALTITUDE_AMSL("altitude_amsl"),
	ALTITUDE_LOCAL("altitude_local"),
	ALTITUDE_RELATIVE("altitude_relative"),
	ALTITUDE_TERRAIN("altitude_terrain"),
	BOTTOM_CLEARANCE("bottom_clearance");

	private String headerName;

	private AltitudeHeaders(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderName() {
		return headerName;
	}
}
