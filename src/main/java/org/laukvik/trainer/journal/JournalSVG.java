package org.laukvik.trainer.journal;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.media.j3d.Geometry;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.w3c.dom.Text;

public class JournalSVG extends JFrame implements MouseFocusListener{

	private static final long serialVersionUID = 1L;
	
	public final static long MILLISECOND	= 1;
	public final static long SECOND			= 1000 * MILLISECOND;
	public final static long MINUTE			= 60 * SECOND;
	public final static long HOUR			= 60 * MINUTE;
	public final static long DAY			= 24 * HOUR;
	public final static long WEEK			= 7 * DAY;
	public final static long MONTH			= 30 * DAY;
	public final static long YEAR			= 365 * DAY;
	
	private SVGEditablePanel panel;

	public JournalSVG( SVG svg, int a ) {
		setLayout( new BorderLayout() );
		panel = new SVGEditablePanel();
		add( new JScrollPane( panel ) );
		setSize( 800, 300);
		panel.setSVG( svg );
		panel.setSize(  svg.width.getPixels(), svg.height.getPixels() );
		setVisible( true );
		for (Geometry g: panel.getSVG().getItems()){
			if (g instanceof Circle){
				g.addMouseFocusListener( this );
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseOver(Geometry geometry) {
		if (geometry.description == null || geometry.description.getText().length() == 0){
//			panel.setToolTipText( null );
		} else {
			String text = geometry.description.getText();
			
			Text dateText = (Text) panel.getSVG().getElementById("tooltipDate");
			dateText.text = text.substring( 0, text.indexOf("|") );
			
			Text comments = (Text) panel.getSVG().getElementById("tooltipText");
			comments.text = text.substring( text.indexOf("|")+1 );
			
			Rectangle rect = (Rectangle) panel.getSVG().getElementById("tooltipBg");
			
			Circle circle = (Circle) panel.getSVG().getElementById("tooltipCircle");
			
			
			
			/* Move tool tip position */
			Point p = new Point( geometry.x.intValue(), geometry.y.intValue() );
			
			if (p.y > 125){
				/* Lower part of screen */
				p.y = p.y - 100;
				p.x = p.x - 200;
			} else {
				/* Upper part of screen */
			}
			
			rect.x = new Pixel( p.x );
			rect.y = new Pixel( p.y );
			
			dateText.x = new Pixel( p.x+100 );
			dateText.y = new Pixel( p.y+5 );
			
			comments.x = new Pixel( p.x+100 );
			comments.y = new Pixel( p.y+30 );
			
			circle.x = geometry.x;
			circle.y = geometry.y;
			
//			setTooltipVisible( true );

		}
	}

	public void mouseOut(Geometry geometry) {
		setTooltipVisible( false );
	}
	
	public void setTooltipVisible( boolean visible ){
		Group group = (Group) panel.getSVG().getElementById("tooltip");
		group.visibility = new Visibility( visible );
		panel.repaint();
	}
	
}