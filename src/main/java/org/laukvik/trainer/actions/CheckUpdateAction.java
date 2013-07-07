package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class CheckUpdateAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "update.check";

	public CheckUpdateAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( journalPanel.getLocale(), "update.check" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( journalPanel.getLocale(), "update.check.tooltip" ) );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().checkForUpdate();
	}

}