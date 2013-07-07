package org.laukvik.trainer.muscle;

import java.util.Comparator;

import org.laukvik.trainer.anatomy.muscle.Muscle;

public class MuscleComparator implements Comparator<Muscle> {
	
	public MuscleComparator(){
	}

	public int compare( Muscle muscle1, Muscle muscle2 ) {
		return muscle1.getLatin().compareToIgnoreCase( muscle2.getLatin() );
	}

}