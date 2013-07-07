package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.MiddleBack;
import org.laukvik.trainer.muscle.HumanMuscle;

public class LatissimusDorsi extends Muscle implements HumanMuscle {
	
	public LatissimusDorsi( MiddleBack back){
		super( 4, "Latissimus dorsi", back );
	}
	
}