package pds.aqane.pds_files_to_db.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

/**
 * This visitor will take care of a {@link PreparedStatement} and define
 * parameters (the "?" in the query).
 * 
 * @author Aldric Vitali Silvestre
 */
public class DefineStatementVisitor implements MavlinkStructVisitor<PreparedStatement> {

	private PreparedStatement statement;

	public DefineStatementVisitor(PreparedStatement statement) {
		this.statement = statement;
	}

	@Override
	public PreparedStatement visit(AltitudeStructData data) {
		try {
			statement.setLong(1, data.getTimestamp());
			statement.setFloat(2, data.getAltitudeAmsl());
			statement.setFloat(3, data.getAltitudeLocal());
			statement.setFloat(4, data.getAltitudeMonotonic());
			statement.setFloat(5, data.getAltitudeRelative());
			statement.setFloat(6, data.getAltitudeTerrain());
			statement.setFloat(7, data.getBottomClearance());
		} catch (SQLException exception) {
			throw new IllegalArgumentException(exception);
		}
		return statement;
	}

	@Override
	public PreparedStatement visit(BatteryStructData data) {
		try {
			statement.setLong(1, data.getTimestamp());
			statement.setInt(2, data.getCurrentConsumed());
			statement.setInt(3, data.getEnergyConsumed());
			statement.setInt(4, data.getTemperature());
			statement.setInt(5, data.getCurrentBattery());
			statement.setInt(6, data.getBatteryRemaining());
			statement.setInt(7, data.getTimeRemaining());
			return statement;
		} catch (SQLException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

	@Override
	public PreparedStatement visit(HighresStructData data) {
		try {
			statement.setLong(1, data.getTimestamp());
			statement.setFloat(2, data.getXacc());
			statement.setFloat(3, data.getYacc());
			statement.setFloat(4, data.getZacc());
			statement.setFloat(5, data.getXgyro());
			statement.setFloat(6, data.getYgyro());
			statement.setFloat(7, data.getZgyro());
			statement.setFloat(8, data.getXmag());
			statement.setFloat(9, data.getYmag());
			statement.setFloat(10, data.getZmag());
			statement.setFloat(11, data.getAbsPressure());
			statement.setFloat(12, data.getDiffPressure());
			statement.setFloat(13, data.getPressureAlt());
			statement.setFloat(14, data.getTemperature());
			return statement;
		} catch (SQLException exception) {
			throw new IllegalArgumentException(exception);
		}
	}

}
