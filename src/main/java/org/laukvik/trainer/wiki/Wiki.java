package org.laukvik.trainer.wiki;

import java.net.URL;

import javax.swing.ImageIcon;

public class Wiki {

	public static ImageIcon getIcon( String filename ){
		return new javax.swing.ImageIcon( Wiki.class.getResource( filename ));
	}

	public static URL getFile( String filename ){
		return Wiki.class.getResource( filename );
	}
	
}