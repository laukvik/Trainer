package org.laukvik.trainer.set;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.units.Weight;
import org.laukvik.trainer.workout.Workout;

public class Set {

	private double weight;
	private int reps;
	private long uid;
	private Workout workout;
	private int sortOrder = -1;
	private String comments;
	
	public Set( double weight, int reps ){
		this.uid = -1;
		this.weight = weight;
		this.reps = reps;
	}
	
	public Set( long uid, double weight, int reps ){
		this.uid = uid;
		this.weight = weight;
		this.reps = reps;
	}
	
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public int getSortOrder() {
		return sortOrder;
	}
	
	public long getUID() {
		return uid;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	
	public Workout getWorkout() {
		return workout;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public Exercise getExercise(){
		if (uid == -1){
			return new Exercise( Muscle.UNKNOWN, -1 ); 
		}
		return getWorkout().getJournal().getExercise( uid );
	}
	
	public String getText(){
		if (getExercise() == null || getExercise().getJournal() == null || getExercise().getJournal().getWeight() == null){
			return "";
		}
		Weight w = getExercise().getJournal().getWeight();
//		Weight w = null;
		return getExercise().getName() + " " + getWeight() + " " + w + " x " + getReps();
	}

	public void setExercise(String value) throws Exception {
		for (Exercise ex :getWorkout().getJournal().getExercises()){
			if (ex.getName().equalsIgnoreCase( value )){
				setUID( ex.getUID() );
				return;
			}
		}
		throw new Exception("Exercise not found!");
	}
	
	public void setExercise( Exercise exercise ){
		setUID( exercise.getUID() );
	}
	
	public void setExercise( long exerciseID ){
		setUID( uid );
	}

	public void setUID(long uid) {
		this.uid = uid;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}