package org.laukvik.trainer.aerobic;

import java.awt.Color;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;

import org.laukvik.trainer.workout.Workout;

public class AerobicTable extends JTable {

	private static final long serialVersionUID = 1L;
	private AerobicTableModel model;
	Color disabledColor = Color.GRAY;

	public AerobicTable() {
		super();
		this.model = new AerobicTableModel();
		setModel( model );
		getSelectionModel().setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		setGridColor( new Color(230,230,230) );
		setColumnWidths();
		setEnabled( false );
	}

	public void setWorkout(Workout workout) {
		model.setWorkout( workout );
		tableChanged( new TableModelEvent( model ) );
	}
	
	public void setEnabled( boolean enabled ) {
		super.setEnabled(enabled);
		if (!enabled){
			setForeground( UIManager.getColor("Label.disabledForeground") );
			getTableHeader().setForeground( UIManager.getColor("Label.disabledForeground") );
		} else {
			setForeground( UIManager.getColor("Label.foreground") );
			getTableHeader().setForeground( UIManager.getColor("Label.foreground") );
		}
	}

	public void setLocale(Locale l) {
		super.setLocale(l);
		model.setLocale( l );
		tableChanged( new TableModelEvent( model, TableModelEvent.HEADER_ROW ) );
		setColumnWidths();
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		tableChanged( new TableModelEvent( model ) );
		setColumnWidths();
	}
	
	public void setColumnWidths(){
		getColumnModel().getColumn(1).setMaxWidth( 32 );
		getColumnModel().getColumn(2).setMaxWidth( 32 );
	}
	
}