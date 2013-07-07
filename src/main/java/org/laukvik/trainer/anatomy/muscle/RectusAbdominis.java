package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Waist;
import org.laukvik.trainer.muscle.HumanMuscle;

public class RectusAbdominis extends Muscle implements HumanMuscle{
	
	public RectusAbdominis( Waist waist ){
		super( 1, "Rectus abdominis", waist );
	}

}