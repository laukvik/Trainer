package org.laukvik.trainer.set;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.exercise.ExercisePanel;

public class SetsTableCellRenderer extends ExercisePanel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Color odd = new Color(232,242,254);
	private Color even = new Color(255,255,255);

	public SetsTableCellRenderer() {
		super();
		setPreferredSize( new Dimension(300, ExercisePanel.REQUIRED_HEIGHT ) );
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setExercise( (Exercise) value, true );
		if (isSelected) {
			setBackground( table.getSelectionBackground() );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground(row % 2 == 0 ? odd : even);
			setForeground( table.getForeground() );
		}
		return this;
	}

}
