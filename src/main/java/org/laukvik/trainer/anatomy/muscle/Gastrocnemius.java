package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Calf;
import org.laukvik.trainer.muscle.HumanMuscle;

public class Gastrocnemius extends Muscle implements HumanMuscle {
	
	public Gastrocnemius(Calf calf){
		super( 14, "Gastrocnemius", calf );
	}

}
