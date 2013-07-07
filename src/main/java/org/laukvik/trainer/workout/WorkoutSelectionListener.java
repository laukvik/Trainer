package org.laukvik.trainer.workout;

public interface WorkoutSelectionListener {
	
	public void workoutSelected( Workout workout );
	public void workoutsSelected( Workout [] workouts );
	public void noWorkoutSelected();

}