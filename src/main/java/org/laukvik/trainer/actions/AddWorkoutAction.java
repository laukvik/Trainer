package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.Action;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;

public class AddWorkoutAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "workout.add";

	public AddWorkoutAction( JournalPanel journalPanel ) {
		super(journalPanel);
		setLocale( journalPanel.getLocale() );
		
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "journal.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), "workout.add.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, JournalHelper.getKeystroke( KeyEvent.VK_N ) );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().addWorkout( new Workout( new Date(), "" ) );
	}

}