package pds.aqane.pds_files_to_db.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * File wrapper used to read csv more easily. This class is designed to read the
 * file content once.
 *
 * @author Aldric Vitali Silvestre
 *
 */
public class CSVFileReader {

	private File file;

	private String separator;

	private BufferedReader bufferedReader;

	private String[] headers;
	
	private String lastLineRead = null;

	public CSVFileReader(String filename, String separator) {
		file = new File(filename);
		this.separator = separator;
	}
	
	public boolean hasMoreLinesToRead() {
		return lastLineRead != null;
	}
	
	public String getBaseFilename() {
		return file.getName();
	}

	/**
	 * Read the first line of the file in order to store headers. The cursor in the
	 * file will therefore move.
	 * 
	 * @throws IOException
	 */
	public void openFileAndReadHeaders() throws IOException {
		bufferedReader = new BufferedReader(new FileReader(file));
		// Read all headers directly
		String line = bufferedReader.readLine();
		lastLineRead = line;
		headers = line.split(separator);
		
	}

	public List<Map<String, String>> readBatchMappedToHeaders(int nbLines) throws IOException {
		List<String> batch = readBatch(nbLines);
		return batch.stream()
				.map(this::mapLineWithHeaders)
				.collect(Collectors.toList());
	}

	/**
	 * Read nbLines from the current cursor position in the file. If it don't
	 * remains nbLines lines in the file, the output list will be shorter.
	 * 
	 * @throws IOException
	 */
	private List<String> readBatch(int nbLines) throws IOException {
		String line;
		List<String> list = new ArrayList<>(nbLines);
		for (int index = 0; index < nbLines; index++) {
			line = bufferedReader.readLine();
			lastLineRead = line;
			if (line == null) {
				break;
			}
			list.add(line);
		}
		return list;
	}

	private Map<String, String> mapLineWithHeaders(String line) {
		if (headers == null) {
			throw new IllegalStateException("Headers are not initialized");
		}
		String[] split = line.split(separator);
		if (split.length != headers.length) {
			throw new IllegalArgumentException(
					"Line read have " + split.length + " elements wherehas we have " + headers.length + " headers. Cannot map line");
		}
		Map<String, String> map = new HashMap<>(split.length);
		for (int index = 0; index < split.length; index++) {
			map.put(headers[index], split[index]);
		}
		return map;
	}
}
