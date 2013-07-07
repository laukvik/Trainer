package org.laukvik.trainer.exercise;

import org.laukvik.trainer.anatomy.muscle.Muscle;

public interface ExerciseListener {

	public void exerciseChanged( Exercise exercise );
	public void exerciseRemoved( Exercise exercise, Muscle muscle );
	public void exerciseAdded( Exercise exercise );
	public void exerciseChangedMuscle( Exercise exercise, Muscle fromMuscle, Muscle toMusle );
	public void exerciseEnabled( Exercise exercise );
	public void exerciseDisabled( Exercise exercise );
	
}