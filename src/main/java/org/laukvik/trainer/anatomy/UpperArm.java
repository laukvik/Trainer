package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.BicepsBrachii;
import org.laukvik.trainer.anatomy.muscle.TricepsBrachii;

public class UpperArm extends Part {
	
	public TricepsBrachii TRICEPS_BRACHII;
	public BicepsBrachii BICEPS_BRACHII;

	public UpperArm( Arm arm ){
		super("UpperArm", arm );
	}

}
