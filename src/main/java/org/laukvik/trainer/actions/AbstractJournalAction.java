package org.laukvik.trainer.actions;

import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public abstract class AbstractJournalAction extends AbstractAction {

	JournalPanel listener;
	Locale locale;

	public AbstractJournalAction( JournalPanel listener ){
		this.listener = listener;
		locale = JournalHelper.getDefaultLocale();
	}
	
	public JournalPanel getJournalPanel() {
		return listener;
	}
	
	public String getActionCommandKey(){
		return (String) getValue( Action.ACTION_COMMAND_KEY  );
	}
	
	public Locale getLocale(){
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}