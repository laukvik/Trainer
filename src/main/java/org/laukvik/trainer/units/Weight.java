package org.laukvik.trainer.units;


public class Weight {

	private String name;
	
	public Weight( String name ){
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static Weight parse( String value ) {
		if (value.equalsIgnoreCase( Kilogram.KG )){
			return new Kilogram();
		} else if (value.equalsIgnoreCase( Pounds.POUNDS )){
			return new Pounds();
		} else {
			return new Kilogram();
		}
	}

	public static Weight [] listUnits() {
		return new Weight [] { new Kilogram(), new Pounds() };
	}
	
}