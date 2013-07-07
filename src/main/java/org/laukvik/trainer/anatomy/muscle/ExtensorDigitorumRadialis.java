package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.LowerArm;
import org.laukvik.trainer.muscle.HumanMuscle;

public class ExtensorDigitorumRadialis extends Muscle implements HumanMuscle {
	
	public ExtensorDigitorumRadialis( LowerArm lowerArm ){
		super( 10, "Extensor carpi radialis", lowerArm );
	}

}
