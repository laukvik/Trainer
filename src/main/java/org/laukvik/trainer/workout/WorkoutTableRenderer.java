package org.laukvik.trainer.workout;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class WorkoutTableRenderer extends WorkoutTableCell implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public static final Color EVEN = new Color(232,242,254);
	public static final Color ODD = new Color(255,255,255);
	
	public WorkoutTableRenderer(){
		super();
	}
	
	public int getFontSize(){
		return 12;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Workout work = (Workout) value;
		setLocale( table.getLocale() );
		if (isSelected){
			setBackground( table.getSelectionBackground()  );
			setForeground( Color.WHITE  );
		} else {
			setBackground( row % 2 == 0 ? ODD  : EVEN  );
			setForeground( Color.BLACK  );
		}
		setSelected( isSelected );
		setWorkout( work );
		return this;
	}

}