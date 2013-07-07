package org.laukvik.trainer.swing;

import javax.swing.JComboBox;

import org.laukvik.trainer.units.Distance;

public class DistanceComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	public DistanceComboBox(){
		super( Distance.listUnits() );
	}

	public Distance getSelectedDistance() {
		return (Distance) getSelectedItem();
	}
	
	public void setSelectedItem( Object anObject ) {
		if (anObject instanceof Distance){
			int max = getItemCount();
			for (int x=0; x<max; x++){
				Object item = getItemAt(x);
				if (getItemAt(x).getClass().getName().equalsIgnoreCase( anObject.getClass().getName() )){
					super.setSelectedItem( item );
				}
			}
		} else {
			/* Object not allowed */
		}
	}
	
}