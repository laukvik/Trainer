package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.Action;

import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class HelpAction extends AbstractJournalAction implements LocaleListener{

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "help";

	public HelpAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		setLocale( journalPanel.getLocale() );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "help.png" ) );
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), COMMAND_KEY + ".tooltip" ) );
		
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().help();
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), COMMAND_KEY + ".tooltip" ) );
	}

}