package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.journal.JournalToolbar;
import org.laukvik.trainer.swing.JournalHelper;

public class ShowBodyPartAction extends AbstractJournalAction {

	private static final long serialVersionUID = 1L;
	public String COMMAND_KEY = "part";
	private Part part;
	private JournalToolbar journalToolbar;

	public ShowBodyPartAction( Part part, JournalPanel journalPanel, JournalToolbar journalToolbar ) {
		super(journalPanel);
		COMMAND_KEY = part.getName().toLowerCase();
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "chart.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), COMMAND_KEY + ".tooltip" ) );
		this.part = part;
		this.journalToolbar = journalToolbar;
	}
	
	public Part getPart() {
		return part;
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().setSelectedPart( part );
		journalToolbar.bodyPartSelected(part);
	}

}