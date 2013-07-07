package org.laukvik.trainer.exercise;

import java.util.Vector;

import javax.swing.Icon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.swing.JournalHelper;

public class ExerciseTableModel implements TableModel{
	
	private Vector<TableModelListener> listeners = new Vector<TableModelListener>();
	private Journal journal;
	
	public ExerciseTableModel( Journal journal ){
		this.journal = journal;
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
			case 0 : return String.class;
			case 1 : return String.class;
			case 2 : return String.class;
			case 3 : return Icon.class;
			case 4 : return Muscle.class;
			default : return null;
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName( int columnIndex ) {
		switch(columnIndex){
			case 0 : return JournalHelper.getLanguage( journal.getLocale(), "exercise");
			case 1 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.notes");
			case 2 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.filename");
			case 3 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.icon");
			case 4 : return JournalHelper.getLanguage( journal.getLocale(), "exercise.muscle");
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
			case 0 : return getRow( rowIndex ).getName();
			case 1 : return getRow( rowIndex ).getNotes();
			case 2 : return "";
			case 3 : return getRow( rowIndex ).getIcon();
			case 4 : return getRow( rowIndex ).getMuscle();
			default : return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : getRow( rowIndex ).setName( (String) value ); break;
			case 1 : getRow( rowIndex ).setNotes( (String) value ); break;
			case 2 :  break;
			case 4 : getRow( rowIndex ).setMuscle( (Muscle) value ); break;
		}
		tableChanged( new TableModelEvent(this, rowIndex) );
	}

}