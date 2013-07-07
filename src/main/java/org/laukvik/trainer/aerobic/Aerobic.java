package org.laukvik.trainer.aerobic;

public class Aerobic {
	
	/** Name of aerobic exercise */
	private String name;
	
	/** Duration in milliseconds */
	private long duration = 0;
	
	/** Distance in meters */
	private float distance = 0;
	
	/** Average heart rate */
	private int heartRate = 0;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	protected Aerobic( String name ){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public boolean isEmpty(){
		return duration == 0 && distance == 0;
	}
	
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	
	public int getHeartRate() {
		return heartRate;
	}
	
}