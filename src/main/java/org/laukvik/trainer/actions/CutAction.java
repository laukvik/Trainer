package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.KeyStroke;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class CutAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "cut";

	public CutAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( journalPanel.getLocale(),  "cut" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( journalPanel.getLocale(), "cut.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, getKeystroke() );
	}

	public void actionPerformed( ActionEvent e) {
		getJournalPanel().cut();
	}

	public static KeyStroke getKeystroke() {
		return JournalHelper.getKeystroke( KeyEvent.VK_X );
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		putValue( Action.NAME, JournalHelper.getLanguage( getJournalPanel().getLocale(), "cut" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getJournalPanel().getLocale(), "cut.tooltip" ) );
	}
	
}