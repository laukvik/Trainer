package org.laukvik.trainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class ImageBadgeIcon extends ImageIcon {

	private static final long serialVersionUID = -447987817774055745L;
	private String text;
	public final static Font badgeFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.BOLD, 9 );

	public ImageBadgeIcon(URL url){
		super( url );
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		super.paintIcon(c, g, x, y);
		if (text != null){
			/* Get 2D to use anti aliasing*/
			Graphics2D g2 = (Graphics2D) g;
			
			/* Save paint settings so we can restore them later */
			Font font = g.getFont();
			Color color = g.getColor();
			Object hint = g2.getRenderingHint( RenderingHints.KEY_ANTIALIASING );
			
			/* Calculate position of badge */
//			int charCount = (""+text).length(); // The length of text
//			int charWidth = 7; // Each char occupies 10 pixels
//			int textWidth = charCount*charWidth;
			int textWidth = g2.getFontMetrics().stringWidth( text );
//			if (textWidth < 10){ textWidth = 10; } // The box MUST be 10 or bigger
			int arcSize = 10;
			int boxWidth = textWidth;
			int boxX = x + getIconWidth() - boxWidth; //

			int textX = boxX;
			
			
			
			
			
//			System.out.println( "Text: " + text + " charCount:"+ charCount + " charWidth: " + charWidth + " BoxWidth: " + boxWidth + " boxX: " + boxX );
			
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setFont( badgeFont );
			g2.setColor( Color.RED );

			g2.fillRoundRect( boxX-3, 0, boxWidth+3*2, 13, arcSize, arcSize );
//			g2.fillRoundRect( 0, 0, 10, 13, arcSize, arcSize );
			g2.setColor( Color.WHITE );
			g2.drawString( text, textX, 10 );	 // +4
			
			/* Restore back to normal paint settings */
			g2.setFont( font );
			g2.setColor( color );
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, hint );
		}
	}
	
}