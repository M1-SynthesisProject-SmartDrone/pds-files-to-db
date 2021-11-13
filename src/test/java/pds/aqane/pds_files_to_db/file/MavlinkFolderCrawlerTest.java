package pds.aqane.pds_files_to_db.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import pds.aqane.pds_files_to_db.data.MavlinkStructName;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkCSVFileReader;
import pds.aqane.pds_files_to_db.file.mavlink.MavlinkFolderCrawler;

/**
 * This test will create a folder in this package, put empty files in it and
 * remove them at the end of the test.
 * 
 * @author Aldric Vitali Silvestre
 */
public class MavlinkFolderCrawlerTest {

	// We are sure that this folder will be destroyed anyways (test success or fail)
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Before
	public void initTempFiles() throws IOException {
		BiFunction<String, Integer, String> createTmpFile = (name, id) -> name + "-" + id + ".csv";
		// Files to create in the temp folder
		List<String> filenameList = List.of(
				createTmpFile.apply(MavlinkStructName.BATTERY_STATUS.getStructName(), 1),
				createTmpFile.apply(MavlinkStructName.BATTERY_STATUS.getStructName(), 2),
				createTmpFile.apply(MavlinkStructName.ALTITUDE.getStructName(), 1),
				createTmpFile.apply(MavlinkStructName.ALTITUDE.getStructName(), 2),
				createTmpFile.apply(MavlinkStructName.ALTITUDE.getStructName(), 3),
				createTmpFile.apply(MavlinkStructName.HIGHRES.getStructName(), 1),
				createTmpFile.apply(MavlinkStructName.ALTITUDE.getStructName(), 4),
				createTmpFile.apply("pepito", 1));
		
		for (String filename : filenameList) {
			tempFolder.newFile(filename);
		}
	}

	@Test
	public void findAllCorrectFiles() {
		MavlinkFolderCrawler crawler = new MavlinkFolderCrawler(tempFolder.getRoot().getAbsolutePath(), 1);
		Map<MavlinkStructName, List<MavlinkCSVFileReader>> wantedFiles = crawler.findAllWantedFiles();
		long fileCount = wantedFiles.values().stream()
				.flatMap(List::stream)
				.count();
		assertEquals(7, fileCount);
		
		assertEquals(4, wantedFiles.get(MavlinkStructName.ALTITUDE).size());
		assertEquals(2, wantedFiles.get(MavlinkStructName.BATTERY_STATUS).size());
		assertEquals(1, wantedFiles.get(MavlinkStructName.HIGHRES).size());
	}

	@Test
	public void findCorrectFilesFromSpecificId() {
		MavlinkFolderCrawler crawler = new MavlinkFolderCrawler(tempFolder.getRoot().getAbsolutePath(), 2);
		Map<MavlinkStructName, List<MavlinkCSVFileReader>> wantedFiles = crawler.findAllWantedFiles();
		long fileCount = wantedFiles.values().stream()
				.flatMap(List::stream)
				.count();
		assertEquals(4, fileCount);
		
		assertEquals(3, wantedFiles.get(MavlinkStructName.ALTITUDE).size());
		assertEquals(1, wantedFiles.get(MavlinkStructName.BATTERY_STATUS).size());
		assertFalse(wantedFiles.containsKey(MavlinkStructName.HIGHRES));
	}
}
