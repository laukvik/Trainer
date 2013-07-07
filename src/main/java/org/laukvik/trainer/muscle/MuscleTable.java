package org.laukvik.trainer.muscle;

import javax.swing.JTable;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.muscle.Muscle;

public class MuscleTable extends JTable {
	
	private static final long serialVersionUID = 1L;

	public MuscleTable() {
		super( new MuscleTableModel() );
//		setMaximumSize( new Dimension(200,20000) );
	}
	
	public Muscle getSelectedMuscle(){
		if (getSelectedRow() > -1){
			return Human.BODY.listAllMuscles()[ getSelectedRow() ];
		} else {
			return null;
		}
	}

}