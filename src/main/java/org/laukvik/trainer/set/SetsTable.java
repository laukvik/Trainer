package org.laukvik.trainer.set;

import java.awt.Color;
import java.util.Locale;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.swing.table.RowColorTableCellRenderer;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.exercise.ExerciseCellEditor;
import org.laukvik.trainer.exercise.ExerciseComboBox;
import org.laukvik.trainer.exercise.ExerciseListener;
import org.laukvik.trainer.exercise.ExercisePanel;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalListener;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

public class SetsTable extends JTable implements SetListener, WorkoutSelectionListener, ExerciseListener, JournalListener, LocaleListener {
	
	private static final long serialVersionUID = 1L;
	private SetsTableModel model;
	private ExerciseComboBox exerciseComboBox;
	public static Color odd = new Color(232,242,254);
	public static Color even = new Color(255,255,255);

	public SetsTable() {
		super( new SetsTableModel() );
		setRowHeight( ExercisePanel.REQUIRED_HEIGHT );
		getColumnModel().getColumn( 1 ).setMaxWidth( 100 );
		getColumnModel().getColumn( 2 ).setMaxWidth( 100 );
		
		setGridColor(new Color(210, 210, 210));
		setDefaultRenderer( Integer.class , new RowColorTableCellRenderer() );
		setDefaultRenderer( Double.class , new RowColorTableCellRenderer() );
		setDefaultRenderer( Icon.class , new RowColorTableCellRenderer() );
		setDefaultRenderer( String.class , new TextWrapTabellCellRenderer() );
		this.model = (SetsTableModel) getModel();
		exerciseComboBox = new ExerciseComboBox();
		getColumnModel().getColumn(0).setCellEditor( new ExerciseCellEditor(exerciseComboBox) );
		getColumnModel().getColumn(0).setCellRenderer(  new SetsTableCellRenderer() );
		
//		getColumnModel().getColumn(2).setCellEditor( new SetSpinnerCellEditor() ); 
//		getColumnModel().getColumn(2).setCellRenderer( new SetSpinnerCellEditor() ); 
	}

	public void valueChanged( ListSelectionEvent e ) {
		super.valueChanged(e);
		if (model.getWorkout() == null){
			
		} else if (getSelectedRowCount() == 0){
			model.getWorkout().getJournal().fireNoSetSelected();
		} else if (getSelectedRowCount() == 1){
			Set set = model.getRow( getSelectedRow() );
			model.getWorkout().getJournal().fireSetSelected( set );
		} else {
			Set [] sets = new Set[ getSelectedRowCount() ];
			for (int x=0; x<sets.length; x++){
				sets[ x ] = model.getRow( x );
			}
			model.getWorkout().getJournal().fireSetsSelected( sets );
		}
		model.setSelectedRow( getSelectedRow() );
	}

	public void setAdded(Set set) {
		int rowIndex = model.indexOf( set );
		tableChanged( new TableModelEvent(model) );
		clearSelection();
		addRowSelectionInterval( rowIndex, rowIndex );
	}

	public void setChanged(Set set) {
		int rowIndex = model.indexOf( set );
		tableChanged( new TableModelEvent(model, rowIndex) );
	}

	public void setRemoved(Set set) {
		int rowIndex = model.indexOf( set );
		tableChanged( new TableModelEvent(model) );
		clearSelection();
		addRowSelectionInterval( rowIndex, rowIndex );
	}

	public void noWorkoutSelected() {
		clearSelection();
		model.setSelectedRow( -1 );
		model.setWorkout( null );
		tableChanged( new TableModelEvent( model ) );
	}

	public void workoutSelected(Workout workout) {	
		clearSelection();
		model.setSelectedRow( -1 );
		exerciseComboBox.setJournal( workout.getJournal() );
		getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(exerciseComboBox) );
		model.setWorkout( workout );
		tableChanged( new TableModelEvent( model ) );
	}
	
	public Workout getSelectedWorkout(){
		return model.getWorkout();
	}

	public void workoutsSelected(Workout[] workouts) {
		model.setWorkout( null );
		tableChanged( new TableModelEvent( model ) );
	}

	public void setsRemoved( Set[] set ) {
		tableChanged( new TableModelEvent(model) );
	}

	public void exerciseAdded(Exercise exercise) {
		tableChanged( new TableModelEvent( model ) );
	}

	public void exerciseChanged(Exercise exercise) {
		tableChanged( new TableModelEvent( model ) );
	}

	public void exerciseRemoved( Exercise exercise, Muscle muscle ) {
		tableChanged( new TableModelEvent( model ) );
	}

	public void exercisesRemoved(Exercise[] exercises) {
		tableChanged( new TableModelEvent( model ) );
	}

	public void exerciseChangedMuscle(Exercise exercise, Muscle fromMuscle,	Muscle toMusle) {
	}

	public void exerciseDisabled(Exercise exercise) {
	}

	public void exerciseEnabled(Exercise exercise) {
	}

	public void journalChanged(Journal journal) {
	}

	public void journalLoaded(Journal journal) {
		exerciseComboBox.setJournal( journal );
	}

	public void ownerChanged(Journal journal) {
	}

	public void localeChanged(Locale locale) {
		model.localeChanged( locale );
		setLocale( locale );
		tableChanged( new TableModelEvent( model, 0, getRowCount()  ) );
		tableChanged( new TableModelEvent( model, TableModelEvent.HEADER_ROW ) );
		setRowHeight( ExercisePanel.REQUIRED_HEIGHT );
		getColumnModel().getColumn( 0 ).setPreferredWidth( 200 );
		getColumnModel().getColumn( 1 ).setMaxWidth( 100 );
		getColumnModel().getColumn( 2 ).setMaxWidth( 100 );
		setGridColor(new Color(210, 210, 210));
		setDefaultRenderer( Integer.class , new OddEvenTableRenderer() );
		setDefaultRenderer( Double.class , new OddEvenTableRenderer() );
		setDefaultRenderer( Icon.class , new OddEvenTableRenderer() );
		setDefaultRenderer( String.class , new TextWrapTabellCellRenderer() );
		exerciseComboBox = new ExerciseComboBox();
		getColumnModel().getColumn(0).setCellEditor( new ExerciseCellEditor(exerciseComboBox) );
		getColumnModel().getColumn(0).setCellRenderer(  new SetsTableCellRenderer() );
		
		getColumnModel().getColumn(3).setCellEditor( new TextAreaEditor(this) );
		
//		getColumnModel().getColumn(2).setCellEditor( new SetSpinnerCellEditor() ); 
//		getColumnModel().getColumn(2).setCellRenderer( new SetSpinnerCellEditor() ); 
	}

}