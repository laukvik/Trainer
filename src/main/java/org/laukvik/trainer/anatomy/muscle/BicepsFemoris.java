package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Thigh;
import org.laukvik.trainer.muscle.HumanMuscle;

/**
 * Dette er egentlig hamstrings
 * 
 * @author morten
 *
 */
public class BicepsFemoris extends Muscle implements HumanMuscle {
	
	public BicepsFemoris( Thigh thigh ){
		super( 12, "Biceps femoris", thigh );
	}

}
