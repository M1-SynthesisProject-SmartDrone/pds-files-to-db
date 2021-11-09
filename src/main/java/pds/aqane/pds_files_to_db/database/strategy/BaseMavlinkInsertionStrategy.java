package pds.aqane.pds_files_to_db.database.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;

public interface BaseMavlinkInsertionStrategy {

	PreparedStatement prepareStatement(Connection connection, BaseMavlinkStructData data) throws SQLException;
	
	
}
