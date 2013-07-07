package org.laukvik.trainer.aerobic;

import java.util.Locale;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.units.Kilometer;
import org.laukvik.trainer.workout.Workout;

public class AerobicTableModel implements TableModel{
	
	Vector <TableModelListener> listeners;
	Workout workout;
	Locale locale;

	public AerobicTableModel(){
		listeners = new Vector<TableModelListener>();
		this.workout = new Workout(0,null);
		this.locale = JournalHelper.getDefaultLocale();
	}
	
	public void setLocale( Locale locale ) {
		this.locale = locale;
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.add( l );
	}
	
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove( l );
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex){
			case 0 : return String.class;
			case 1 : return Integer.class;
			case 2 : return Float.class;
			case 3 : return Integer.class;
			default : return Object.class;
		}
	}

	public int getColumnCount() {
		return 4;
	}
	
	public String getUnit(){
		if (workout == null || workout.getJournal() == null){
			return new Kilometer().toString();
		}
		return workout.getJournal().getDistance().toString();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex){
			case 0 : return JournalHelper.getLanguage( locale, "aerobic" );
			case 1 : return JournalHelper.getLanguage( locale, "minutes" );
			case 2 : return JournalHelper.getLanguage( locale, "distance." + getUnit().toLowerCase() );
			case 3 : return JournalHelper.getLanguage( locale, "aerobic.heartrate"  );
			default : return null;
		}
	}

	public int getRowCount() {
		return workout.listAerobics().length;
	}
	
	public Aerobic getRow( int rowIndex ){
		return workout.listAerobics()[ rowIndex ];
	}

	public Object getValueAt( int rowIndex, int columnIndex ) {
		Aerobic a =  getRow( rowIndex );
		switch (columnIndex){
			case 0 : return JournalHelper.getLanguage( locale, a.toString().toLowerCase() );
			case 1 : return a.getDuration();
			case 2 : return a.getDistance();
			case 3 : return a.getHeartRate();
			default : return null;
		}
	}

	public boolean isCellEditable( int rowIndex, int columnIndex ) {
		return columnIndex > 0;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Number n  = (Number) value;
		Aerobic a =  getRow( rowIndex );
		if (columnIndex == 1){
			a.setDuration( n.longValue() );
		} else if (columnIndex == 2){
			a.setDistance( n.floatValue() );
		} else if (columnIndex == 3){
			a.setHeartRate( n.intValue() );
		}
	}

	public void setWorkout( Workout workout ) {
		this.workout = workout;
	}
	
}