package pds.aqane.pds_files_to_db.main;

public class App {
	
	public static void main(String[] args) {
		FileConverterFacade facade = new FileConverterFacade();
		facade.init(args);
		facade.processFolder();
	}
}
