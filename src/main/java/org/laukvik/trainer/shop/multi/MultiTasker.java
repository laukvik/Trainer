package org.laukvik.trainer.shop.multi;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MultiTasker implements TaskListener{
	
	/* A variable keeping all tasks to be done  */
	private Vector <Task> tasks = new Vector<Task>();
	private Timer timer;
	private int maxTasks = 2;
	
	public MultiTasker(){
	}
	
	/**
	 * Move completed tasks into other vector
	 * 
	 */
	public void taskCompleted( Task task ){

	}
	
	public void addTask( Task task ){
		task.setName( "Task #" + (tasks.size()+1) + " download " + task.getURL() );
		task.setTaskListener( this );
		tasks.add( task );
	}
	
	public void start( long delay ){
		startRemainingTasks();
		timer = new Timer();
		timer.schedule( new ScheduledTask(), 0, delay );
	}
	
	public void startRemainingTasks(){
		int running = runningCount();
		log( "Remaining tasks: " + running + "/" + maxTasks );
		for (Task t : tasks){
			if (running < maxTasks){
				if (t.isNotStarted() && !t.isRunning()){
					t.start();
					running++;
				}
			}
		}
	}
	
	public int runningCount(){
		int running = 0;
		for (Task t: tasks){
			if (t.isRunning()){
				running++;
			}
		}
		return running;
	}
	
	public void log( Object message ){
//		System.out.println( this.getClass().getName() + ": "+ message );
	}
	
	public void stop(){
		for (Task t : tasks){
			t.stop();
		}
		timer.cancel();
	}
	
	/**
	 * Returns whether or not all tasks are completed 
	 * 
	 * @return
	 */
	public boolean isComplete(){
		int max = tasks.size();
		int completed = 0;
		for (Task t: tasks){
			if (t.isCompleted()){
				completed++;
			}
		}
		return max == completed;
	}

	private class ScheduledTask extends TimerTask {

		public void run() {
			
			System.err.println( "Multitasker holds the following tasks" );
			for (Task t : tasks){
				if (t.isRunning()){
					System.out.println(  "\t" + t.getName() + " " +  t.isCompleted() + " " + t.getStatusText() );
				} else if (t.isCompleted()){
					System.out.println(  "\tDone!" );
				}
				
			}
			
			startRemainingTasks();
			
			if (isComplete()){
				stop();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		MultiTasker multi = new MultiTasker();
		multi.addTask( new Task( "http://www.ca.uky.edu/forestry/maehrelk/The%20elk%20landscape%202.jpg" ) );
		multi.addTask( new Task( "http://www.vanderbilt.edu/geo/program_images/landscape_ireland.gif" ) );
		multi.addTask( new Task( "http://www.nordpeis.no/arch/_img/9100799.jpg" ) );
		
		multi.start( 1000 );
	}

}