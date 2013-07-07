package org.laukvik.trainer.journal;

public interface JournalListener {

	public void journalChanged( Journal journal );
	public void ownerChanged( Journal journal );
	public void journalLoaded( Journal journal );
	
}