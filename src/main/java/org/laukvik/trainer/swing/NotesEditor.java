package org.laukvik.trainer.swing;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

public class NotesEditor extends JTextArea implements CaretListener, WorkoutSelectionListener{

	private static final long serialVersionUID = 1L;
	private Workout [] selectedWorkouts = null;

	public NotesEditor(){
		super();
        setLineWrap( true );
        setWrapStyleWord( true );
		setFont( new Font( getFont().getName(), Font.PLAIN, 11 )  );
		setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );
		addCaretListener( this );
	}

	public void setText(String t){
		removeCaretListener(this);
		super.setText(t);
		addCaretListener( this );
	}

	public void caretUpdate( CaretEvent e ) {
		if (selectedWorkouts == null || selectedWorkouts.length == 0){
			
		} else {
			selectedWorkouts[ 0 ].setNotes( getText() );
			selectedWorkouts[ 0 ].getJournal().fireWorkoutNotesChanged( selectedWorkouts[ 0 ] );
		}
	}

	public void noWorkoutSelected() {
		setText("");
		setEnabled( false ); 
		this.selectedWorkouts = null;
	}

	public void workoutSelected(Workout workout) {
		setText( workout.getNotes() );
		setEnabled( true );
		this.selectedWorkouts =  new Workout [] { workout };
	}

	public void workoutsSelected( Workout[] workouts ) {
		setText("");
		setEnabled( false );
		this.selectedWorkouts = workouts;
	}
	
}