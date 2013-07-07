package org.laukvik.trainer.journal;

public interface JournalReadWriteListener {

	public void statusChanged( String message );
	public void progressChanged( int percent );
	public void setMinMax( int min, int max );
	
}