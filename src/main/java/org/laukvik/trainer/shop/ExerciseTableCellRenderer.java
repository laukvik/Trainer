package org.laukvik.trainer.shop;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import org.laukvik.trainer.exercise.Exercise;

public class ExerciseTableCellRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public static final Color EVEN = new Color(232,242,254);
	public static final Color ODD = new Color(255,255,255);
	
	public ExerciseTableCellRenderer(){
		super();
		setLineWrap( true );
		setWrapStyleWord( true );
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setFont( table.getFont() );
		Exercise exercise = (Exercise) value;
		setText( exercise.getName() + "\n\n" + exercise.getNotes() );
		setLocale( table.getLocale() );
		if (isSelected){
			setBackground( table.getSelectionBackground()  );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground( row % 2 == 0 ? ODD : EVEN );
			setForeground( table.getForeground()  );
		}
		return this;
	}

}