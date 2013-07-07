package org.laukvik.trainer.help;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent.EventType;
import org.laukvik.swing.locale.LocaleListener;

import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.swing.JournalHelper;

public class HelpViewer extends JDialog implements HyperlinkListener, ActionListener, LocaleListener{

	private static final long serialVersionUID = 1L;
	private JButton back;
	private JEditorPane editorPane;
	
	public HelpViewer( Component component, Locale locale ){
		setDefaultCloseOperation( HelpViewer.DISPOSE_ON_CLOSE );
		setTitle( "Help" );
		setLayout( new BorderLayout() );

		JPanel bottom = new JPanel();
		back = (JButton) bottom.add( new JButton( "Close" ) );
		back.addActionListener( this );
		
		editorPane = new JEditorPane();
		editorPane.setEditable( false );
		editorPane.addHyperlinkListener( this );
		
		JScrollPane scroll = new JScrollPane( editorPane );
		add( scroll, BorderLayout.CENTER );
//		add( bottom, BorderLayout.NORTH );
		
		setSize( new Dimension(600,400) );
		
		setAlwaysOnTop( true );
		setLocationRelativeTo( component );
		setVisible( true );
//		setModal( true );
		setURL( JournalManager.getHelpURL() );
		localeChanged( locale );
	}

	public void hyperlinkUpdate(HyperlinkEvent evt) {
		if (evt.getEventType() == EventType.ACTIVATED){
			setURL( evt.getURL() );
		}
	}
	
	public void setURL( URL url ){
		try {
			editorPane.setPage( url );
		} catch (IOException e) {
			editorPane.setText( e.getMessage() );
		}
	}

	public void actionPerformed(ActionEvent e) {
		dispose();		
	}

	public void localeChanged(Locale locale){
		setTitle( JournalHelper.getLanguage( locale, "help" ) );
	}
	
}