package org.laukvik.trainer.units;

public class Distance {

	private String name;
	
	public Distance( String name ){
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static Distance parse( String value ) {
		if (value.equalsIgnoreCase( Kilometer.KILOMETER )){
			return new Kilometer();
		} else if (value.equalsIgnoreCase( Mile.MILE )){
			return new Mile();
		} else {
			return new Kilometer();
		}
	}

	public static Distance [] listUnits() {
		return new Distance [] { new Kilometer(), new Mile() };
	}
	
}