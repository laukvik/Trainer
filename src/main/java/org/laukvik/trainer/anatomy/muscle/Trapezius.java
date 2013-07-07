package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.UpperBack;
import org.laukvik.trainer.muscle.HumanMuscle;

public class Trapezius extends Muscle implements HumanMuscle {
	
	public Trapezius( UpperBack back ){
		super( 17, "Trapezius", back );
	}

}
