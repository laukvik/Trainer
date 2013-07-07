package org.laukvik.trainer.exercise;

import java.awt.Graphics;
import org.laukvik.svg.shape.SVG;
import org.laukvik.svg.swing.SVGEditablePanel;

public class ExerciseTab extends SVGEditablePanel {

	private static final long serialVersionUID = 1L;
	private Exercise exercise;
	
	public ExerciseTab( Exercise exercise, SVG svg ){
		this.exercise = exercise;
		setSVG( svg );
	}
	
	public ExerciseTab( Exercise exercise ){
		this( exercise, null );
	}
	
	public Exercise getExercise() {
		return exercise;
	}
	
	public void paint(Graphics g) {
		if (getSVG() == null){
			
		} else {
			super.paint(g);
		}
	}
	
}