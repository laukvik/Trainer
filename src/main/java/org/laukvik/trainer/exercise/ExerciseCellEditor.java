package org.laukvik.trainer.exercise;

import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellEditor;

public class ExerciseCellEditor extends DefaultCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;

	public ExerciseCellEditor( ExerciseComboBox setsCombo ) {
		super( setsCombo );
	}
	
}