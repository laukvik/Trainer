package org.laukvik.trainer.journal;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;
import org.laukvik.swing.locale.LocaleListener;

import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.exercise.ExerciseComparator;
import org.laukvik.trainer.exercise.ExerciseListener;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetListener;
import org.laukvik.trainer.set.SetSelectionListener;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.units.Distance;
import org.laukvik.trainer.units.Kilogram;
import org.laukvik.trainer.units.Kilometer;
import org.laukvik.trainer.units.Weight;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutComparator;
import org.laukvik.trainer.workout.WorkoutListener;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

/**
 * @author morten
 *
 */
public class Journal {
	
	private String owner = "Unknown";
	private Date birthday = new Date(); 
	private int sex = UNKNOWN;
	private long authorID = System.currentTimeMillis();
	
	public final static int MALE = 1;
	public final static int UNKNOWN = 0;
	public final static int FEMALE = -1;
	
	private Vector <Exercise> excercises = new Vector<Exercise>();
	private Vector <Workout> workouts = new Vector<Workout>();
	private Vector <JournalListener> journalListeners;
	private Vector <ExerciseListener> exerciseListeners;
	private Vector <WorkoutListener> workoutListeners;
	private Vector <SetListener> setListeners;
	private Vector <WorkoutSelectionListener> workoutSelectionListeners;
	private Vector <SetSelectionListener> setSelectionListeners;
	private Vector <LocaleListener> localeListeners;
	
	private Vector <Status> statuses = new Vector<Status>();
	
	public final static long MILLISECOND	= 1;
	public final static long SECOND			= 1000 * MILLISECOND;
	public final static long MINUTE			= 60 * SECOND;
	public final static long HOUR			= 60 * MINUTE;
	public final static long DAY			= 24 * HOUR;
	public final static long WEEK			= 7 * DAY;
	public final static long MONTH			= 30 * DAY;
	public final static long YEAR			= 365 * DAY;
	
	private Locale locale;
	private Weight weight;
	private Distance distance;
	/* Preferences */
	private Date lastUsed, lastChanged;
	
	
	public Journal(){
		owner = System.getProperty("user.name");
		journalListeners = new Vector<JournalListener>();
		exerciseListeners = new Vector<ExerciseListener>();
		workoutListeners = new Vector<WorkoutListener>();
		setListeners = new Vector<SetListener>();
		workoutSelectionListeners = new Vector<WorkoutSelectionListener>();
		setSelectionListeners = new Vector<SetSelectionListener>();
		localeListeners = new Vector<LocaleListener>();
		locale = JournalHelper.getDefaultUnknownLocale();
		weight = new Kilogram();
		distance = new Kilometer();
	}
	
	public void setAuthorID(long authorID) {
		this.authorID = authorID;
	}
	
	public long getAuthorID() {
		return authorID;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight( Weight weight ) {
		this.weight = weight;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance( Distance distance ) {
		this.distance = distance;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		fireLocaleChanged( locale );
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public boolean isMale(){
		return this.sex == MALE;
	}
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		if (sex == UNKNOWN || sex == MALE || sex == FEMALE){
			this.sex = sex;
		} else {
			this.sex = UNKNOWN;
		}
	}
	
	public void addStatus( Status status ){
		statuses.add( status );
	}
	
	public void removeStatus( Status status ){
		statuses.remove( status );
	}
	
	public Vector<Status> getStatuses() {
		return statuses;
	}

	public Set [] duplicate( Set [] sets ){
		Set [] dups = new Set[ sets.length ];
		for (int x=0; x<sets.length; x++){
			dups[ x ] = duplicate( sets[ x ] );
		}
		return dups;
	}
	
	public Set duplicate( Set set ){
		return new Set( set.getUID(), set.getWeight(), set.getReps() );
	}
	
	public Workout [] duplicate( Workout [] workouts ){
		Workout [] dups = new Workout[ workouts.length ];
		for (int x=0; x<workouts.length; x++){
			dups[ x ] = duplicate( workouts[ x ] );
		}
		return dups;
	}
	
	public Workout duplicate( Workout work ){
		Workout dup = new Workout( work.getMillis(), work.getNotes() );
		for (Set set : work.getSets()){
			dup.add( duplicate( set ) );
		}
		return dup;
	}
	
	public void log( String message ){
//		System.out.println( "Journal: " + message  );
	}
	/**************************************************************************************/
	public void addLocaleListener( LocaleListener listener ){
		log( "addLocaleListener: " + listener );
		this.localeListeners.add( listener );
	}
	public void removeLocaleListener( LocaleListener listener ){
		this.localeListeners.remove( listener );
	}
	public void fireLocaleChanged( Locale locale ) {
		log( "fireLocaleChanged: " );
		for (LocaleListener l : localeListeners){
			l.localeChanged( locale );
		}
	}
	
	/**************************************************************************************/
	public void addWorkoutSelectionListener( WorkoutSelectionListener listener ){
		this.workoutSelectionListeners.add( listener );
	}
	public void removeWorkoutSelectionListener( WorkoutSelectionListener listener ){
		this.workoutSelectionListeners.remove( listener );
	}
	public void fireNoWorkoutSelected() {
		log( "fireNoWorkoutSelected: " );
		for (WorkoutSelectionListener l : workoutSelectionListeners){
			l.noWorkoutSelected();
		}
	}
	public void fireWorkoutSelected( Workout workout ){
		log( "fireWorkoutSelected: " );
		for (WorkoutSelectionListener l : workoutSelectionListeners){
			l.workoutSelected( workout );
		}
	}
	public void fireWorkoutsSelected( Workout [] workouts ){
		log( "fireWorkoutsSelected: " );
		for (WorkoutSelectionListener l : workoutSelectionListeners){
			l.workoutsSelected( workouts );
		}
	}
	
	/**************************************************************************************/
	public void addSetSelectionListener( SetSelectionListener listener ){
		this.setSelectionListeners.add( listener );
	}
	public void removeSetSelectionListener( SetSelectionListener listener ){
		this.setSelectionListeners.remove( listener );
	}
	public void fireNoSetSelected() {
		log( "fireNoSetSelected: " );
		for (SetSelectionListener l : setSelectionListeners){
			l.noSetsSelected();
		}
	}
	public void fireSetSelected( Set set ){
		log( "fireSetSelected: " );
		for (SetSelectionListener l : setSelectionListeners){
			l.setSelectedSet( set );
		}
	}
	public void fireSetsSelected( Set [] sets ){
		log( "fireSetsSelected: " );
		for (SetSelectionListener l : setSelectionListeners){
			l.setsSelectedSets( sets );
		}
	}
	/**************************************************************************************/
	public void addJournalListener( JournalListener listener ){
		this.journalListeners.add( listener );
	}
	public void removeJournalListener( JournalListener listener ){
		this.journalListeners.remove( listener );
	}
	public void fireJournalChanged(){
		for (JournalListener l : journalListeners){
			l.journalChanged( this );
		}
	}
	public void fireJournalOwnerChanged(){
		log( "fireJournalOwnerChanged" );
		for (JournalListener l : journalListeners){
			l.ownerChanged( this );
		}
	}
	public void fireJournalLoaded(){
		log( "fireJournalLoaded" );
		for (JournalListener l : journalListeners){
			l.journalLoaded( this );
		}
	}
	/* ******************************** Exercise ****************************************************/
	public void addExerciseListener( ExerciseListener listener ){
		log( "addExerciseListener: " + listener  );
		exerciseListeners.add( listener );
	}
	public void removeExerciseListener( ExerciseListener listener ){
		log( "removeExerciseListener: " + listener );
		exerciseListeners.remove( listener );
	}

	public void fireExerciseChangedMuscle( Exercise exercise, Muscle fromMuscle, Muscle toMusle ) {
		log( "fireExerciseChanged: " + exerciseListeners.size() + " listeners. " );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseChangedMuscle(exercise, fromMuscle, toMusle );
		}
	}
	
	public void fireExerciseChanged( Exercise exercise ){
		log( "fireExerciseChanged: " + exerciseListeners.size() + " listeners. " );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseChanged( exercise );
		}
	}
	public void fireExerciseAdded( Exercise exercise ){
		log( "fireExerciseAdded: " + exerciseListeners.size() + " listeners." );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseAdded( exercise );
		}
	}
	public void fireExerciseRemoved( Exercise exercise, Muscle muscle ){
		log( "fireExerciseRemoved: " + exerciseListeners.size() + " listeners." );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseRemoved( exercise, muscle );
		}
	}

	public void fireExerciseEnabled(Exercise exercise) {
		log( "fireExerciseEnabled: " + exerciseListeners.size() + " listeners." );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseEnabled(exercise);
		}
	}

	public void fireExerciseDisabled(Exercise exercise) {
		log( "fireExerciseDisabled: " + exerciseListeners.size() + " listeners." );
		for (ExerciseListener l : exerciseListeners){
			l.exerciseDisabled(exercise);
		}
	}

	/* ********************************** Workout **************************************************/
	public void addWorkoutListener( WorkoutListener listener ){
		log( "addWorkoutListener: " + listener );
		this.workoutListeners.add( listener );
	}
	public void removeWorkoutListener( WorkoutListener listener ){
		this.workoutListeners.remove( listener );
	}
	public void fireWorkoutChanged( Workout workout ){
		log( "fireWorkoutChanged" );
		for (WorkoutListener l : workoutListeners){
			l.workoutChanged( workout );
		}
	}
	public void fireWorkoutAdded( Workout workout ){
		log( "fireWorkoutAdded: " + workout );
		for (WorkoutListener l : workoutListeners){
			l.workoutAdded( workout );
		}
	}
	public void fireWorkoutRemoved( Workout workout ){
		log( "fireWorkoutRemoved:" );
		for (WorkoutListener l : workoutListeners){
			l.workoutRemoved( workout );
		}
	}
	public void fireWorkoutDateChanged( Workout workout ){
		log( "fireWorkoutDateChanged: " + workout.getClass().getName() );
		for (WorkoutListener l : workoutListeners){
			l.workoutDateChanged( workout );
		}
	}

	public void fireWorkoutOrderChanged() {
		log( "fireWorkoutOrderChanged: " );
		for (WorkoutListener l : workoutListeners){
			l.workoutOrderChanged( );
		}
	}

	public void fireWorkoutNotesChanged(Workout workout) {
		log( "fireWorkoutNotesChanged: " + workout.getClass().getName() );
		for (WorkoutListener l : workoutListeners){
			l.workoutNotesChanged( workout );
		}
	}

	public void fireWorkoutRemoved( Workout[] workouts ) {
		log( "fireWorkoutRemoved:" );
		for (WorkoutListener l : workoutListeners){
			l.workoutsRemoved( workouts );
		}
	}
	
	/*********************************** Set **************************************************/
	public void addSetListener( SetListener listener ){
		log( "addSetListener: " + listener );
		this.setListeners.add( listener );
	}
	public void removeSetListener( SetListener listener ){
		log( "removeSetListener: " + listener );
		this.setListeners.remove( listener );
	}
	public void fireSetChanged( Set set ){
		log( "fireSetChanged: " + set );
		for (SetListener l : setListeners){
			l.setChanged( set );
		}
	}
	public void fireSetAdded( Set set ){
		log( "fireSetAdded: " + set );
		for (SetListener l : setListeners){
			l.setAdded( set );
		}
	}
	public void fireSetRemoved( Set set ){
		log( "fireSetRemoved:" + set );
		for (SetListener l : setListeners){
			l.setRemoved( set );
		}
	}
	public void fireSetsRemoved( Set [] sets ){
		log( "fireSetsRemoved:" + sets );
		for (SetListener l : setListeners){
			l.setsRemoved( sets );
		}
	}
	/***************************************************************************************/
	public void sortWorkouts(){
		Collections.sort( workouts, new WorkoutComparator() );
	}

	public void sortExercises(){
		Collections.sort( excercises, new ExerciseComparator() );
	}
	
	public GregorianCalendar getStart(){
		GregorianCalendar date = new GregorianCalendar();
		for (Workout w : workouts){
			if (w.getCalendar().compareTo( date ) < 0){
				date = w.getCalendar();
			}
		}
		return date;
	}
	
	public GregorianCalendar getEnd(){
		GregorianCalendar date = new GregorianCalendar();
		for (Workout w : workouts){
			if (w.getCalendar().compareTo( date ) > 0){
				date = w.getCalendar();
			}
		}
		return date;
	}
	
	public long getStartDate(){
		return new GregorianCalendar(2007,0,1).getTimeInMillis();
	}

	public long getEndDate(){
		return new GregorianCalendar(2008,0,1).getTimeInMillis();
	}
	
	public int toPixels( long millis ){
		return (int) ((millis - getStartDate()) / DAY * 2);
	}
	
	/**
	 * Adds a new Workout
	 * 
	 * @param workout
	 */
	public void addWorkout( Workout workout ){
		workout.setJournal( this );
		workouts.add( workout );
		sortWorkouts();
		fireWorkoutAdded( workout );
	}
	
	/**
	 * Adds a new Exercise
	 * @param exercise
	 */
	public void addExercise( Exercise exercise ){
		if (hasExercise( exercise )){
			System.err.println( "Exercise already exist: " + exercise.getName() );
		} else {
			exercise.setJournal( this );
			excercises.add( exercise );
			fireExerciseAdded( exercise );
		}
	}
	
	/**
	 * Returns true if the specified Exercise already is in the journal 
	 * 
	 * @param exercise
	 * @return
	 */
	public boolean hasExercise( Exercise exercise ){
		return getExercise( exercise.getUID() ) != null;
	}
	
	public void addExercise( Exercise [] exercises ) {
		for (Exercise x : exercises){
			addExercise( x );
		}
	}
	
	public Vector<Exercise> getExercises() {
		return excercises;
	}
	
	public Vector<Workout> getWorkouts() {
		return workouts;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Exercise getExercise( long uid ) {
		for (Exercise ex : excercises){
			if (ex.getUID() == uid){
				return ex;
			}
		}
		return null;
	}
	
	public synchronized void removeExercises( Exercise... exercises ) {
		for (Exercise x : exercises){
			synchronized(x){
				removeExercise( x );
			}
		}
	}

	public synchronized void removeExercise( Exercise exercise ) {
		for (Workout w : workouts){
			Vector <Set> toBeRemoved = new Vector<Set>();
			for (Set s : w.getSets()){
				/* Gather up all sets to be removed first and the
				 * remove all of them to avoid ConcurrentModificationException */
				if (s.getExercise().sameAs( exercise )){
					toBeRemoved.add( s );
				}
			}
			w.getSets().removeAll( toBeRemoved );
		}
		synchronized(excercises){
			excercises.remove( exercise );
		}
	}

	public Workout getSelectedWorkout() {
		return null;
	}
	
	public Vector<Exercise> getExercises( Part part  ){
		return getExercises( part.listAllMuscles() );
	}
	
	public Vector<Exercise> getEnabledExercises( Muscle... muscles  ){
		Vector <Exercise> items = new Vector<Exercise>();
		for (Exercise exercise : getExercises()){
			for (Muscle m : muscles){
				if (exercise.getMuscle().sameAs(m) && exercise.isEnabled()){
					items.add( exercise );
				}
			}
		}
		return items;
	}
	
	public Vector<Exercise> getExercises( Muscle... muscles  ){
		Vector <Exercise> items = new Vector<Exercise>();
		for (Exercise exercise : getExercises()){
			for (Muscle m : muscles){
				if (exercise.getMuscle().sameAs(m)){
					items.add( exercise );
				}
			}
		}
		return items;
	}

	public String toString() {
		return "Journal";
	}

	/**
	 * Returns all workouts which includes the specified exercise 
	 * 
	 * @param exercise
	 * @return
	 */
	public Vector<Workout> getWorkouts( Exercise exercise ){
		Vector <Workout> items = new Vector<Workout>();
		for (Workout workout : getWorkouts()){
			for (Set set : workout.getSets()){
				if (set.getExercise().sameAs(exercise)){
					items.add( workout );
				}
			}
		}
		return items;
	}

	public void setMale( boolean male ) {
		this.sex = male ? MALE : FEMALE;
	}
	
	public void setFemale( boolean female ) {
		this.sex = female ? FEMALE : MALE;
	}

	public void sortStatuses() {
		Collections.sort( statuses, new StatusComparator() );
	}
	
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	
	public Date getLastChanged() {
		return lastChanged;
	}
	
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	public Date getLastUsed() {
		return lastUsed;
	}

}