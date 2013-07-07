package org.laukvik.trainer.anatomy;

import java.util.Vector;

import org.laukvik.trainer.anatomy.muscle.Muscle;

public class Part {

	
	private String name = "";
	private Part [] parts = new Part[ 0 ];
	private Muscle [] muscles = new Muscle[ 0 ];
	private Part parent;
	
	public Part( String name, Part parent ){
		this.name = name;
		this.parent = parent;
	}

	public Part getParent() {
		return parent;
	}

	public void setParent(Part parent) {
		this.parent = parent;
	}
	
	public String getName(){
		return name;
	}
	
	public void setMuscles( Muscle...muscles ){
		this.muscles = muscles;
	}
	
	public Muscle [] listMuscles(){
		return muscles;
	}
	
	public Muscle [] listAllMuscles(){
		Vector <Muscle> items = new Vector<Muscle>();
		items.addAll( getRecursiveMuscles( this ) );
		Muscle [] muscles = new Muscle[ items.size() ];
		items.toArray( muscles );
		return muscles;
	}
	
	private Vector <Muscle> getRecursiveMuscles( Part part ){
		Vector <Muscle> items = new Vector<Muscle>();
		if (part == null){
			
		} else {
			for (Muscle m : part.muscles){
				items.add( m );
			}
			for(Part p : part.listParts()){
				items.addAll( getRecursiveMuscles(p) );
			}
		}
		return items;
	}
	
	public boolean hasMuscles(){
		return muscles != null && muscles.length > 0;
	}
	
	public void setParts( Part...parts ){
		this.parts = parts;
	}
	
	public Part [] listParts(){
		return parts;
	}
	
	public boolean hasParts(){
		return parts != null && parts.length > 0;
	}

	public boolean sameAs(Part anotherPart) {
		return this.name.equalsIgnoreCase( anotherPart.name );
	}
	
}