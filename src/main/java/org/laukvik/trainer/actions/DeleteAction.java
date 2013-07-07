package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class DeleteAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "delete";

	public DeleteAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( journalPanel.getLocale(), "delete" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( journalPanel.getLocale(), "delete.tooltip" ) );
		putValue( Action.ACCELERATOR_KEY, getKeystroke() );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().delete();
	}

	public static KeyStroke getKeystroke() {
		return JournalHelper.getKeystroke( KeyEvent.VK_BACK_SPACE );
	}

}