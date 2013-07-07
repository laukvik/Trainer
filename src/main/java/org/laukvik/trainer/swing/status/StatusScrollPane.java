package org.laukvik.trainer.swing.status;

import javax.swing.JScrollPane;
import org.laukvik.svg.swing.SVGPanel;

public class StatusScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public StatusScrollPane( SVGPanel panel ){
		super( panel );
//		setColumnHeaderView( horisontal );
//		setRowHeaderView( vertical );
		setWheelScrollingEnabled( true );
		
		
		setCorner( StatusScrollPane.UPPER_LEFT_CORNER , null );
		setCorner( StatusScrollPane.UPPER_RIGHT_CORNER , null );
	}
	
}