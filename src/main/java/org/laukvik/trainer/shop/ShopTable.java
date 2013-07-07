package org.laukvik.trainer.shop;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.JTable;

import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.journal.Journal;

public class ShopTable extends JTable{

	private static final long serialVersionUID = 1L;
	private ShopTableModel model;

	public ShopTable(){
		super( new ShopTableModel( new Journal() ) );
		setRowHeight( 100 );
		setGridColor(new Color(210, 210, 210));
//		setDefaultRenderer( Object.class , new OddEvenTableRenderer() );
		setDefaultRenderer( Icon.class , new IconTableCellRenderer() );
		setDefaultRenderer( GregorianCalendar.class , new DateTableCellRenderer( ) );
		setDefaultRenderer( String.class , new AuthorTableCellRenderer() );
		setDefaultRenderer( Exercise.class, new ExerciseTableCellRenderer() );	
		setDefaultRenderer( Boolean.class, new BooleanTableCellRenderer() );	
	}

	public void setJournal(Journal journal){
		setLocale( journal.getLocale() );
		setDefaultRenderer( GregorianCalendar.class , new DateTableCellRenderer( journal.getLocale() ) );
		model = new ShopTableModel( journal );
		setModel( model );
		getColumnModel().getColumn( 0 ).setMinWidth( 32 );
		getColumnModel().getColumn( 0 ).setMaxWidth( 32 );
		getColumnModel().getColumn( 1 ).setMinWidth( 100 );
		getColumnModel().getColumn( 1 ).setMaxWidth( 100 );
		
		getColumnModel().getColumn( 2 ).setPreferredWidth( 300 );
		
//		getColumnModel().getColumn( 3 ).setMinWidth( 100 );
//		getColumnModel().getColumn( 3 ).setMaxWidth( 100 );
		getColumnModel().getColumn( 3 ).setPreferredWidth( 50 );
		getColumnModel().getColumn( 4 ).setPreferredWidth( 50 );
	}
	
	public Journal getJournal(){
		return model.getJournal();
	}
	
	public Exercise [] getSelectedExercises(){
		return model.getSelected();
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