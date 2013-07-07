package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.laukvik.trainer.ImageBadgeIcon;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;

public class ShopAction extends AbstractJournalAction{

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "shop";


	public ShopAction( JournalPanel journalPanel ) {
		super( journalPanel );
		putValue( Action.NAME, JournalHelper.getLanguage( getLocale(), COMMAND_KEY ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		ImageBadgeIcon icon = JournalHelper.getIcon( "shop.png" );
		putValue( Action.SMALL_ICON, icon );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( getLocale(), "shop.tooltip" ) );
	}

	public void actionPerformed(ActionEvent e) {
		getJournalPanel().openShop();
	}
	
	public void setBadge( String text ){
		ImageBadgeIcon icon = JournalHelper.getIcon( "shop.png" );
		icon.setText( text );
		putValue( Action.SMALL_ICON, icon );
	}

}