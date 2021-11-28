package pds.aqane.pds_files_to_db.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.DataBufferInt;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hsqldb.cmdline.SqlFile;
import org.junit.Before;
import org.junit.Test;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.implems.AltitudeStructData;

/**
 * Insert a list of data into databse and check if insertions were made correctly.
 * Uses the "schema.sql" file in the "main/resources" folder
 * 
 * This test is designed to work with H2 databases.
 * The queries used are in general SQL language, so changing database will not change anything normally.
 * 
 * @author Aldric Vitali Silvestre
 */
public class InsertDataTest {

	private static final int DATALIST_SIZE = 100;
	
	@Before
	public void initDatabase() throws Exception {
		Connection connection = DatabaseConnection.getConnection();
		// Create tables
		URI uri = InsertDataTest.class.getClassLoader().getResource("schema.sql").toURI();
		File inputFile = new File(uri);
		SqlFile sqlFile = new SqlFile(inputFile);
		sqlFile.setConnection(connection);
		sqlFile.execute();
		
		// Check if script have executed successfully
		Statement statement = connection.createStatement();
		statement.execute("SELECT COUNT(*) FROM altitude");
		ResultSet resultSet = statement.getResultSet();
		boolean hasNext = resultSet.next();
		assertTrue(hasNext);
		assertEquals(0, resultSet.getInt(1));
	}
	
	@Test
	public void testInsertionAltitude() throws IllegalArgumentException, SQLException {
		List<BaseMavlinkStructData> altidudeList = createAltitudeList(DATALIST_SIZE);
		MavlinkDbInserter inserter = new MavlinkDbInserter();
		inserter.insertBatch(altidudeList);
		
		Connection connection = DatabaseConnection.getConnection();
		
		// Count
		Statement statement = connection.createStatement();
		statement.execute("SELECT COUNT(*) FROM altitude;");
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		assertEquals(DATALIST_SIZE, resultSet.getInt(1));
		
		// select first and check values
		Statement statement2 = connection.createStatement();
		statement2.execute("SELECT * FROM altitude WHERE id = 1;");
		ResultSet resultSet2 = statement2.getResultSet();
		resultSet2.next();
		assertEquals(1, resultSet2.getInt("id"));
		assertEquals(0l, resultSet2.getLong("timestamp"));
		assertEquals(Float.NaN, resultSet2.getFloat("altitude_amsl"), 0.00001f);
	}
	
	private List<BaseMavlinkStructData> createAltitudeList(int size) {
		return IntStream.range(0, size)
				.mapToObj(this::altStructDataFromInt)
				.collect(Collectors.toList());
	}
	
	private AltitudeStructData altStructDataFromInt(int i) {
		var data = new AltitudeStructData();
		data.setTimestamp(i);
		data.setAltitudeAmsl(i);
		data.setAltitudeLocal(Float.NaN);
		data.setAltitudeMonotonic(i);
		data.setAltitudeRelative(i);
		data.setAltitudeTerrain(i);
		data.setBottomClearance(i);
		return data;
	}
}
