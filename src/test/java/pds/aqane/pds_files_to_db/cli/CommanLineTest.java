package pds.aqane.pds_files_to_db.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pds.aqane.pds_files_to_db.config.CommandLineHandler;

public class CommanLineTest {

	private CommandLineHandler commandLineHandler = new CommandLineHandler();
	
	@Test
	public void testOnlyFolderCommand() {
		String folderName = "/path/to/file";
		String[] args = {"-f", folderName};
		commandLineHandler.parseCommandLine(args);
		assertEquals(folderName, commandLineHandler.getFolderName());
		assertEquals(1l, commandLineHandler.getIdBegin());
	}
	
	@Test
	public void testFolderAndId() {
		String folderName = "/path/to/file";
		Long idBegin = 13l;
		String[] args = {"-i", idBegin.toString(), "-f", folderName};
		commandLineHandler.parseCommandLine(args);
		assertEquals(folderName, commandLineHandler.getFolderName());
		assertEquals(idBegin.longValue(), commandLineHandler.getIdBegin());
	}
	
	@Test(expected = RuntimeException.class)
	public void testNoFolder() {
		String[] args = {"-i", "13"};
		commandLineHandler.parseCommandLine(args);
	}
	
	@Test(expected = RuntimeException.class)
	public void testUnparsableId() {
		String[] args = {"-i", "azerty1", "-f", "/path/to/folder"};
		commandLineHandler.parseCommandLine(args);
	}
}
