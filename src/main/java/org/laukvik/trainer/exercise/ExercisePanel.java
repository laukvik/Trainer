package org.laukvik.trainer.exercise;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.UIManager;

import org.laukvik.trainer.swing.JournalHelper;

public class ExercisePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Exercise exercise;
	public final static int REQUIRED_HEIGHT = 58;
	public boolean isFirstInGroup = false;
	public Color disabledColor = UIManager.getColor("Button.disabledText");

	public ExercisePanel() {
		setPreferredSize( new Dimension(300,REQUIRED_HEIGHT) );
	}
	
	public void setExercise( Exercise exercise, boolean isFirstInGroup ){
		this.exercise = exercise;
		this.isFirstInGroup = isFirstInGroup;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (exercise == null){
			
		} else {
			Graphics2D g2 = (Graphics2D) g;
			Color orgCol = g2.getColor();
			
			if (exercise.isEnabled()){
				g2.setComposite( AlphaComposite.Src );
			} else {
				g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			}
			
			if (isFirstInGroup){
				
			}
//			g2.setColor( Color.RED );
//			g2.fillRect( 0,0, getWidth(), 1 );
			
			if (exercise.getSmallIcon() != null){
				g2.drawImage( exercise.getSmallIcon().getImage(), 4, 4, this );
			} else {
				
				g2.setColor( Color.WHITE );
				g2.fillRect( 4,4, 50,50 );
				g2.setColor( orgCol );
			}
			
			Font org = g2.getFont();
			
			g2.setFont( new Font(org.getName(), Font.BOLD, 13 ) );
			g2.drawString( exercise.getName(), 70, 24 );
			
			
			g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2.setFont( new Font(org.getName(), Font.PLAIN, 10 ) );
			
			if (exercise.getJournal() != null){
				Locale locale = exercise.getJournal().getLocale();
				String muscle = exercise.getMuscle().getLatin().toLowerCase().replaceAll(" ", ".");
				String part = exercise.getMuscle().getPart().getName().toLowerCase().replaceAll(" ", ".");
				
				String s = JournalHelper.getLanguage( locale, muscle );
				String s2 = JournalHelper.getLanguage( locale, part );
				
				if (s.equalsIgnoreCase( s2 )){
					g2.drawString( s, 70, 38 );
				} else {
					g2.drawString( s2 + " (" + s + ")", 70, 38 );
				}
//				
				
			}

		}

	}

}