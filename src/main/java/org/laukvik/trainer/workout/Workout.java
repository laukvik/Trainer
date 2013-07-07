package org.laukvik.trainer.workout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import org.laukvik.trainer.aerobic.Aerobic;
import org.laukvik.trainer.aerobic.Biking;
import org.laukvik.trainer.aerobic.Running;
import org.laukvik.trainer.aerobic.Swimming;
import org.laukvik.trainer.aerobic.Walking;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.set.Set;

public class Workout {
	
	private GregorianCalendar date;
	private String notes = "";
	private Vector<Set> sets = new Vector<Set>();
	private Journal journal;
	private Biking biking;
	private Running running;
	private Swimming swimming;
	private Walking walking;
	
	public Workout( long millis, String notes ){
		setDate( millis );
		this.notes = notes;
		biking = new Biking();
		running = new Running();
		swimming = new Swimming();
		walking = new Walking();
	}

	public Workout( Date date, String notes ){
		setDate( date );
		this.notes = notes;
		biking = new Biking();
		running = new Running();
		swimming = new Swimming();
		walking = new Walking();
	}
	
	public void add( Set set ){
		set.setWorkout( this );
		set.setSortOrder( sets.size() );
		sets.add( set );
	}
	
	public void remove( Set set ){
		set.setWorkout( null );
		sets.remove( set );
		resort();
	}
	
	private void resort(){
		int x=0;
		for (Set s : sets){
			s.setSortOrder( x );
			x++;
		}
	}
	
	public void remove(Set[] set) {
		sets.remove( set );
		for (Set s : set){
			s.setWorkout( null );
		}
		resort();
	}
	
	public Vector<Set> getSets() {
		return sets;
	}
	
	public GregorianCalendar getCalendar(){
		return date;
	}
	
	public Date getDate(){
		return date.getTime();
	}
	
	public void setDate( long millis ){
		this.date = new GregorianCalendar();
		this.date.setTimeInMillis( millis );
	}
	
	public void setDate(Date date) {
		this.date = new GregorianCalendar();
		this.date.setTimeInMillis( date.getTime() );
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	
	public Journal getJournal() {
		return journal;
	}

	public long getMillis() {
		return date.getTimeInMillis();
	}

	public Set getMaximumWeightSet( Exercise ex ) {
		double max = 0;
		Set s = null;
		for (Set set : sets){
			if (set.getExercise() != null && set.getExercise().getUID() == ex.getUID()){
				if (set.getWeight() > max){
					max = set.getWeight();
					s = set;
				}
			}
		}
		return s;
	}

	public void setDate( GregorianCalendar date ) {
		setDate( date.getTime() );
	}

	public Biking getBiking() {
		return biking;
	}

	public void setBiking(Biking biking) {
		this.biking = biking;
	}

	public Running getRunning() {
		return running;
	}

	public void setRunning(Running running) {
		this.running = running;
	}

	public Swimming getSwimming() {
		return swimming;
	}

	public void setSwimming(Swimming swimming) {
		this.swimming = swimming;
	}

	public Walking getWalking() {
		return walking;
	}

	public void setWalking(Walking walking) {
		this.walking = walking;
	}
	
	public Aerobic [] listAerobics(){
		return new Aerobic [] { running, walking, biking, swimming };
	}

	public String toString(Locale locale) {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", locale );
		return format.format( date.getTime() ) + " (" + getSets().size() + ")";
	}
	
}