package org.laukvik.trainer.shop;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalManager;

public class ShopPanel extends JPanel implements HyperlinkListener {

	private static final long serialVersionUID = 1L;
	private JEditorPane editor;
	
	public ShopPanel( Journal journal ) {
		super();
		setLayout( new BorderLayout() );
		editor = new JEditorPane();
		editor.setEditable( false );
		setMinimumSize( new Dimension( 0, 150 ) );
		setMaximumSize( new Dimension( 32000, 150 ) );
		setPreferredSize( new Dimension( 500, 150 ) );
		try {
			editor.setPage( JournalManager.getShopURL(journal) );
			editor.addHyperlinkListener( this );
		} catch (IOException e) {
			editor.setText( e.getMessage() );
//			e.printStackTrace();
		}
		add( editor );
	}


	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ENTERED){ // onMouseOver
//			System.out.println( "ENTERED" );
			setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		} else if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){ // onClick
//			System.out.println( "ACTIVATED" );
			try {
				editor.setPage( e.getURL() );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getEventType() == HyperlinkEvent.EventType.EXITED){ // onMouseOut
//			System.out.println( "EXITED" );
			setCursor( Cursor.getDefaultCursor() );
		}
	}
	
}