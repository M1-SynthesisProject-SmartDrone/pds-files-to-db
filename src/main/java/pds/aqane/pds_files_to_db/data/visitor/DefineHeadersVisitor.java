package pds.aqane.pds_files_to_db.data.visitor;

import java.util.List;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;

/**
 * Each struct data have a list of defined headers to be used
 * 
 * @author Aldric Vitali Silvestre
 */
public class DefineHeadersVisitor implements MavlinkStructVisitor<List<String>> {

	@Override
	public List<String> visit(AltitudeStructData data) {
		return null;
	}

	@Override
	public List<String> visit(BatteryStructData data) {
		return null;
	}

	@Override
	public List<String> visit(HighresStructData data) {
		return null;
	}

}
