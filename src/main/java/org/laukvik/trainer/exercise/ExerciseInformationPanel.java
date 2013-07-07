package org.laukvik.trainer.exercise;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ExerciseInformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Exercise exercise;
	private JEditorPane textarea;

	public ExerciseInformationPanel() {
		setLayout( new BorderLayout() );
		textarea = new JEditorPane();
		textarea.setEditable( false );
		add( new JScrollPane(textarea) );
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
//		System.out.println( "NOW" );
		this.exercise = exercise;
		textarea.setText( exercise.getNotes() );
	}
	
}