package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.UpperArm;
import org.laukvik.trainer.muscle.HumanMuscle;

public class BicepsBrachii extends Muscle implements HumanMuscle {
	
	public BicepsBrachii(UpperArm upperArm){
		super( 8, "Biceps Brachii", upperArm );
	}
	
}