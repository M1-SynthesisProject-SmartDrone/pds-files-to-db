package pds.aqane.pds_files_to_db.data.visitor;

import pds.aqane.pds_files_to_db.data.implems.AltitudeStructData;
import pds.aqane.pds_files_to_db.data.implems.BatteryStructData;
import pds.aqane.pds_files_to_db.data.implems.HighresStructData;

public interface MavlinkStructVisitor<T> {

	T visit(AltitudeStructData data);
	
	T visit(BatteryStructData data);
	
	T visit(HighresStructData data);
}
