package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Thigh;
import org.laukvik.trainer.muscle.HumanMuscle;

public class QuadricepsFemoris extends Muscle implements HumanMuscle {
	
	public QuadricepsFemoris(Thigh thigh){
		super( 16, "Quadriceps femoris", thigh );
	}

}
