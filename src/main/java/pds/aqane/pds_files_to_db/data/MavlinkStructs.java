package pds.aqane.pds_files_to_db.data;

import java.util.function.Supplier;

import pds.aqane.pds_files_to_db.data.implems.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.implems.BatteryStructData;
import pds.aqane.pds_files_to_db.data.implems.HighresStructData;

/**
 * Each enum contains the base name of the file and a supplier to create new
 * instances.
 *
 * @author Aldric Vitali Silvestre
 */
public enum MavlinkStructs {
	HIGHRES("mavlink_highres_imu_t", HighresStructData::new),
	ALTITUDE("mavlink_altitude_t", AltitudeStructData::new),
	BATTERY_STATUS("mavlink_battery_status_t", BatteryStructData::new);

	private String structName;

	private Supplier<BaseMavlinkStructData> mavStructSupplier;

	public String getStructName() {
		return structName;
	}

	/**
	 * Create a new empty data struct object at each call.
	 */
	public BaseMavlinkStructData createEmpty() {
		return mavStructSupplier.get();
	}

	private MavlinkStructs(String name, Supplier<BaseMavlinkStructData> mavStructSupplier) {
		this.structName = name;
		this.mavStructSupplier = mavStructSupplier;
	}
}
