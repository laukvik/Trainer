package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.laukvik.trainer.exercise.ExerciseEditorDialog;
import org.laukvik.trainer.swing.JournalHelper;

public class AddExerciseAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "addExercise";
	private ExerciseEditorDialog exerciseEditorPanel;

	public AddExerciseAction( ExerciseEditorDialog exerciseEditorPanel ) {
		this.exerciseEditorPanel = exerciseEditorPanel;
		putValue( Action.NAME, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.add" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "plus.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.add.tooltip" ) );
	}

	public void actionPerformed(ActionEvent e) {
		this.exerciseEditorPanel.add();
	}

}