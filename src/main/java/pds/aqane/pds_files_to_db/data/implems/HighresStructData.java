package pds.aqane.pds_files_to_db.data.implems;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.MavlinkStructs;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

public class HighresStructData extends BaseMavlinkStructData {

	private float xacc;
	private float yacc;
	private float zacc;

	private float xgyro;
	private float ygyro;
	private float zgyro;

	private float xmag;
	private float ymag;
	private float zmag;

	private float absPressure;
	private float diffPressure;
	private float pressureAlt;
	private float temperature;

	public HighresStructData() {
		super(MavlinkStructs.HIGHRES);
	}

	public float getXacc() {
		return xacc;
	}

	public void setXacc(float xacc) {
		this.xacc = xacc;
	}

	public float getYacc() {
		return yacc;
	}

	public void setYacc(float yacc) {
		this.yacc = yacc;
	}

	public float getZacc() {
		return zacc;
	}

	public void setZacc(float zacc) {
		this.zacc = zacc;
	}

	public float getXgyro() {
		return xgyro;
	}

	public void setXgyro(float xgyro) {
		this.xgyro = xgyro;
	}

	public float getYgyro() {
		return ygyro;
	}

	public void setYgyro(float ygyro) {
		this.ygyro = ygyro;
	}

	@Override
	public <T> T accept(MavlinkStructVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public float getZgyro() {
		return zgyro;
	}

	public void setZgyro(float zgyro) {
		this.zgyro = zgyro;
	}

	public float getXmag() {
		return xmag;
	}

	public void setXmag(float xmag) {
		this.xmag = xmag;
	}

	public float getYmag() {
		return ymag;
	}

	public void setYmag(float ymag) {
		this.ymag = ymag;
	}

	public float getZmag() {
		return zmag;
	}

	public void setZmag(float zmag) {
		this.zmag = zmag;
	}

	public float getAbsPressure() {
		return absPressure;
	}

	public void setAbsPressure(float absPressure) {
		this.absPressure = absPressure;
	}

	public float getPressureAlt() {
		return pressureAlt;
	}

	public void setPressureAlt(float pressureAlt) {
		this.pressureAlt = pressureAlt;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getDiffPressure() {
		return diffPressure;
	}

	public void setDiffPressure(float diffPressure) {
		this.diffPressure = diffPressure;
	}
}
