package org.laukvik.trainer.shop.multi;

public class TwoThreadsDemo {

	public static void main (String[] args) {
        new SimpleThread("http://www.ca.uky.edu/forestry/maehrelk/The%20elk%20landscape%202.jpg", 1).start();
        new SimpleThread("http://www.vanderbilt.edu/geo/program_images/landscape_ireland.gif",2).start();
    }
	
}