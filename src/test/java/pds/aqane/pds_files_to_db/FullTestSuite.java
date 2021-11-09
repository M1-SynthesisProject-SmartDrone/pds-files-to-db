package pds.aqane.pds_files_to_db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pds.aqane.pds_files_to_db.cli.CommandLineTest;
import pds.aqane.pds_files_to_db.data.ConverterTest;
import pds.aqane.pds_files_to_db.file.FileTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FileTestSuite.class,
	ConverterTest.class,
	CommandLineTest.class
})
public class FullTestSuite {

}
