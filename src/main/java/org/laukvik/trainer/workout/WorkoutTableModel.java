package org.laukvik.trainer.workout;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.swing.JournalHelper;

public class WorkoutTableModel implements TableModel{
	
	private Journal journal;
	
	public WorkoutTableModel( Journal journal ) {
		this.journal = journal;
	}

	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
			case 0 : return Workout.class;
			default : return null;
		}
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		switch(columnIndex){
			case 0 : return JournalHelper.getLanguage( journal.getLocale(), "workouts");
			default : return null;
		}
	}

	public int getRowCount() {
		return journal.getWorkouts().size();
	}
	
	public Workout getRow( int rowIndex ){
		return journal.getWorkouts().get( rowIndex );
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Workout workout = getRow( rowIndex );
		switch(columnIndex){
			case 0 : return workout;
			default : return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
	}

	public void setAdded(Set set) {
	}

	public void setChanged(Set set) {
	}

	public void setRemoved(Set set) {
	}

	public void workoutAdded(Workout workout) {
	}

	public void workoutChanged(Workout workout) {
	}

	public void workoutDateChanged(Workout workout) {
	}

	public void workoutNotesChanged(Workout workout) {
	}

	public void workoutRemoved(Workout workout) {
	}

	public void journalChanged(Journal journal) {
	}

	public void journalLoaded(Journal journal) {
		setJournal( journal );
	}

	public void ownerChanged(Journal journal) {
	}

	public int getRowIndex(Set set) {
		return journal.getWorkouts().indexOf( set.getWorkout() );
	}

	public int indexOf(Workout workout) {
		return journal.getWorkouts().indexOf( workout );
	}

	public Workout[] getRows(int[] rows) {
		Workout [] works = new Workout[ rows.length ];
		for (int x=0; x<rows.length; x++){
			int rowIndex = rows[ x ];
			works[ x ] = journal.getWorkouts().get( rowIndex );
		}
		return works;
	}

}