package org.laukvik.trainer.exercise;

import java.util.Vector;

import javax.swing.event.ListDataListener;

import org.laukvik.trainer.journal.Journal;

public class ExerciseComboBoxModel implements javax.swing.ComboBoxModel{
	
	private Journal journal;
	private Vector <ListDataListener> listeners = new Vector<ListDataListener>();
	private Object selectedItem = null;
	Vector<Exercise> exercises;
	
	public ExerciseComboBoxModel( Journal journal ){
		this.journal = journal;
		
//		Vector<Exercise> ons = new Vector<Exercise>();
//		Vector<Exercise> offs = new Vector<Exercise>();
//		
//		for (Exercise e : journal.getExercises()){
//			if (e.isEnabled()){
//				ons.add( e );
//			} else {
//				offs.add( e );
//			}
//		}
//		
//		for (Exercise e : ons){
//			exercises.add( e );
//		}
//		
//		for (Exercise e : offs){
//			exercises.add( e );
//		}
		journal.sortExercises();
	}
	
	public ExerciseComboBoxModel(){
		journal = new Journal();
	}

	public void addListDataListener(ListDataListener l) {
		listeners.add( l );
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove( l );
	}
	
	public Object getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object anItem) {
		this.selectedItem = anItem;
	}
	
	public Object getElementAt(int index) {
		return journal.getExercises().get( index );
	}

	public int getSize() {
		return journal.getExercises().size();
	}

}