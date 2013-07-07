package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.Deltoideus;
import org.laukvik.trainer.anatomy.muscle.SerratusAnterior;

public class Shoulder extends Part {
	
	public Deltoideus DELTOIDEUS;
	public SerratusAnterior SERRATUS_ANTERIOR;
	
//	public DeltoideusAnterior  ANTERIOR;
//	public DeltoideusMiddle MIDDLE;
//	public DeltoideusPosterior   BACK;

	public Shoulder( Human human ) {
		super("Shoulder", human );
	}

}
