package org.laukvik.trainer.shop;

import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.swing.JournalHelper;

public class ShopTableModel implements TableModel{
	
	private Vector<TableModelListener> listeners = new Vector<TableModelListener>();
	private Journal journal;
	private Vector<Exercise> selected;
	
	public ShopTableModel( Journal journal ){
		setJournal( journal );
	}
	
	public Journal getJournal() {
		return journal;
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.add( l );
	}
	
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove( l );
	}
	
	public void tableChanged( TableModelEvent evt ){
		for (TableModelListener l : listeners){
			l.tableChanged( evt );
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
			case 0 : return Boolean.class;
			case 1 : return Icon.class;
			case 2 : return Exercise.class;
			case 3 : return GregorianCalendar.class;
			case 4 : return String.class;
			default : return null;
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName( int columnIndex ) {
		switch(columnIndex){
			case 0 : return ""; // checkbox
			case 1 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.icon");
			case 2 : return JournalHelper.getLanguage( journal.getLocale(), "exercise");
			case 3 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.created");
			case 4 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.author");
			default : return null;
		}
	}

	public int getRowCount() {
		return journal.getExercises().size();
	}
	
	public Exercise getRow( int rowIndex ){
		return journal.getExercises().get( rowIndex );
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : return selected.contains( getRow( rowIndex ) );
			case 1 : return getRow( rowIndex ).getIcon();
			case 2 : return getRow( rowIndex );
			case 3 : 
				GregorianCalendar cal = new GregorianCalendar( journal.getLocale() );
				cal.setTimeInMillis( getRow( rowIndex ).getUID() );
				return cal;
			case 4 : 
				String author = getRow( rowIndex ).getAuthor();
				if (author == null){
					return JournalHelper.getLanguage( journal.getLocale(), "exercise.author.unknown");
				} else {
					return author;
				}
			default : return null;
		}
		
	}

	public boolean isCellEditable( int rowIndex, int columnIndex ) {
		if (columnIndex == 0){
//			if (journal.hasExercise( getRow( rowIndex ) )){
//				return false;
//			}
			return true;
		} else {
			return false;
		}
	}

	public void setValueAt( Object value, int rowIndex, int columnIndex ) {
		if ((Boolean) value){
			selected.add( getRow( rowIndex ) );
		} else {
			selected.remove( getRow( rowIndex ) );
		}
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
		selected = new Vector<Exercise>();
	}

	public Exercise[] getSelected() {
		Exercise [] exercises = new Exercise[ selected.size() ];
		selected.toArray( exercises );
		return exercises;
	}

}