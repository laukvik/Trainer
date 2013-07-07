package org.laukvik.trainer.workout;

import java.util.Comparator;

public class WorkoutComparator implements Comparator<Workout> {
	
	public WorkoutComparator(){
	}

	public int compare( Workout w1, Workout w2 ) {
		if (w1.getMillis() > w2.getMillis()){
			return 0;
		} else {
			return 1;
		}
	}

}