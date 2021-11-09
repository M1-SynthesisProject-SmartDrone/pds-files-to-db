package pds.aqane.pds_files_to_db.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;

/**
 * Insert Mavlink structs (or batches) into the database. We have one entry
 * point from the outside, but with all instances of structs implementations.
 * 
 * @author Aldric Vitali Silvestre
 */
public class MavlinkDbInserter {
	
	private static final Logger LOGGER = Logger.getLogger(MavlinkDbInserter.class);

	private static final int MAX_BATCH_SIZE = 50;

	// No internal changes
	CreateInsertQueryVisitor queryVisitor = new CreateInsertQueryVisitor();
	
	/**
	 * Insert a list of struct data into the database. We assume that all the batch contains elements of the same struct type.
	 * @param structDataList
	 * @throws SQLException
	 * @throws IllegalArgumentException If there is a problem putting parameters in prepared statement
	 */
	public void insertBatch(List<BaseMavlinkStructData> structDataList) throws SQLException, IllegalArgumentException {
		if(structDataList.size() == 0) {
			return;
		}
		LOGGER.info("Inserting " + structDataList.size() + " rows");
		Connection connection = DatabaseConnection.getConnection();
		String query = structDataList.get(0).accept(queryVisitor);
		PreparedStatement statement = connection.prepareStatement(query);
		// Take care of a statement, so we need a new one for each query
		var defineStatementVisitor = new DefineStatementVisitor(statement);
		int counter = 0;
		for (var data : structDataList) {
			statement = data.accept(defineStatementVisitor);
			statement.addBatch();
			counter++;
			if (counter % MAX_BATCH_SIZE == 0 || counter == structDataList.size()) {
				int[] resultCodes = statement.executeBatch();
				// Find how many rows were not inserted
				long insertFailCount = Arrays.stream(resultCodes).filter(code -> code == Statement.EXECUTE_FAILED).count();
				if(insertFailCount > 0) {
					LOGGER.warn(insertFailCount + " rows were not inserted");
				}
			}
		}
	}
	
	public void insertOne(BaseMavlinkStructData data) throws SQLException, IllegalArgumentException {
		Connection connection = DatabaseConnection.getConnection();
		String query = data.accept(queryVisitor);
		PreparedStatement statement = connection.prepareStatement(query);
		var defineStaementVisitor = new DefineStatementVisitor(statement);
		statement = data.accept(defineStaementVisitor);
		statement.executeUpdate();
	}
}
