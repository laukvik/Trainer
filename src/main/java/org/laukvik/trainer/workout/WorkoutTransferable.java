package org.laukvik.trainer.workout;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class WorkoutTransferable implements Transferable {
	
	public final static DataFlavor WORKOUT_FLAVOR = new DataFlavor( Workout.class, "Workouts" );
	Workout [] workouts;
	
	public WorkoutTransferable( Workout... workouts ){
		this.workouts = workouts;
	}

	public Object getTransferData( DataFlavor flavor ) throws UnsupportedFlavorException, IOException {
		return workouts;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor [] { WORKOUT_FLAVOR };
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals( WORKOUT_FLAVOR );
	}

}
