package org.laukvik.trainer.workout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.UIManager;
import org.laukvik.trainer.set.Set;

public class WorkoutTableCell extends JLabel {

	private static final long serialVersionUID = 1L;
	private Workout workout;
	private boolean isSelected;
	public final static Font setFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 10 );
	public final static Font dayFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 12 );
	public final static Font monFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 8 );
	public final static Font yearFont = new Font( UIManager.getFont("Label.font").getFontName(), Font.PLAIN, 7 );
	public final static Font aerobicFontO = new Font( UIManager.getFont("Label.font").getFontName(), Font.BOLD, 12 );
	public final static Font aerobicFont2 = new Font( UIManager.getFont("Label.font").getFontName(), Font.BOLD, 8 );
	
	public final static Color bgColor 	= Color.WHITE;
	public final static Color setColor 	= Color.BLACK;
	public final static Color dayColor 	= new Color(255, 105, 0);
	public final static Color monColor 	= Color.GRAY;
	public final static Color yearColor = new Color( 180, 180, 180 );
	public final static Color aerobicColor = new Color( 80, 130, 220 );
	
	public final static Color bgSelectedColor 	= new Color( 220,225,232 );
	public final static Color setSelectedColor 	= Color.WHITE;
	public final static Color daySelectedColor = new Color(255, 105, 0);
	public final static Color monSelectedColor = Color.WHITE;
	public final static Color yearSelectedColor= new Color( 180, 180, 180 );
	public final static Color aerobicSelectedColor = Color.BLACK;
	
	public final static int PADDING = 5;

	public WorkoutTableCell(){
		super();
	}
	
	public void setWorkout( Workout workout ) {
		this.workout = workout;
	}
	
	public Workout getWorkout() {
		return workout;
	}
	
	public int getStringWidth( String s, Font font ){
		return getGraphics().getFontMetrics(font).stringWidth( s );
	}
	
	public int getStringHeight( String s, Font font ){
		return getGraphics().getFontMetrics(font).getHeight();
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
//		g.setColor( isSelected() ? bgSelectedColor : bgColor );
		g.setColor( getBackground() );
		g.fillRect( 0,0, getWidth(), getHeight() );
		
		
		int split = 50;
		
		/* Draw month */
		g.setFont( monFont );
		g.setColor( isSelected() ? monSelectedColor : monColor );
		int month = getWorkout().getCalendar().get(  GregorianCalendar.MONTH );
		String monthString = CalendarHelper.listMonths( getLocale() )[ month ];
		int monthPos = (split - getStringWidth( monthString, monFont )) / 2;
		g.drawString( monthString, monthPos, 9 );
		
		/* Draw day */
		g.setFont( dayFont );
		g.setColor( isSelected() ? daySelectedColor : dayColor );
		String dateString = getWorkout().getCalendar().get(  GregorianCalendar.DAY_OF_MONTH )+"";
		int dayPos = (split - getStringWidth( dateString, dayFont )) / 2;
		g.drawString( dateString, dayPos, 20 );

		/* Draw year */
		g.setFont( yearFont );
		g.setColor( isSelected() ? yearSelectedColor : yearColor );
		String yearString = getWorkout().getCalendar().get(  GregorianCalendar.YEAR ) + "";
		int yearPos = (split - getStringWidth( yearString, yearFont )) / 2;
		g.drawString( yearString, yearPos, 28 );

		/* Draw sets */
		g.setFont( setFont );
		g.setColor( isSelected() ? setSelectedColor : setColor );
		int y = 0;
		for (Set set : getWorkout().getSets()){
			if (set.getText() != null){
				g.drawString( set.getText(), split, (y)*getRowSize() + PADDING + setFont.getSize() );
			}
			
			y++;
		}
		
		/* Draw oxygen symbol when activity is present */
		if (getWorkout().getRunning().getDistance() > 0 || getWorkout().getBiking().getDistance() > 0  ||
			getWorkout().getWalking().getDistance() > 0 || getWorkout().getSwimming().getDistance() > 0 ){

			g.setColor( isSelected() ? aerobicSelectedColor : aerobicColor );

			g.setFont( aerobicFontO );
			g.drawString( "O", 2, 20 );
			
			g.setFont( aerobicFont2 );
			g.drawString( "2", 10, 23 );
		}
		
	
	}
	
	public final static int getRowSize(){
		return setFont.getSize() + 2;
	}

	public final static int getPadding(){
		return PADDING;
	}
	
}