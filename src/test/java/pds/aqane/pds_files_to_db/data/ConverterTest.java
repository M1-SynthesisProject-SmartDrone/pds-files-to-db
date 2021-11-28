package pds.aqane.pds_files_to_db.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import pds.aqane.pds_files_to_db.data.converter.MavlinkStructConverter;

/**
 * From multiple map with headers, parse them and check if parsing succeded.
 * 
 * @author Aldric Vitali Silvestre
 */
public class ConverterTest {

	MavlinkStructConverter converter = new MavlinkStructConverter();

	@Test
	public void parseAltitudeData() {
		Map<String, String> toTest = ALTITUDE_LINE_REF;
		AltitudeStructData altitudeStructData = (AltitudeStructData) converter.convertLineWithHeadersOrThrow(toTest, MavlinkStructName.ALTITUDE);
		assertEquals(123456, altitudeStructData.getTimestamp());
		assertEquals(0.1234, altitudeStructData.getAltitudeMonotonic(), 0.0001);
	}
	
	@Test
	public void parseAltitudeDataWithOptional() {
		Map<String, String> toTest = ALTITUDE_LINE_REF;
		Optional<BaseMavlinkStructData> result = converter.convertLineWithHeaders(toTest, MavlinkStructName.ALTITUDE);
		assertTrue(result.isPresent());
		assertTrue(result.get() instanceof AltitudeStructData);
		AltitudeStructData altitudeStructData = (AltitudeStructData) result.get();
		assertEquals(123456, altitudeStructData.getTimestamp());
		assertEquals(0.1234, altitudeStructData.getAltitudeMonotonic(), 0.0001);
	}

	@Test
	public void parseBatteryData() {
		Map<String, String> toTest = BATTERY_LINE_REF;
		BatteryStructData structData = (BatteryStructData) converter.convertLineWithHeadersOrThrow(toTest, MavlinkStructName.BATTERY_STATUS);
		assertEquals(12, structData.getEnergyConsumed());
		assertEquals(78, structData.getTemperature());
	}
	
	@Test
	public void parseHighresData() {
		Map<String, String> toTest = HIGHRES_LINE_REF;
		HighresStructData structData = (HighresStructData) converter.convertLineWithHeadersOrThrow(toTest, MavlinkStructName.HIGHRES);
		assertEquals(2.2222, structData.getAbsPressure(), 0.0001);
		assertEquals(Float.NaN, structData.getXacc(), 0.0001);
		assertEquals(Float.NaN, structData.getYacc(), 0.0001);
		assertEquals(78.12, structData.getYmag(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void parseHighres_noTemperature() {
		// don't modif the original
		Map<String, String> copy = deepCopy(HIGHRES_LINE_REF);
		copy.remove("abs_pressure");
		converter.convertLineWithHeadersOrThrow(copy, MavlinkStructName.HIGHRES);
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseHighres_notFloat() {
		// don't modif the original
		Map<String, String> copy = deepCopy(HIGHRES_LINE_REF);
		copy.put("abs_pressure", "test");
		converter.convertLineWithHeadersOrThrow(copy, MavlinkStructName.HIGHRES);
	}

	private Map<String, String> deepCopy(Map<String, String> toCopy) {
		return toCopy.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
	}
	
	private static final Map<String, String> ALTITUDE_LINE_REF = Map.of(
			"timestamp", "123456",
			"altitude_monotonic", "0.1234",
			"altitude_amsl", "nan",
			"altitude_local", "0.1234",
			"altitude_relative", "0.1234",
			"altitude_terrain", "0.1234",
			"bottom_clearance", "0.1234");

	// More than 10 entries, we cannot use Map.of
	private static final Map<String, String> HIGHRES_LINE_REF = Map.ofEntries(
			Map.entry("timestamp", "123456"),
			Map.entry("xacc", "-nan"), Map.entry("yacc", "nan"), Map.entry("zacc", "nan"),
			Map.entry("xgyro", "0.156"), Map.entry("ygyro", "34.9865"), Map.entry("zgyro", "23.1234"),
			Map.entry("xmag", "0.156"), Map.entry("ymag", "78.12"), Map.entry("zmag", "23.1234"),
			Map.entry("abs_pressure", "2.2222"),
			Map.entry("diff_pressure", "5.3234"),
			Map.entry("pressure_alt", "123.456"),
			Map.entry("temperature", "30.45")
	);
	
	private static final Map<String, String> BATTERY_LINE_REF = Map.of(
			"timestamp", "123456",
			"current_consumed", "23",
			"energy_consumed", "12",
			"temperature", "78",
			"current_battery", "89",
			"battery_remaining", "11",
			"time_remaining", "960");
}
