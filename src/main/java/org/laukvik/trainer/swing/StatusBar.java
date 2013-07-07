package org.laukvik.trainer.swing;

import java.awt.Font;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.laukvik.swing.locale.LocaleListener;

import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetSelectionListener;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

public class StatusBar extends JLabel implements WorkoutSelectionListener, SetSelectionListener, LocaleListener{

	private static final long serialVersionUID = 1L;
	int size = 3;

	public StatusBar(){
		setLocale( JournalHelper.getDefaultLocale() );
		setBorder( BorderFactory.createEmptyBorder( size, size, size, size ) );
		setFont( new Font( getFont().getName(), Font.PLAIN, 10   ) );
		setHorizontalAlignment( SwingConstants.CENTER );
	}
	
	public void setStatus( String status ){
		setText( status );
	}

	public void noWorkoutSelected() {
		setStatus( JournalHelper.getLanguage( getLocale(), "workouts.noselected") );
	}

	public void workoutSelected(Workout workout) {
		Object [] params = { 1 };
		setStatus( JournalHelper.getLanguage( getLocale(), "workouts.selected", params ) );
	}

	public void workoutsSelected(Workout[] workouts) {
		String [] params = { workouts.length +"" };
		setStatus( JournalHelper.getLanguage( getLocale(), "workouts.selected", (Object[]) params ) );
	}

	public void noSetsSelected() {
		setStatus( JournalHelper.getLanguage( getLocale(), "sets.noselected") );
	}

	public void setSelectedSet( Set set) {
		Object [] params = { 1 };
		setStatus( JournalHelper.getLanguage( getLocale(), "sets.selected", params) );
	}

	public void setsSelectedSets( Set[] sets) {
		Object [] params = { sets.length };
		setStatus( JournalHelper.getLanguage( getLocale(), "sets.selected",params) );
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
	}
	
}