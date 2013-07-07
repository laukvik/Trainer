package org.laukvik.trainer.exercise;

import java.util.Locale;
import java.util.Vector;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;

public class ExerciseTreeModel implements TreeModel {
	
	private Journal journal;
	private Vector<TreeModelListener> listeners;
	private Locale locale;
	private Human human;
	
	public ExerciseTreeModel( Journal journal ){
		this.journal = journal;
		setLocale( journal.getLocale() );
		listeners = new Vector<TreeModelListener>();
		human = Human.BODY;
	}
	
	public Vector<Object> listObjects( Part part ){
		Vector<Object> items = new Vector<Object>();
		for (Part p : part.listParts()){
			items.add( p );
		}
		for (Muscle m : part.listMuscles()){
			items.add( m );
		}
		return items;
	}
	
	public Object getChild( Object parent, int index ) {
//		log( "getChild: " + index + " " + parent );
		/* Returns Journal as the root */
		if (parent instanceof Journal){
			return human.listParts()[ index ];

		} else if (parent instanceof Part){
			Part p = (Part) parent;
			return listObjects( p ).get( index );
			
		} else if (parent instanceof Muscle){
			return journal.getExercises( (Muscle) parent ).get( index );
			
		} else if (parent instanceof Exercise){
			return (Exercise) parent;
			
		} else {
			return null;	
		}
	}

	public int getChildCount( Object parent ){
		if (parent instanceof Journal){
			return human.listParts().length;
			
		} else if (parent instanceof Part){
			Part p = (Part) parent;
			return p.listParts().length + p.listMuscles().length;
			
		} else if (parent instanceof Muscle){
			return journal.getExercises( (Muscle) parent ).size();
			
		} else if (parent instanceof Exercise){
			return 0;
			
		} else{
			return 0;
		}
	}

	public int getIndexOfChild( Object parent, Object child ) {
		if (child instanceof Journal){
			return 0;
			
		} else if (parent instanceof Part){
			Part p = (Part) parent;
			return listObjects( p ).indexOf( child );
		} else if (child instanceof Muscle){
			return ((Muscle) child).getMuscleID();
			
		} else if (child instanceof Exercise){
			return journal.getExercises().indexOf( (Exercise) child );
			
		} else {
			return 0;	
		}
	}

	/**
	 * Leaf = no child
	 */
	public boolean isLeaf( Object parent ) {
		if (parent instanceof Journal){
			return false;
		} else if (parent instanceof Part){
			return false;
		} else if (parent instanceof Muscle){
			return false;
		} else if (parent instanceof Exercise){
			return true;
		} else {
			return false;
		}
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void log( Object message ){
		System.out.println( ExerciseTreeModel.class.getName() + ": " + message );
	}

	/**
	 * Returns the Journal
	 * 
	 */
	public Object getRoot() {
		return journal;
	}


	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove( l ); 
	}

	public void addTreeModelListener(TreeModelListener l) {
		listeners.add( l );
	}

	public void treeNodesChanged( TreeModelEvent evt ) {
		for (TreeModelListener l : listeners){
			l.treeNodesChanged( evt );
		}
	}
	
	public void treeNodesInserted( TreeModelEvent evt ) {
		for (TreeModelListener l : listeners){
			l.treeNodesInserted( evt );
		}
	}
	
	public void treeNodesRemoved( TreeModelEvent evt ) {
		for (TreeModelListener l : listeners){
			l.treeNodesRemoved(evt);
		}
	}
	
	public void treeStructureChanged( TreeModelEvent evt ) {
		for (TreeModelListener l : listeners){
			l.treeStructureChanged( evt);
		}
	}

	public void valueForPathChanged( TreePath path, Object newValue ) {
		treeStructureChanged( new TreeModelEvent(this,path) );
	}
	
	public void valueForPathChanged( Object object, TreePath path, int [] childIndices, Object [] children ) {
		treeStructureChanged( new TreeModelEvent( object,path,childIndices,children) );
	}

}