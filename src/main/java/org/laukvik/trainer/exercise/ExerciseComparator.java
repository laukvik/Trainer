package org.laukvik.trainer.exercise;

import java.util.Comparator;

public class ExerciseComparator implements Comparator<Exercise> {
	
	/**
	 * Compares and sorts by enabled exercises first, then its
	 * sorted by name
	 * 
	 */
	public ExerciseComparator(){
	}

	public int compare( Exercise w1, Exercise w2 ) {
		if (w1.isEnabled() && w2.isEnabled()){
			/* Compare is evaluated by name */
		} else if (!w1.isEnabled() && w2.isEnabled()){
			return 1;
		} else if (w1.isEnabled() && !w2.isEnabled()){
			return -1;
		}
		return w1.getName().compareTo( w2.getName() );
	}

}