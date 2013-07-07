package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.ExtensorDigitorumRadialis;
import org.laukvik.trainer.anatomy.muscle.FlexorDigitorumSuperficialis;

public class LowerArm extends Part {
	
	public ExtensorDigitorumRadialis EXTENSOR_DIGITORUM_RADIALIS;
	public FlexorDigitorumSuperficialis FLEXOR_DIGITORUM_RADIALIS;
	
	public LowerArm( Part part ) {
		super("LowerArm", part );
	}	
	
}