package pds.aqane.pds_files_to_db.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * This is a pre-test just testing if the url is well made and the identifiants are corrrect.
 * 
 * @author Aldric Vitali Silvestre
 */
public class DatabaseConnectionTest {

	@Test
	public void testConnection() {
		DatabaseConnection.getConnection();
	}
	
	@Test
	public void runSimpleQuery() throws SQLException {
		Statement statement = DatabaseConnection.getConnection().createStatement();
		statement.execute("SELECT 'test success';");
		ResultSet resultSet = statement.getResultSet();
		boolean haveNext = resultSet.next();
		assertTrue(haveNext);
		assertEquals("test success", resultSet.getString(1));
	}
}
