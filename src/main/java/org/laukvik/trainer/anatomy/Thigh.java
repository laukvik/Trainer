package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.BicepsFemoris;
import org.laukvik.trainer.anatomy.muscle.QuadricepsFemoris;

public class Thigh extends Part{
	
	public BicepsFemoris BICEPS_FEMORIS;
	public QuadricepsFemoris QUADRICEPS_FEMORIS;

	public Thigh(Leg leg) {
		super("Thigh", leg );
	}

}