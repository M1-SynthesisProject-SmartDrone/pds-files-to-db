package pds.aqane.pds_files_to_db.data;

/**
 * All wanted filenames are named after the mavlink struct that we have store
 * in.
 *
 * @author Aldric Vitali Silvestre
 */
public enum MavlinkStructName {
	HIGHRES("mavlink_highres_imu_t"),
	ALTITUDE("mavlink_altitude_t"),
	BATTERY_STATUS("mavlink_battery_status_t");

	private String structName;

	public String getStructName() {
		return structName;
	}
	
	private MavlinkStructName(String name) {
		this.structName = name;
	}
}
