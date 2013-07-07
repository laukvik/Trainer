package org.laukvik.trainer.journal;

import java.util.Comparator;

/**
 * Sorts the statuses with the earliest date first
 * 
 * @author Morten
 *
 */
public class StatusComparator implements Comparator<Status> {

	public StatusComparator(){
	}

	public int compare( Status w1, Status w2 ) {
		if (w1.time > w2.time){
			return 1;
		} else {
			return 0;
		}
	}
	
}