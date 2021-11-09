package pds.aqane.pds_files_to_db.data;

import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

public class AltitudeStructData extends BaseMavlinkStructData {

	private float altitudeMonotonic;
	private float altitudeAmsl;
	private float altitudeLocal;
	private float altitudeRelative;
	private float altitudeTerrain;
	private float bottomClearance;
	
	public AltitudeStructData() {
		super(MavlinkStructName.ALTITUDE);
	}

	public float getAltitudeMonotonic() {
		return altitudeMonotonic;
	}

	public void setAltitudeMonotonic(float altitudeMonotonic) {
		this.altitudeMonotonic = altitudeMonotonic;
	}

	public float getAltitudeAmsl() {
		return altitudeAmsl;
	}

	public void setAltitudeAmsl(float altitudeAmsl) {
		this.altitudeAmsl = altitudeAmsl;
	}

	public float getAltitudeLocal() {
		return altitudeLocal;
	}

	public void setAltitudeLocal(float altitudeLocal) {
		this.altitudeLocal = altitudeLocal;
	}

	public float getAltitudeRelative() {
		return altitudeRelative;
	}

	public void setAltitudeRelative(float altitudeRelative) {
		this.altitudeRelative = altitudeRelative;
	}

	public float getAltitudeTerrain() {
		return altitudeTerrain;
	}

	public void setAltitudeTerrain(float altitudeTerrain) {
		this.altitudeTerrain = altitudeTerrain;
	}

	public float getBottomClearance() {
		return bottomClearance;
	}

	public void setBottomClearance(float bottomClearance) {
		this.bottomClearance = bottomClearance;
	}

	@Override
	public <T> T accept(MavlinkStructVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
