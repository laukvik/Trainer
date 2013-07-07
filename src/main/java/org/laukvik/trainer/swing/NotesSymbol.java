package org.laukvik.trainer.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.UIManager;

import org.laukvik.trainer.workout.Workout;

public class NotesSymbol extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;
	public final static Color notesColor = new Color( 255, 0, 0 );
	public final static Font notesFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 8 );
//	JournalPanel panel;
	Workout work;

	public NotesSymbol( Workout work, int x, int y ){
		super();
//		this.panel = panel;
		this.work = work;
		setHorizontalTextPosition( NotesSymbol.CENTER );
		setHorizontalAlignment( NotesSymbol.CENTER );
		if (work.getNotes() == null || work.getNotes().length() == 0){
			setText(" ");
			setBackground( Color.WHITE );
			setForeground( Color.WHITE );
			setFont( notesFont );
			setOpaque( true );
			setBounds( x, y-2, 6, 6 );
		} else {
			setText("!");
			setToolTipText( work.getNotes() );
			setBackground( notesColor );
			setForeground( Color.WHITE );
			setFont( notesFont );
			setOpaque( true );
			setBounds( x, y, 12, 12 );
		}
		setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		addMouseListener( this );
	}

	public void mouseClicked(MouseEvent e) {
//		panel.selectWorkout( work ); 
	} 

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
	
}