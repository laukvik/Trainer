package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JButton;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class DetailsAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "details";

	public DetailsAction( JournalPanel journalPanel ) {
		super(journalPanel);
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "info.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), COMMAND_KEY + ".tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, JournalHelper.getKeystroke( KeyEvent.VK_I ) );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().setDetailsVisible( !getJournalPanel().isDetailsVisible() );		
		JButton button = (JButton) e.getSource();
		button.setSelected( getJournalPanel().isDetailsVisible() );
	}

}