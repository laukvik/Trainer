package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Shoulder;
import org.laukvik.trainer.muscle.HumanMuscle;

public class Deltoideus extends Muscle implements HumanMuscle {
	
	public Deltoideus(Shoulder shoulders){
		super( 5, "Deltoideus", shoulders );
	}

}