package pds.aqane.pds_files_to_db.database;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

/**
 * From the data class, we will create the appropriate query for insertion.
 * 
 * @author Aldric Vitali Silvestre
 */
public class CreateInsertQueryVisitor implements MavlinkStructVisitor<String> {

	@Override
	public String visit(AltitudeStructData data) {
		return "INSERT INTO "
				+ "altitude(timestamp, altitude_monotonic, altitude_amsl, altitude_local, altitude_relative, altitude_terrain, bottom_clearance) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String visit(BatteryStructData data) {
		return "INSERT INTO "
				+ "battery(timestamp, current_consumed, energy_consumed, temperature, current_battery, battery_remaining, time_remaining) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String visit(HighresStructData data) {
		return "INSERT INTO highres "
				+ "(timestamp, xacc, yacc, zacc, xgyro, ygyro, zgyro, xmag, ymag, zmag, abs_pressure, diff_pressure, pressure_alt, temperature) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

}
