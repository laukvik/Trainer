package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class PreferencesAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "preferences";

	public PreferencesAction( JournalPanel journalPanel ) {
		super(journalPanel);
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "preferences.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( journalPanel.getLocale(), "preferences.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, JournalHelper.getKeystroke( KeyEvent.VK_COMMA ) );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().preferences();
	}

}