package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Chest;
import org.laukvik.trainer.muscle.HumanMuscle;

public class PectoralisMajor extends Muscle implements HumanMuscle {
	
	public PectoralisMajor( Chest chest ){
		super( 15, "Pectoralis major", chest );
	}

}
