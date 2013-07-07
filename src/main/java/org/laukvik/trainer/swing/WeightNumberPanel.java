package org.laukvik.trainer.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.UIManager;

public class WeightNumberPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	public static final Color weightFgColor = new Color( 0, 0, 0 );
	public static final Color weightBgColor = UIManager.getColor("Panel.background");
	public final static Font weightFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 9 );

	public WeightNumberPanel(){
		super();
	}
	
	public int getStringWidth( String s, Font font ){
		return getGraphics().getFontMetrics(font).stringWidth( s );
	}
	
	public int getStringWidth( String s ){
		return getStringWidth( s, weightFont );
	}
	
	public void paint( Graphics g){
		super.paint(g);
		g.setColor( weightBgColor );
		g.fillRect( 0,0, getWidth(), getHeight() );
		g.setColor( weightFgColor );
		g.setFont( weightFont );
		/* Draw weight */
		int fifty = 0;
		int y = 1;
		while (y > 0 && fifty < 1000){
//		for (int fifty=0; fifty<10; fifty++){
			String fiftyString = fifty * 50 + " kg";
			int fiftyWidth = getStringWidth( fiftyString );
			int x = 60-fiftyWidth;
			y = getHeight()- fifty*50 + 4;
			g.drawString( fiftyString, x-20, y );
			fifty++;
		}
	}

}