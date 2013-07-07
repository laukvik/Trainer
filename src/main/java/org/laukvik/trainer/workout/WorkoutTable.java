package org.laukvik.trainer.workout;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.exercise.ExerciseListener;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalListener;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetListener;

public class WorkoutTable extends JTable implements JournalListener, ExerciseListener, WorkoutListener, SetListener, LocaleListener {

	private static final long serialVersionUID = 1L;
	private Journal journal;
	private WorkoutTableModel model;
	private WorkoutTableRenderer workoutTableRenderer;
	
	public WorkoutTable( WorkoutTableModel model ){
		super( model );
		this.model = model;
		this.workoutTableRenderer = new WorkoutTableRenderer();
		setDefaultRenderer( Workout.class, workoutTableRenderer );
		setGridColor(new Color(210, 210, 210));
		setBackground( new Color(220,225,232) ); // dce0e8
	}
	
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
	}
	
	public void scrollToRow( int rowIndex ){
		JViewport viewport = (JViewport)getParent();
	    
        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = getCellRect( rowIndex, 0, true);
    
        // The location of the view relative to the table
        Rectangle viewRect = viewport.getViewRect();
    
        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0).
        rect.setLocation(rect.x-viewRect.x, rect.y-viewRect.y);
    
        // Calculate location of rect if it were at the center of view
        int centerX = (viewRect.width-rect.width)/2;
        int centerY = (viewRect.height-rect.height)/2;
    
        // Fake the location of the cell so that scrollRectToVisible
        // will move the cell to the center
        if (rect.x < centerX) {
            centerX = -centerX;
        }
        if (rect.y < centerY) {
            centerY = -centerY;
        }
        rect.translate(centerX, centerY);
    
        // Scroll the area into view.
        viewport.scrollRectToVisible(rect);
	}
	
	public void reloadAll(){
		int [] rows = getSelectedRows();
//		
		tableChanged( new TableModelEvent( getModel() ) );
		updateRowSizes();
		getSelectionModel().clearSelection();
		for (int y : rows){
			getSelectionModel().addSelectionInterval( y, y );
		}
	}

	public void journalChanged(Journal journal) {
		reloadAll();
	}

	public void journalLoaded(Journal journal) {
		this.journal = journal;
		reloadAll();
	}

	public void ownerChanged(Journal journal) {
	}
	
	public void updateRowSizes(){
		int row=0;
		for (Workout work : journal.getWorkouts()){
			int rowSize = work.getSets().size() * WorkoutTableCell.getRowSize() + WorkoutTableCell.getPadding()*2; // 12 er fontst�rrelse og 10 er 2x5 pixel buffer oppe og nede
			if (rowSize < 30){
				rowSize = 30;
			}
			setRowHeight( row, rowSize );
			row++;
		}
	}
	
	public void updateRowSize( int rowIndex ){
		Workout work = journal.getWorkouts().get( rowIndex );
		int rowSize = work.getSets().size() * WorkoutTableCell.getRowSize() + WorkoutTableCell.getPadding()*2; // 12 er fontst�rrelse og 10 er 2x5 pixel buffer oppe og nede
		if (rowSize < 30){
			rowSize = 30;
		}
		setRowHeight( rowIndex, rowSize );
	}

	public void exerciseAdded(Exercise exercise) {
		
		reloadAll();
	}

	public void exerciseChanged(Exercise exercise) {
		reloadAll();
	}

	public void exerciseRemoved( Exercise exercise, Muscle muscle ) {
		reloadAll();
	}

	public void exercisesRemoved(Exercise[] exercises) {
		reloadAll();
	}

	public void setAdded(Set set) {
		repaintSet( set );
	}

	public void setChanged(Set set) {
		repaintSet( set );
	}

	public void setRemoved(Set set) {
		repaintSet( set );
	}

	public void setsRemoved(Set[] set) {
		int rowIndex = getSelectedRow();
		tableChanged( new TableModelEvent(model, rowIndex, rowIndex,0, TableModelEvent.UPDATE ) );
		updateRowSize( rowIndex );
	}
	
	public void log( Object message ){
		System.out.println( "WorkoutTable: " + message );
	}
	
	public void repaintSet( Set set ){
		int rowIndex = model.getRowIndex( set );
		tableChanged( new TableModelEvent(model, rowIndex, rowIndex,0, TableModelEvent.UPDATE ) );
		updateRowSize( rowIndex );
	}

	public void workoutAdded( Workout workout ) {
		reloadAll();
		int index = workout.getJournal().getWorkouts().indexOf( workout );
		setRowSelectionInterval( index , index );
	}

	public void workoutChanged(Workout workout) {
		int rowIndex = model.indexOf( workout );
		tableChanged( new TableModelEvent(model, rowIndex, rowIndex,0, TableModelEvent.UPDATE ) );
		updateRowSize( rowIndex );
	}

	public void workoutDateChanged(Workout workout) {
		int rowIndex = model.indexOf( workout );
		tableChanged( new TableModelEvent(model, rowIndex, rowIndex,0, TableModelEvent.UPDATE ) );
		updateRowSize( rowIndex );
		getSelectionModel().clearSelection();
	}

	public void workoutNotesChanged(Workout workout) {
	}

	public void workoutRemoved(Workout workout) {
		int index = getSelectedRow();
		reloadAll();
		setRowSelectionInterval( index , index );
	}

	public void workoutsRemoved(Workout[] workouts) {
		int index = getSelectedRow();
		reloadAll();
		setRowSelectionInterval( index , index );
	}

	public void workoutOrderChanged() {
		reloadAll();
	}

	public void exerciseChangedMuscle(Exercise exercise, Muscle fromMuscle,	Muscle toMusle) {
	}

	public void exerciseDisabled(Exercise exercise) {
	}

	public void exerciseEnabled(Exercise exercise) {
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		tableChanged( new TableModelEvent( model  ) );
		tableChanged( new TableModelEvent( model, TableModelEvent.HEADER_ROW ) );
		reloadAll();
	}

}