package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Waist;
import org.laukvik.trainer.muscle.HumanMuscle;

public class ObliquusInternusAbdominis extends Muscle implements HumanMuscle {
	
	public ObliquusInternusAbdominis( Waist waist ){
		super( 3, "Obliquus internus abdominis", waist );
	}

}
