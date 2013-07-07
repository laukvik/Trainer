package org.laukvik.trainer.journal;

public class Status {
	
	public static final String TIME    = "time";
	
	public static final String BODYFAT = "bodyfat";
	public static final String WEIGHT  = "weight";
	public final static String NECK    = "neck";
	public final static String CHEST   = "chest";
	public final static String WAIST   = "waist";
	
	public final static String LEFTUPPERARM = "leftUpperArm";
	public final static String LEFTFOREARM  = "leftForeArm";
	public final static String LEFTTHIGH    = "leftThigh";
	public final static String LEFTCALF     = "leftCalf";
	
	public final static String RIGHTUPPERARM = "rightUpperArm";
	public final static String RIGHTFOREARM  = "rightForeArm";
	public final static String RIGHTTHIGH    = "rightThigh";
	public final static String RIGHTCALF     = "rightCalf";
	

	
	public final static String [] TYPES = 
	{ BODYFAT,WEIGHT,NECK,CHEST,WAIST, LEFTUPPERARM,LEFTFOREARM,LEFTTHIGH,LEFTCALF,	RIGHTUPPERARM,RIGHTFOREARM,RIGHTTHIGH,RIGHTCALF };
	
	
	public long time;
	
	public float weight = 0;
	public float bodyfat = 0;
	public float waist;
	public float neck;
	public float chest;
	
	public float leftUpperArm;
	public float leftForeArm;
	public float leftThigh;
	public float leftCalf;
	
	public float rightUpperArm;
	public float rightForeArm;
	public float rightThigh;
	public float rightCalf;

	public Status(){
		this( System.currentTimeMillis() );
	}
	
	public Status( long time ) {
		this.time = time;
	}
	
	public void setValue( String name, float value ){
		if (name.equalsIgnoreCase( BODYFAT )){
			this.bodyfat = value;
		} else if (name.equalsIgnoreCase( WEIGHT )){
			this.weight = value;
		} else if (name.equalsIgnoreCase( NECK )){
			this.neck = value;
		} else if (name.equalsIgnoreCase( CHEST )){
			this.chest = value;
		} else if (name.equalsIgnoreCase( WAIST )){
			this.waist = value;
			
		} else if (name.equalsIgnoreCase( LEFTUPPERARM )){
			this.leftUpperArm = value;
		} else if (name.equalsIgnoreCase( LEFTFOREARM )){
			this.leftForeArm = value;
		} else if (name.equalsIgnoreCase( LEFTTHIGH )){
			this.leftThigh = value;
		} else if (name.equalsIgnoreCase( LEFTCALF )){
			this.leftCalf = value;
			
		} else if (name.equalsIgnoreCase( RIGHTUPPERARM )){
			this.rightUpperArm = value;
		} else if (name.equalsIgnoreCase( RIGHTFOREARM )){
			this.rightForeArm = value;
		} else if (name.equalsIgnoreCase( RIGHTTHIGH )){
			this.rightThigh = value;
		} else if (name.equalsIgnoreCase( RIGHTCALF )){
			this.rightCalf = value;
		} else if (name.equalsIgnoreCase( TIME )){
			this.time = (long) value;
		} else {
			throw new IllegalArgumentException("Value '" + name + "' not valid type!" );
		}	
	}
	
	public float getValue( String name ){
		if (name.equalsIgnoreCase( BODYFAT )){
			return this.bodyfat;
		} else if (name.equalsIgnoreCase( WEIGHT )){
			return this.weight;
		} else if (name.equalsIgnoreCase( NECK )){
			return this.neck;
		} else if (name.equalsIgnoreCase( CHEST )){
			return this.chest;
		} else if (name.equalsIgnoreCase( WAIST )){
			return this.waist;
			
		} else if (name.equalsIgnoreCase( LEFTUPPERARM )){
			return this.leftUpperArm;
		} else if (name.equalsIgnoreCase( LEFTFOREARM )){
			return this.leftForeArm;
		} else if (name.equalsIgnoreCase( LEFTTHIGH )){
			return this.leftThigh;
		} else if (name.equalsIgnoreCase( LEFTCALF )){
			return this.leftCalf;
			
		} else if (name.equalsIgnoreCase( RIGHTUPPERARM )){
			return this.rightUpperArm;
		} else if (name.equalsIgnoreCase( RIGHTFOREARM )){
			return this.rightForeArm;
		} else if (name.equalsIgnoreCase( RIGHTTHIGH )){
			return this.rightThigh;
		} else if (name.equalsIgnoreCase( RIGHTCALF )){
			return this.rightCalf;
		} else if (name.equalsIgnoreCase( TIME )){
			return this.time;
		} else {
			throw new IllegalArgumentException("Value '" + name + "' not valid type!" );
		}
	}
	
	public long getMilliseconds() {
		return time;
	}

}