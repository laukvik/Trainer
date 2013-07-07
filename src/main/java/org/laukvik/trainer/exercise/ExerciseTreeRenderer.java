package org.laukvik.trainer.exercise;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.swing.JournalHelper;

public class ExerciseTreeRenderer extends DefaultTreeCellRenderer{

	private static final long serialVersionUID = 4723243992042912630L;
	private Journal journal;
	
	public ExerciseTreeRenderer(){
		super();
	}
	
	public void loadJournal( Journal journal ){
		this.journal = journal;
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (value instanceof Muscle){
			Muscle m = (Muscle) value;
			int size =journal.getExercises(m).size();
			setText( JournalHelper.getLanguage( journal.getLocale(), m.getLatin().toLowerCase().replace(" ", ".") )+ " (" + size + ")"  );
			setToolTipText( "Latin: " + m.getLatin() );
			
		} else if (value instanceof Part){
			setText( JournalHelper.getLanguage( journal.getLocale(), ((Part)value).getName().toLowerCase().replace(" ", ".") ) );
			setToolTipText( null );
			
		} else if (value instanceof Exercise){
			setText( ((Exercise)value).getName() );
			setToolTipText( null );
			
		} else {
			setText( value.getClass().getName() );
			setToolTipText( null );
		}
		return this;
	}

}