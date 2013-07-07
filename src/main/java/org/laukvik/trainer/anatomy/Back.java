package org.laukvik.trainer.anatomy;

public class Back extends Part {
	
	public UpperBack UPPER;
	public MiddleBack MIDDLE;
	public LowerBack LOWER;
	public Neck NECK;

	public Back(Human human) {
		super("Back",human);
	}

}
