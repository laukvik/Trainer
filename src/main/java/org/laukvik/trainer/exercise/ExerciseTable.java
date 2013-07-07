package org.laukvik.trainer.exercise;

import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

import org.laukvik.trainer.muscle.MuscleComboBox;

public class ExerciseTable extends JTable {

	private static final long serialVersionUID = 1L;

	public ExerciseTable( ExerciseTableModel model ) {
		super(model);
		getColumnModel().getColumn( 0 ).setPreferredWidth( 200 );
		getColumnModel().getColumn( 2 ).setPreferredWidth( 150 );
		setDefaultRenderer( Object.class , new OddEvenTableRenderer() );
		
		MuscleComboBox box = new MuscleComboBox( getLocale() );
		getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor( box ) );

		setGridColor(new Color(210, 210, 210));
		setRowHeight( getFont().getSize() + 10 );
	}
	
}