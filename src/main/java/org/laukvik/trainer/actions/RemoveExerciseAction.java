package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.laukvik.trainer.exercise.ExerciseEditorDialog;
import org.laukvik.trainer.swing.JournalHelper;

public class RemoveExerciseAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "removeExercise";
	private ExerciseEditorDialog exerciseEditorPanel;

	public RemoveExerciseAction( ExerciseEditorDialog exerciseEditorPanel ) {
		this.exerciseEditorPanel = exerciseEditorPanel;
		putValue( Action.NAME, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.delete" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "minus.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.delete.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, JournalHelper.getKeystroke( KeyEvent.VK_DELETE ) );
	}

	public void actionPerformed(ActionEvent e) {
		this.exerciseEditorPanel.remove();
	}

}