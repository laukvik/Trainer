package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.UpperArm;
import org.laukvik.trainer.muscle.HumanMuscle;

public class TricepsBrachii extends Muscle implements HumanMuscle {
	
	public TricepsBrachii(UpperArm upperArm){
		super( 6, "Triceps Brachii", upperArm );
	}

}