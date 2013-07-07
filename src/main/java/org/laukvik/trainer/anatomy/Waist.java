package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.ObliquusExternusAbdominis;
import org.laukvik.trainer.anatomy.muscle.ObliquusInternusAbdominis;

public class Waist extends Part{
	
	public ObliquusExternusAbdominis OBLIQUUS_EXTERNUS_ABDOMINIS;
	public ObliquusInternusAbdominis OBLIQUUS_INTERNUS_ABDOMINIS;
	
	public Waist(Human human) {
		super( "Waist", human );
	}

}
