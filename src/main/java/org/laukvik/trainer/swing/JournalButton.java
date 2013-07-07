package org.laukvik.trainer.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.actions.AbstractJournalAction;

public class JournalButton extends JButton implements LocaleListener{

	private static final long serialVersionUID = 1L;

	public JournalButton( AbstractJournalAction action ) {
		super( action );
		setHorizontalTextPosition( SwingConstants.CENTER );
		setHorizontalAlignment( SwingConstants.CENTER );
		setVerticalAlignment( SwingConstants.BOTTOM );
		setVerticalTextPosition( SwingConstants.BOTTOM  );
		setMinimumSize( new Dimension(100,64) );
		setFont( new Font( getFont().getName(), Font.PLAIN, 10 ) );
		setBorderPainted( false );
	}

	public void localeChanged (Locale locale ) {
		setText( JournalHelper.getLanguage(locale, getActionCommand() ) );
		setToolTipText( JournalHelper.getLanguage(locale, getActionCommand() + ".tooltip" ) );
	}
	
}