package org.laukvik.trainer.swing;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JProgressBar;

import org.laukvik.trainer.journal.JournalReadWriteListener;

public class JournalProgressDialog extends Dialog implements JournalReadWriteListener {

	private static final long serialVersionUID = 1L;
	private JProgressBar bar;
	private Dimension size = new Dimension( 200, 25 );

	public JournalProgressDialog( Frame owner, String title ){
		super( owner, title, true );
		setLayout( new FlowLayout() );
		setSize( 220, 40 );
		setResizable( false );
		setUndecorated( true );
		
		bar = new JProgressBar( 0, 100 );
		bar.setMinimumSize( size );
		bar.setPreferredSize( size );
		bar.setMaximumSize( size );
		bar.setIndeterminate( false );
		bar.setStringPainted( true );
		bar.setString( title );
		add( bar );
		setLocationRelativeTo( null );
	}
	
	public void setMinMax( int min, int max){
		bar.setMinimum( min );
		bar.setMaximum( max );
	}
	
	public void showDialog(){
		setVisible( true );
	}
	
	public void setText( String message ){
		bar.setString( message );
	}
	
	public void closeDialog(){
		setVisible( false );
		dispose();
	}

	public void statusChanged(String message) {
		bar.setString( message );
	}

	public void progressChanged( int percent ) {
		bar.setValue( percent );
	}
	
}