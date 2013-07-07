package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Buttocks;
import org.laukvik.trainer.muscle.HumanMuscle;

public class GluteusMaximus extends Muscle implements HumanMuscle {

	public GluteusMaximus(Buttocks buttocks){
		super( 7, "Gluteus maximus", buttocks );
	}
	
}