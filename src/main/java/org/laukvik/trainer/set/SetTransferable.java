package org.laukvik.trainer.set;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class SetTransferable implements Transferable {
	
	public final static DataFlavor SETS_FLAVOR = new DataFlavor( Set.class, "Workout sets" );
	Set [] sets;
	
	public SetTransferable( Set... sets ){
		this.sets = sets;
	}

	public Object getTransferData( DataFlavor flavor ) throws UnsupportedFlavorException, IOException {
		return sets;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor [] { SETS_FLAVOR };
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals( SETS_FLAVOR );
	}

}
