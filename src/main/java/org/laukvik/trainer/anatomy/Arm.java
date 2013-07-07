package org.laukvik.trainer.anatomy;


public class Arm extends Part{
	
	public UpperArm UPPER;
	public LowerArm LOWER;
	
	public Arm(Human human){
		super( "Arm", human );
	}
	
}