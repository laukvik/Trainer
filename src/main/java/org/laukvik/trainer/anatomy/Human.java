package org.laukvik.trainer.anatomy;

import org.laukvik.trainer.anatomy.muscle.BicepsBrachii;
import org.laukvik.trainer.anatomy.muscle.BicepsFemoris;
import org.laukvik.trainer.anatomy.muscle.Deltoideus;
import org.laukvik.trainer.anatomy.muscle.ErectusSpinae;
import org.laukvik.trainer.anatomy.muscle.ExtensorDigitorumRadialis;
import org.laukvik.trainer.anatomy.muscle.FlexorDigitorumSuperficialis;
import org.laukvik.trainer.anatomy.muscle.Gastrocnemius;
import org.laukvik.trainer.anatomy.muscle.GluteusMaximus;
import org.laukvik.trainer.anatomy.muscle.LatissimusDorsi;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.anatomy.muscle.ObliquusExternusAbdominis;
import org.laukvik.trainer.anatomy.muscle.ObliquusInternusAbdominis;
import org.laukvik.trainer.anatomy.muscle.PectoralisMajor;
import org.laukvik.trainer.anatomy.muscle.QuadricepsFemoris;
import org.laukvik.trainer.anatomy.muscle.SerratusAnterior;
import org.laukvik.trainer.anatomy.muscle.Trapezius;
import org.laukvik.trainer.anatomy.muscle.TricepsBrachii;

public class Human extends Part{
	
	public final static Human BODY = Human.getInstance();
	private static Human instance = null;
	
	public Arm ARM;
	public Waist WAIST;
	public Chest CHEST;
	public Back BACK;
	public Leg LEG;
	public Shoulder SHOULDERS;
	public Buttocks BUTTOCKS;

	protected Human(){
		super( "Body", null );
		
		/* ARM */
		ARM = new Arm( this );
		ARM.LOWER = new LowerArm( ARM );
		ARM.LOWER.EXTENSOR_DIGITORUM_RADIALIS = new ExtensorDigitorumRadialis( ARM.LOWER );
		ARM.LOWER.FLEXOR_DIGITORUM_RADIALIS = new FlexorDigitorumSuperficialis( ARM.LOWER );
		ARM.LOWER.setMuscles( ARM.LOWER.EXTENSOR_DIGITORUM_RADIALIS, ARM.LOWER.FLEXOR_DIGITORUM_RADIALIS );
		ARM.UPPER = new UpperArm( ARM );
		ARM.UPPER.TRICEPS_BRACHII = new TricepsBrachii( ARM.UPPER );
		ARM.UPPER.BICEPS_BRACHII = new BicepsBrachii( ARM.UPPER );
		ARM.UPPER.setMuscles( ARM.UPPER.TRICEPS_BRACHII, ARM.UPPER.BICEPS_BRACHII );
		ARM.setParts( ARM.LOWER, ARM.UPPER );

		/* LEGS */
		LEG      = new Leg( this );
		LEG.THIGH = new Thigh( LEG );
		LEG.THIGH.BICEPS_FEMORIS = new BicepsFemoris( LEG.THIGH );
		LEG.THIGH.QUADRICEPS_FEMORIS = new QuadricepsFemoris( LEG.THIGH );
		LEG.THIGH.setMuscles( LEG.THIGH.BICEPS_FEMORIS, LEG.THIGH.QUADRICEPS_FEMORIS );
		LEG.CALF = new Calf( LEG );
		LEG.CALF.GASTROCNEMIUS = new Gastrocnemius( LEG.CALF );
		LEG.CALF.setMuscles( LEG.CALF.GASTROCNEMIUS );
		LEG.setParts( LEG.THIGH, LEG.CALF );
		
		/* SHOULDER */
		SHOULDERS  = new Shoulder( this );
//		SHOULDERS.FRONT = new FrontShoulder( SHOULDERS );
//		SHOULDERS.MIDDLE = new MiddleShoulder( SHOULDERS );
//		SHOULDERS.BACK = new BackShoulder( SHOULDERS );
		
		SHOULDERS.DELTOIDEUS = new Deltoideus( SHOULDERS );
		SHOULDERS.SERRATUS_ANTERIOR = new SerratusAnterior( SHOULDERS );
		SHOULDERS.setMuscles( SHOULDERS.DELTOIDEUS, SHOULDERS.SERRATUS_ANTERIOR );
//		SHOULDERS.setParts( SHOULDERS.FRONT, SHOULDERS.MIDDLE, SHOULDERS.BACK );
		
		/* BUTTOCK */
		BUTTOCKS = new Buttocks( this );
		BUTTOCKS.GLUTEUS_MAXIMUS = new GluteusMaximus( BUTTOCKS );
		BUTTOCKS.setMuscles( BUTTOCKS.GLUTEUS_MAXIMUS );
		
		/* WAIST */
		WAIST  = new Waist( this );
		WAIST.OBLIQUUS_EXTERNUS_ABDOMINIS = new ObliquusExternusAbdominis( WAIST );
		WAIST.OBLIQUUS_INTERNUS_ABDOMINIS = new ObliquusInternusAbdominis( WAIST );
		WAIST.setMuscles( WAIST.OBLIQUUS_EXTERNUS_ABDOMINIS, WAIST.OBLIQUUS_INTERNUS_ABDOMINIS );
		
		/* CHEST */
		CHEST  = new Chest( this );
		CHEST.PECTORALIS_MAJOR = new PectoralisMajor( CHEST );
		CHEST.setMuscles( CHEST.PECTORALIS_MAJOR );
		
		BACK    = new Back( this );
		BACK.UPPER = new UpperBack( BACK );
		BACK.UPPER.TRAPEZIUS = new Trapezius( BACK.UPPER );
		BACK.MIDDLE = new MiddleBack( BACK );
		BACK.MIDDLE.LATISSIMUS_DORSI = new LatissimusDorsi( BACK.MIDDLE );
		BACK.LOWER = new LowerBack( BACK );
		BACK.LOWER.ERECTUS_SPINAE = new ErectusSpinae( BACK.LOWER );
		
		BACK.UPPER.setMuscles( BACK.UPPER.TRAPEZIUS );
		BACK.MIDDLE.setMuscles( BACK.MIDDLE.LATISSIMUS_DORSI );
		BACK.LOWER.setMuscles( BACK.LOWER.ERECTUS_SPINAE );
		BACK.setParts( BACK.UPPER, BACK.MIDDLE, BACK.LOWER );
		
		setParts( ARM, WAIST, CHEST, BACK, LEG, SHOULDERS, BUTTOCKS );
	}
	
	public Muscle getMuscle( int muscleID ){
		for (Muscle m : listAllMuscles()){
			if (m.getMuscleID() == muscleID){
				return m;
			}
		}
		return Muscle.UNKNOWN;
	}
	
	public static Human getInstance(){
		if (instance == null){
			instance = new Human();
		}
		return instance;
	}

	public Arm getArm() {
		return ARM;
	}

	public Waist getWaist() {
		return WAIST;
	}

	public Chest getChest() {
		return CHEST;
	}

	public Back getBack() {
		return BACK;
	}

	public Leg getLeg() {
		return LEG;
	}

	public Shoulder getShoulders() {
		return SHOULDERS;
	}

	public Buttocks getButtocks() {
		return BUTTOCKS;
	}

	public Human getBody() {
		return BODY;
	}

}