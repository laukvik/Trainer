package org.laukvik.trainer.set;

public interface SetListener {

	public void setAdded( Set set );
	public void setRemoved( Set set );
	public void setChanged( Set set );
	public void setsRemoved( Set [] set );
	
}