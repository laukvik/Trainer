package org.laukvik.trainer.exercise;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class TransparencySlider extends JSlider implements MouseWheelListener{

	private static final long serialVersionUID = 1L;

	public TransparencySlider( String transparent, String opaqueue ) {
		super( 0, 100, 90 );
		setMajorTickSpacing( 50 );
		setMinorTickSpacing( 10 );
		setPaintTicks( true );
		setMaximumSize( new Dimension(200,50) );
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new SliderLabel( transparent ) );
		labelTable.put( new Integer( 100 ), new SliderLabel( opaqueue ) );
		setLabelTable( labelTable );
		setPaintLabels(true);
		addMouseWheelListener( this );
	}
	
	public class SliderLabel extends JLabel{

		private static final long serialVersionUID = 1L;

		public SliderLabel( String text ){
			super( text );
			setFont( new Font( getFont().getName(), Font.PLAIN, 10 )  );
		}
		
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (isEnabled()){
			setValue( getValue() - e.getWheelRotation() );	
		}
	}
	
}