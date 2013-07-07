package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.Gastrocnemius;

public class Calf extends Part {
	
	public Gastrocnemius GASTROCNEMIUS;
	
	public Calf(Leg leg) {
		super("Calf", leg );
	}
	
}