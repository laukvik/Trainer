package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Shoulder;
import org.laukvik.trainer.muscle.HumanMuscle;

public class SerratusAnterior extends Muscle implements HumanMuscle {
	
	public SerratusAnterior( Shoulder shoulder ){
		super( 11, "Serratus anterior", shoulder );
	}

}
