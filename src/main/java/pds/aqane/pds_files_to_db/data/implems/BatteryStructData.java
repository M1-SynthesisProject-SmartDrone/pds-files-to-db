package pds.aqane.pds_files_to_db.data.implems;

import pds.aqane.pds_files_to_db.data.BaseMavlinkStructData;
import pds.aqane.pds_files_to_db.data.MavlinkStructs;
import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

public class BatteryStructData extends BaseMavlinkStructData {

	private int currentConsumed;
	private int energyConsumed;
	private int temperature;
	private int currentBattery;
	private int batteryRemaining;
	private int timeRemaining;
	
	public BatteryStructData() {
		super(MavlinkStructs.BATTERY_STATUS);
	}

	public int getCurrentConsumed() {
		return currentConsumed;
	}

	public void setCurrentConsumed(int currentConsumed) {
		this.currentConsumed = currentConsumed;
	}

	public int getEnergyConsumed() {
		return energyConsumed;
	}

	public void setEnergyConsumed(int energyConsumed) {
		this.energyConsumed = energyConsumed;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getCurrentBattery() {
		return currentBattery;
	}

	public void setCurrentBattery(int currentBattery) {
		this.currentBattery = currentBattery;
	}

	public int getBatteryRemaining() {
		return batteryRemaining;
	}

	public void setBatteryRemaining(int batteryRemaining) {
		this.batteryRemaining = batteryRemaining;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	@Override
	public <T> T accept(MavlinkStructVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
