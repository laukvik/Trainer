package org.laukvik.trainer.set;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RepSpinner extends JSpinner {
	

	private static final long serialVersionUID = 1L;

	public RepSpinner(){
		super( new SpinnerNumberModel( 0, 0,1000,1 ) );
		setBorder( null );
	}
	
}