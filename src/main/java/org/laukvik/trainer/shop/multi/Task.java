package org.laukvik.trainer.shop.multi;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Task extends Thread{
	
	public final static int NOT_STARTED = -1;
	public final static int RUNNING     = 1;
	public final static int COMPLETED   = 0;
	
	private URL url;
	private int status = NOT_STARTED;
	
	int totalRead = 0;
	int toBeRead = 0;
	
	private TaskListener listener;
	
	public Task( URL url ){
		super();
		this.url = url;
	}
	
	public Task( String url ){
		super();
		try {
			this.url = new URL( url );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void setTaskListener( TaskListener listener ){
		this.listener = listener;
	}
	
	public int getTotalRead() {
		return totalRead;
	}
	
	public URL getURL(){
		return url;
	}
	
	public int getPercent(){
		if (toBeRead == 0){
			return 0;
		}
		return totalRead * 100 / toBeRead;
	}
	
	public String getStatusText(){
		return getPercent() + "%";
	}
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus( int status ){
		this.status = status;
		switch(status){
			case NOT_STARTED : log( "Not started" ); break;
			case COMPLETED : log( "Completed" ); break;
			case RUNNING : log( "Running" ); break;
		}
		if (this.status == COMPLETED){
			listener.taskCompleted( this );
		}
	}
	
	public boolean isNotStarted(){
		return status == NOT_STARTED;
	}
	
	public boolean isCompleted(){
		return status == COMPLETED;
	}
	
	public boolean isRunning(){
		return status == RUNNING;
	}
	
	public void log( Object message ){
//		System.out.println( this.getClass().getName() + ": "+ message );
	}
	
	public void run() {
		download();
	}
	
	public void download(){
		setStatus( RUNNING );
		try {
			getBytesFromFile( url );
		} catch (Exception e) {
			e.printStackTrace();
		}
		setStatus( COMPLETED );
	}
	
	public void getBytesFromFile( URL url ) throws Exception {
		URLConnection conn = url.openConnection();
		toBeRead = conn.getContentLength();
		
	    InputStream is = conn.getInputStream();
	    
	    // Create the byte array to hold the data
	    byte[] bytes = new byte[ 1024 ];
	
	    // Read in the bytes
	    totalRead = 0;
	    int numRead = 10;
	    while (numRead > 0) {
	    	numRead = is.read( bytes );
	    	totalRead += numRead;
//	    	log( "Reading " + totalRead  );
	    }
	
	    // Close the input stream and return bytes
	    is.close();
	}

		
}