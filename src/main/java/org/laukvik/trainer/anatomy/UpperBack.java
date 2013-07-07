package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.Trapezius;

public class UpperBack extends Part{

	public Trapezius TRAPEZIUS;
	
	public UpperBack( Back back ) {
		super("UpperBack", back );
	}

}