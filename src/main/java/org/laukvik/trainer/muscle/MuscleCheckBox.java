package org.laukvik.trainer.muscle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;

/**
 * A checkbox that is selected if the specified Exercise has the given muscle 
 * in the constructor
 * 
 * @author morten
 *
 */
public class MuscleCheckBox extends JCheckBox implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Muscle muscle;
	private Exercise exercise;
	
	public MuscleCheckBox( Muscle muscle ){
		super( muscle.getLatin() );
		this.muscle = muscle;
		addActionListener( this );
	}

	public Muscle getMuscle() {
		return muscle;
	}
	
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
		if (this.exercise == null){
			setSelected( false );
		} else {
			setSelected( this.exercise.hasMuscle( muscle ) );
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (exercise == null){
			
		} else {
			if (isSelected() && exercise.hasMuscle( muscle )){
				exercise.removeMuscle( muscle );
			} else {
				exercise.addMuscle( muscle );
			}
		}
	}

}