package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.PectoralisMajor;

public class Chest extends Part {
	
	public PectoralisMajor PECTORALIS_MAJOR;

	public Chest(Human human) {
		super( "Chest", human );
	}
	
}