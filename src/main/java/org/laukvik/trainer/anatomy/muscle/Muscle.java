package org.laukvik.trainer.anatomy.muscle;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.Part;

public class Muscle {
	
	/** **/
	public final static Muscle UNKNOWN = new Muscle(-1,"Unknown", null  );
	
	private int muscleID;
	String latin;
	String name;
	Part part;
	
	protected Muscle( int muscleID, String latin, Part parent ){
		this.muscleID = muscleID;
		this.latin = latin;
		this.part = parent;
	}
	
	public static Muscle getInstance( int muscleID ){
		return Human.BODY.getMuscle(muscleID);
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Part getPart(){
		return part;
	}

	public int getMuscleID(){
		return muscleID;
	}
	
	/**
	 * Returns the latin name of the muscle
	 * 
	 * @return
	 */
	public String getLatin(){
		return latin;
	}

	public String toString() {
		return getClass().getSimpleName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean sameAs( Muscle muscle ) {
		return this.muscleID == muscle.muscleID;
	}
	
}