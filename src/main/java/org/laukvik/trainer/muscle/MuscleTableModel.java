package org.laukvik.trainer.muscle;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.muscle.Muscle;

public class MuscleTableModel implements TableModel {
	
	Vector<TableModelListener> listeners;
	
	public MuscleTableModel() {
		this.listeners = new Vector<TableModelListener>();
	}

	public void addTableModelListener(TableModelListener l) {
		this.listeners.add( l );
	}

	public void removeTableModelListener(TableModelListener l) {
		this.listeners.remove( l );
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return Muscle.class;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		return "Muscle";
	}

	public int getRowCount() {
		return Human.BODY.listAllMuscles().length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return Human.BODY.listAllMuscles()[ rowIndex ];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}

}
