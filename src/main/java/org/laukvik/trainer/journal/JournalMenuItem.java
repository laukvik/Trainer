package org.laukvik.trainer.journal;

import java.util.Locale;

import javax.swing.Action;
import javax.swing.JMenuItem;

import org.laukvik.trainer.actions.AbstractJournalAction;
import org.laukvik.trainer.swing.JournalHelper;

public class JournalMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public JournalMenuItem( AbstractJournalAction action ){
		super( action );
	}
	
//	public void localeChanged(Locale locale) {
//		setLocale( locale );
//		putValue( Action.NAME, JournalHelper.getLanguage( locale, "paste" ) );
//		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( locale, "paste.tooltip" ) );
//	}
	
	public void setLocale(Locale l) {
		super.setLocale(l);
		
		String keyText = getAction().getValue( Action.ACTION_COMMAND_KEY ).toString();
		setText( JournalHelper.getLanguage( l, keyText ) );	
		setToolTipText( JournalHelper.getLanguage( l, keyText + ".tooltip" ) );
		
	}
	
}