package org.laukvik.trainer.shop.multi;

import java.io.InputStream;
import java.net.URL;

public class SimpleThread extends Thread {

	int index;
	
	public SimpleThread(String str, int index) {
		super(str);
		this.index = index;
	}

	public void run() {
		try {
			getBytesFromFile( getName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBytesFromFile( String url ) throws Exception {
	    InputStream is = new URL( url ).openStream();
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[ 1024 ];
	
	    // Read in the bytes
	    int totalRead = 0;
	    int numRead = 10;
	    while (numRead > 0) {
	    	numRead = is.read( bytes );
	    	totalRead += numRead;
	    	System.out.println( index+  ". Reading " + totalRead  );
	    }
	
	    // Close the input stream and return bytes
	    is.close();
	}
	
}