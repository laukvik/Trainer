package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.KeyStroke;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class CopyAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "copy";

	public CopyAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( journalPanel.getLocale(), "copy" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( journalPanel.getLocale(), "copy.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, getKeystroke() );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().copy(); 
	}
	
	public static KeyStroke getKeystroke(){
		return JournalHelper.getKeystroke( KeyEvent.VK_C );
	}
	
	public void localeChanged(Locale locale) {
		setLocale( locale );
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), "copy" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), "copy.tooltip" ) );
		
	}

}