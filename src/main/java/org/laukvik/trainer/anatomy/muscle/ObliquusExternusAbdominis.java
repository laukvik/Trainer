package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Waist;
import org.laukvik.trainer.muscle.HumanMuscle;

public class ObliquusExternusAbdominis extends Muscle implements HumanMuscle {
	
	public ObliquusExternusAbdominis( Waist waist ){
		super( 2, "Obliquus externus abdominis", waist );
	}

}