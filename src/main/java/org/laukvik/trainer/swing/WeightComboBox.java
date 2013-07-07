package org.laukvik.trainer.swing;

import javax.swing.JComboBox;

import org.laukvik.trainer.units.Weight;

public class WeightComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	public WeightComboBox(){
		super( Weight.listUnits() );
	}

	public Weight getSelectedWeight() {
		return (Weight) getSelectedItem();
	}
	
	public void setSelectedItem( Object anObject ) {
		if (anObject instanceof Weight){
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