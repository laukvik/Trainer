package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.LowerBack;
import org.laukvik.trainer.muscle.HumanMuscle;

public class ErectusSpinae extends Muscle implements HumanMuscle {
	
	public ErectusSpinae(LowerBack back){
		super( 13, "Erectus spinae", back );
	}
	
}
