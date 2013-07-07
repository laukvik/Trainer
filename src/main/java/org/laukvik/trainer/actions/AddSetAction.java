package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;

public class AddSetAction extends AbstractJournalAction{

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "set.add";
	private Workout workout;

	public AddSetAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "table.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), "set.add.tooltip" ) );
	}

	public void actionPerformed(ActionEvent e) {
		if (workout != null){
			Set set = new Set( 0, 0 );
			workout.add( set );
			workout.getJournal().fireSetAdded( set );
		}
	}

	public void setWorkout( Workout workout ) {
		this.workout = workout;
	}

}