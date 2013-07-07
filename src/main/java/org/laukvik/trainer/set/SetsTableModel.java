package org.laukvik.trainer.set;

import java.util.Locale;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;

public class SetsTableModel implements TableModel, LocaleListener {
	
	private Workout workout;
	private Vector <TableModelListener> listeners = new Vector<TableModelListener>();
	private boolean editingAllowed = false;
	private int selectedRow = -1;
	private Locale locale;
	
	public SetsTableModel( Workout workout ){
		this.workout = workout;
		this.locale = workout.getJournal().getLocale();
	}
	
	public SetsTableModel(){
		this.workout = null;
		this.locale = Locale.US;
	}
	
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	public void setEditingAllowed(boolean editingAllowed) {
		this.editingAllowed = editingAllowed;
	}
	
	public boolean isEditingAllowed() {
		return editingAllowed;
	}
	
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	
	public Workout getWorkout() {
		return workout;
	}
	
	public void fireRowChanged( int rowIndex ){
		for (TableModelListener l : listeners){
			l.tableChanged( new TableModelEvent( this, rowIndex ) );
		}
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.add( l );
	}
	
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove( l );
	}

	public Class<?> getColumnClass( int columnIndex ) {
		switch( columnIndex ){
			case 0 : return Exercise.class;
			case 1 : return Double.class;
			case 2 : return Integer.class;
			case 3 : return String.class;
			default : return null;
		}
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex){
			case 0 : return JournalHelper.getLanguage( locale,"exercise");
			case 1 : return JournalHelper.getLanguage( locale, "set.weight");
			case 2 : return JournalHelper.getLanguage( locale, "set.reps");
			case 3 : return JournalHelper.getLanguage( locale, "set.comments");
			default : return null;
		}
	}
	
	public int getRowCount() {
		if (workout == null){
			return 0;
		}
		return workout.getSets().size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Set set = getRow( rowIndex );
		switch(columnIndex){
			case 0 : return set.getExercise();
			case 1 : return set.getWeight();
			case 2 : return set.getReps();
			case 3 : return set.getComments();
			default : return null;
		}
	}
	
	public Set getRow( int rowIndex ){
		return workout.getSets().get( rowIndex );
	}

	public boolean isCellEditable( int rowIndex, int columnIndex ) {
		if (columnIndex > 0){
			return true;
		} 
		return selectedRow == rowIndex;
	}

	public void setValueAt( Object value, int rowIndex, int columnIndex ) {
		Set set = getRow( rowIndex );
		if (columnIndex == 0){
			if (value != null){
				set.setExercise( (Exercise) value );
				fireRowChanged( rowIndex );
			}
		} else if (columnIndex == 1){
			set.setWeight( (Double) value );
			fireRowChanged( rowIndex );
		} else if (columnIndex == 2){
			set.setReps( (Integer) value );
			fireRowChanged( rowIndex );
		} else if (columnIndex == 3){
			set.setComments( (String) value );
			fireRowChanged( rowIndex );
		}
		this.workout.getJournal().fireSetChanged( set );
	}

	public int indexOf(Set set) {
		return workout.getSets().indexOf( set );
	}

	public void localeChanged(Locale locale) {
		this.locale = locale;
		
	}

}