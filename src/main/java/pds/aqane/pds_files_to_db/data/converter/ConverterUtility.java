package pds.aqane.pds_files_to_db.data.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * All globals methods that can apply to converting things
 * 
 * @author Aldric Vitali Silvestre
 */
public class ConverterUtility {

	/**
	 * Replace all "nan" or "-nan" c++ strings to valid "Nan" or "-NaN" values
	 * supported by Java.
	 * 
	 */
	public static Map<String, String> convertCppNans(Map<String, String> toConvert) {
		return toConvert.entrySet().stream()
				.map(ConverterUtility::replaceCppNan)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private static Entry<String, String> replaceCppNan(Entry<String, String> entry) {
		return Map.entry(entry.getKey(), entry.getValue().replace("nan", "NaN"));
	}
}
