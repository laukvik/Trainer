package org.laukvik.trainer.workout;

import org.laukvik.trainer.set.Set;

public interface WorkoutListener {
	
	public void workoutOrderChanged();
	public void workoutDateChanged( Workout workout );
	public void workoutNotesChanged( Workout workout );
	
	public void workoutChanged( Workout workout );
	public void workoutAdded( Workout workout );
	public void workoutRemoved( Workout workout );
	public void workoutsRemoved( Workout[] workouts );

	public void setChanged( Set set );
	public void setAdded( Set set );
	public void setRemoved( Set set );
	
}