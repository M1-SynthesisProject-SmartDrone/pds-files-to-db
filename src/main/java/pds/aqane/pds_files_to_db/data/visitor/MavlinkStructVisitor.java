package pds.aqane.pds_files_to_db.data.visitor;

import pds.aqane.pds_files_to_db.data.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.BatteryStructData;
import pds.aqane.pds_files_to_db.data.HighresStructData;

public interface MavlinkStructVisitor<T> {

	T visit(AltitudeStructData data);
	
	T visit(BatteryStructData data);
	
	T visit(HighresStructData data);
}
