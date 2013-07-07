package org.laukvik.trainer.exercise;

import java.util.Locale;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;

public class ExerciseTree extends JTree implements ExerciseListener, LocaleListener {

	private static final long serialVersionUID = 1L;
	private ExerciseTreeModel model;
	private Journal journal;
	private ExerciseTreeRenderer renderer;
	
	public ExerciseTree(){
		super();
		this.journal = new Journal();
		this.renderer = new ExerciseTreeRenderer();
		setRootVisible( false );
		setCellRenderer( renderer );
		getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
	}
	
	public void setJournal( Journal journal ){
		this.journal = journal;
		renderer.loadJournal( journal );
		model = new ExerciseTreeModel( journal );
		setCellRenderer( renderer );
		setModel( model );
	}
	
	public void log( Object message ){
//		System.out.println( ExerciseTree.class.getName() + ": " + message );
	}

	public void exerciseAdded( Exercise exercise ) {
		log( "exerciseAdded" );
		TreePath pathMuscle = new TreePath( new Object[]{ journal, exercise.getMuscle() });
		model.treeStructureChanged( new TreeModelEvent( journal, pathMuscle ) );
		
		TreePath pathExercise = new TreePath( new Object[]{ journal, exercise.getMuscle(), exercise });
		getSelectionModel().setSelectionPath( pathExercise );
		makeVisible( pathExercise );
	}

	public void exerciseChanged( Exercise exercise ) {
		Muscle muskel = exercise.getMuscle();
		Part underGruppe = muskel.getPart(); 
		Part hovedGruppe = underGruppe.getParent();
		TreePath path = new TreePath( new Object[]{ journal, hovedGruppe, underGruppe, muskel  });
		int [] childIndices = { childIndex };
		Object [] children = { exercise };
		/* Update exercise node */
		model.treeNodesChanged( new TreeModelEvent( this, path, childIndices, children ) );
		

		
	}

	/**
	 * Fires when an exercise was removed
	 * 
	 */
	public void exerciseRemoved( Exercise exercise, Muscle muscle ) {
		Muscle muskel = exercise.getMuscle();
		Part underGruppe = muskel.getPart(); 
		Part hovedGruppe = underGruppe.getParent();
		TreePath path = new TreePath( new Object[]{ journal, hovedGruppe, underGruppe, muskel  });
		int [] childIndices = { childIndex };
		Object [] children = { exercise };
		model.treeNodesRemoved( new TreeModelEvent( this, path, childIndices, children ) );
		
		int [] childIndices2 = { 0 };
		Object [] children2 = { underGruppe };
		/* Update Muscle node (the parent) */
		model.treeNodesChanged( new TreeModelEvent( this, path.getParentPath(), childIndices2, children2 ) );
	}
	
	int childIndex = -1;
	public void setChildIndice( int index ){
		log( "Setting child indices: " + index );
		this.childIndex = index;
	}

	public void exercisesRemoved( Exercise[] exercises ) {
	}

	public void exerciseChangedMuscle( Exercise exercise, Muscle fromMuscle, Muscle toMusle) {
		log( "exerciseChangedMuscle" );
		TreePath pathFrom = new TreePath( new Object[]{ journal, fromMuscle });		
		model.treeStructureChanged( new TreeModelEvent( journal, pathFrom ) );
	
		TreePath pathTo = new TreePath( new Object[]{ journal, toMusle });		
		model.treeStructureChanged( new TreeModelEvent( journal, pathTo ) );
		
		TreePath path = new TreePath( new Object[]{ journal, toMusle, exercise });		
		model.treeStructureChanged( new TreeModelEvent( journal, path ) );
		getSelectionModel().setSelectionPath( path );
		makeVisible( pathTo );
	}

	public void exerciseDisabled(Exercise exercise) {
	}

	public void exerciseEnabled(Exercise exercise) {
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		model = new ExerciseTreeModel( journal );
		setModel( model );
	}

}