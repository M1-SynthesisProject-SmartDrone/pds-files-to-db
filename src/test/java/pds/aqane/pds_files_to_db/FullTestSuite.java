package pds.aqane.pds_files_to_db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pds.aqane.pds_files_to_db.cli.CommanLineTest;
import pds.aqane.pds_files_to_db.data.ConverterTest;
import pds.aqane.pds_files_to_db.file.FileTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FileTestSuite.class,
	ConverterTest.class,
	CommanLineTest.class
})
public class FullTestSuite {

}
