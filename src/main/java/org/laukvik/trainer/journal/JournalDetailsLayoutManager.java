package org.laukvik.trainer.journal;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Vector;

public class JournalDetailsLayoutManager implements LayoutManager {
	
	public final static String FIXED    = "fixed";
	public final static String FLEXIBLE = "flexible";
	private Vector<Component> components;
	private Vector<String> constraints;
	private Dimension minimumLayoutSize;
	private Dimension preferredLayoutSize;
	
	/**
	 * add( datePicker, FIXED, 200 );
	 * add( aerobic, FIXED, 200 );
	 * add( textEditor );
	 * 
	 */
	public JournalDetailsLayoutManager(){
		this.components = new Vector<Component>();
		this.constraints = new Vector<String>();
	}

	public void layoutContainer( Container parent ){
		Dimension s = parent.getSize();
		System.out.println( s );
		
		/* Calculate flexible space */
		int flexibleHeight = s.height;
		int flexibleComponentCount = 0;
		for (Component c : components){
			if (!isFixed(c)){
				flexibleHeight -= c.getSize().height;
				flexibleComponentCount += 1;
			}
		}
		
		/* Calculate flexible space for each component  */
		int flexibleHeightForOneComponent = flexibleHeight / flexibleComponentCount;
		
		/* Set bounds for all components */
		int y = 0;
		for (Component c : components){
			int height = 0;
			if (isFixed(c)){
				/* Use preferred size */
				height = c.getPreferredSize().height;
			} else {
				/* Use as much as possible */
				height = flexibleHeightForOneComponent;
			}
			c.setBounds( 0 , y, s.width, height  );
			
			/* */
			y += height;
		}
		
		minimumLayoutSize = new Dimension( s.width, y );
//		preferredLayoutSize = new Dimension( s.width, y );
	} 
	
	private boolean isFixed( Component c ){
		int index = components.indexOf( c );
		return constraints.get(index).equalsIgnoreCase( FIXED );
	}
	
	public void addLayoutComponent(String name, Component comp) {
		components.add( comp );
		constraints.add( name );
	}
	
	public void removeLayoutComponent(Component comp) {
		int index = components.indexOf( comp );
		components.remove( comp );
		constraints.remove( index );
	}

	public Dimension minimumLayoutSize(Container parent) {
		return minimumLayoutSize;
	}

	public Dimension preferredLayoutSize(Container parent) {
		return preferredLayoutSize;
	}

}