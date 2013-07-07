package org.laukvik.trainer.anatomy;


public class Leg extends Part {
	
	public Thigh THIGH;
	public Calf CALF;
	
	public Leg(Human human) {
		super( "Leg", human );
	}
}