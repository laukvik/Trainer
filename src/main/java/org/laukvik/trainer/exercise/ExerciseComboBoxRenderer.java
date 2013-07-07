package org.laukvik.trainer.exercise;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ExerciseComboBoxRenderer extends ExercisePanel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public ExerciseComboBoxRenderer() {
		setPreferredSize( new Dimension( 200, ExercisePanel.REQUIRED_HEIGHT ));
	}

	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ){
		Exercise x = (Exercise) value;
		setExercise( x, true );
		if (isSelected) {
			setBackground( list.getSelectionBackground() );
			setForeground( list.getSelectionForeground() );
		} else {
			setBackground( list.getBackground() );
			setForeground( list.getForeground() );
		}
		return this;
	}

}