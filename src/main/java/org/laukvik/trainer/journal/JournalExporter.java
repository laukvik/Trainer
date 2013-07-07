package org.laukvik.trainer.journal;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.beans.Visibility;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.UIManager;
import org.laukvik.svg.*;
import org.laukvik.svg.shape.*;
import org.laukvik.svg.stroke.*;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;

public class JournalExporter {
	
	public final static long MILLISECOND	= 1;
	public final static long SECOND			= 1000 * MILLISECOND;
	public final static long MINUTE			= 60 * SECOND;
	public final static long HOUR			= 60 * MINUTE;
	public final static long DAY			= 24 * HOUR;
	public final static long WEEK			= 7 * DAY;
	public final static long YEAR			= 365 * DAY;

	Exercise exercise;
	Journal journal;
	
	GregorianCalendar start;
	GregorianCalendar end;
	
	long millisPrPixel = DAY / 3;
	
	Format formatter;
	
	public JournalExporter( Journal journal  ){
		this.journal = journal;
		formatter = new SimpleDateFormat("dd. MMMM yyyy", journal.getLocale() );
	}
	
	/**
	 * Sets the number of milliseconds required to be 1 pixel with on the graph
	 * 
	 * @param milliseconds
	 */
	public void setMillisecondsPrPixel( long milliseconds ) {
		this.millisPrPixel = milliseconds;
	}
	
	/**
	 * Returns the name of the month with the specified index. 
	 * January is 0 and December is 11
	 * 
	 * @param index
	 * @return
	 */
	public String getMonth( int index ){
		if (index < 12){
			return CalendarHelper.listMonths( journal.getLocale() )[ index ];
		} else {
			return CalendarHelper.listMonths( journal.getLocale() )[ index % 12 ];	
		}
	}
	
	/**
	 * Creates an empty SVG to use as an empty graph
	 * 
	 * @return
	 */
	public SVG emptyLowerHorisontalStatusScrollbar(){
		
		int headingHeight = 16;
		
		SVG svg = new SVG();
		svg.width = new Pixel( toPixels(end) );
		svg.height = new Pixel( headingHeight );
		
		Rectangle bg = new Rectangle( new Percent(0), new Pixel(0), new Percent(100), new Percent( 100 ) );
		bg.fill.color = new SVGColor(UIManager.getColor("Button.shadow"));
		svg.add( bg );

		return svg;
	}
	
	public SVG emptyUpperHorisontalStatusScrollbar(){
		
		int headingHeight = 30;
		
		SVG svg = new SVG();
		svg.width = new Pixel( toPixels(end) );
		svg.height = new Pixel( headingHeight );
		
		/* Create a header gradient */
		LinearGradient headingGradient = new LinearGradient( new Pixel(0), new Pixel(0), new Pixel(0), new Pixel(headingHeight) );
		headingGradient.id = new SVGID( "headingGradient" );
		headingGradient.spreadMethod = SpreadMethod.PAD;
		headingGradient.setSVG( svg );
		headingGradient.add( new Stop( new Unit(0), new SVGColor(UIManager.getColor("Button.background")) ));
		headingGradient.add( new Stop( new Percent(100), new SVGColor(UIManager.getColor("Button.shadow"))  ));
		svg.addDefinition( headingGradient );
		
		Rectangle bg = new Rectangle( new Percent(0), new Pixel(0), new Percent(100), new Percent( 100 ) );
		bg.fill.setDefinitionID( headingGradient.id, svg );
		svg.add( bg );
		
		/* Add a vertical border line */
		svg.add( new Line( new Pixel(149), new Pixel(0), new Pixel(149), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(1) ) );
		/* Add a horisontal border line */
		svg.add( new Line( new Pixel(0), new Pixel(headingHeight-1), new Percent(100), new Pixel(headingHeight-1), SVGColor.black, new Pixel(1), new Opacity(1) ) );
		
		return svg;
	}
	
	public SVG horisontalStatusScrollbar(){
		/* Create start and stop dates based the workouts in the journal */
		this.start = new GregorianCalendar( journal.getStart().get( Calendar.YEAR ), 0, 1 );
		this.end = new GregorianCalendar( journal.getEnd().get( Calendar.YEAR ), 0, 1 );
		end.add( Calendar.YEAR, 1 );
		
		int headingHeight = 30;
		
		SVG svg = new SVG();
		svg.width = new Pixel( toPixels(end) );
		svg.height = new Pixel( headingHeight );
		
		/* Create a header gradient */
		LinearGradient headingGradient = new LinearGradient( new Pixel(0), new Pixel(0), new Pixel(0), new Pixel(headingHeight) );
		headingGradient.id = new SVGID( "headingGradient" );
		headingGradient.spreadMethod = SpreadMethod.PAD;
		headingGradient.setSVG( svg );
		headingGradient.add( new Stop( new Unit(0), new SVGColor(UIManager.getColor("Button.background")) ));
		headingGradient.add( new Stop( new Percent(100), new SVGColor(UIManager.getColor("Button.shadow"))  ));
		svg.addDefinition( headingGradient );
		
		Rectangle bg = new Rectangle( new Percent(0), new Pixel(0), new Percent(100), new Percent( 100 ) );
		bg.fill.setDefinitionID( headingGradient.id, svg );
		svg.add( bg );
		
		/* Draw grid */
		GregorianCalendar grid = new GregorianCalendar();
		grid.setTimeInMillis( start.getTimeInMillis() );
		
		/* Create the date for february to get the pixel width of a month */
		GregorianCalendar january = new GregorianCalendar();
		january.setTimeInMillis( start.getTimeInMillis() );
		january.add( Calendar.MONTH, 1 );
		int monthPixels = toPixels( january );
		
		/* Draw a bottom border line */
		svg.add( new Line( new Pixel(0), new Pixel(headingHeight-1), new Percent(100), new Pixel(headingHeight-1), SVGColor.black, new Pixel(1), new Opacity(1) ) );
		
		while (grid.getTimeInMillis() < end.getTimeInMillis()){
			/* Get the pixel position for the workout */
			int x = toPixels( grid );

			/* Draw the vertical lines */
			if (grid.get( Calendar.MONTH ) == Calendar.JANUARY){
				svg.add( new Line( new Unit(x), new Unit(0), new Unit(x), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(0.5f) ) );	
			} else {
				svg.add( new Line( new Unit(x), new Unit(0), new Unit(x), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(0.1f) ) );
			}
			
			/* Draw the name of the month */
			String monthName = getMonth( grid.get( Calendar.MONTH ) );
			svg.add( new Text( new Pixel( x + (monthPixels/2) ), new Pixel(7), monthName, TextAnchor.MIDDLE, new Font(new Pixel(10) ), SVGColor.black, new Opacity(0.8f)  ) );
			
			/* Draw the year number */
			if (grid.get( Calendar.MONTH ) == Calendar.JULY){
				String yearName = grid.get( Calendar.YEAR ) + "";
				svg.add( new Text( new Pixel(x ), new Pixel(26), yearName, TextAnchor.MIDDLE, new Font(new Pixel(30) ), SVGColor.black, new Opacity(0.7f)   ) );
			}
			
			/* Go to next month */
			grid.add( Calendar.MONTH, 1 );
		}
		
		return svg;
	}
	

	/**
	 * Creates an SVG with all status names to use as legends
	 * 
	 * @return
	 */
	public SVG verticalStatusScrollbar() {
		SVG svg = new SVG();
		svg.width = new Pixel( 150 );
		svg.height = new Pixel( 1000 );
		
		int statusHeight = 50;
				
		Rectangle headerBG = new Rectangle( new Percent(0), new Unit(0), new Percent(100), new Percent(100) );
		headerBG.fill.color = new SVGColor(UIManager.getColor("Button.shadow"));
		svg.add( headerBG );
	
		for (int y=0; y<13; y++){
			int rowLocation = y*statusHeight;
			svg.add( new Line( new Pixel(0), new Pixel(rowLocation), new Percent(100), new Pixel( rowLocation ), SVGColor.black, new Pixel(1), new Opacity(0.1f) ) );
			Text text = new Text( new Pixel(145), new Pixel(rowLocation + statusHeight/2 ), JournalHelper.getLanguage( journal.getLocale(), Status.TYPES[ y ].toLowerCase()) );
			text.anchor = TextAnchor.END;
			text.baselineAlignment = BaselineAlignment.MIDDLE;
			svg.add( text );
			
			
			Text p50 = new Text( new Pixel(3), new Pixel(rowLocation+3), "+50%" );
//			p50.anchor = TextAnchor.END;
			p50.opacity = new Opacity( 0.4f );
			p50.baselineAlignment = BaselineAlignment.TOP;
			p50.font.size = new Pixel( 10 );
			p50.fill.color = SVGColor.white;
			svg.add( p50 );
			
			Text m50 = new Text( new Pixel(3), new Pixel(rowLocation+statusHeight-3), "-50%" );
//			m50.anchor = TextAnchor.END;
			m50.opacity = new Opacity( 0.4f );
			m50.baselineAlignment = BaselineAlignment.BOTTOM;
			m50.font.size = new Pixel( 10 );
			m50.fill.color = SVGColor.black;
			svg.add( m50 );
			
		}

		svg.add( new Line( new Pixel(149), new Pixel(0), new Pixel(149), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(1) ) );
		
		return svg;
	}
	
	public SVG statusToSVG( ){
		/* Create start and stop dates based the workouts in the journal */
		this.start = new GregorianCalendar( journal.getStart().get( Calendar.YEAR ), 0, 1 );
		this.end = new GregorianCalendar( journal.getEnd().get( Calendar.YEAR ), 0, 1 );
		end.add( Calendar.YEAR, 1 );
		
		int statusHeight = 50;
		
		SVG svg = new SVG();
		svg.width = new Pixel( toPixels(end) );
		svg.height = new Pixel( statusHeight*13 );
		svg.height = Percent.PERCENT100;
		
		/* Create a graph gradient */
		LinearGradient gradient = new LinearGradient( new Pixel(0), new Pixel(0), new Pixel(0), new Pixel(statusHeight/2) );
		gradient.id = new SVGID( "gradient" );
		gradient.spreadMethod = SpreadMethod.PAD;
		gradient.setSVG( svg );
		gradient.add( new Stop( new Unit(0), SVGColor.red ));
		gradient.add( new Stop( new Percent(100), SVGColor.green  ));
		svg.addDefinition( gradient );

		Rectangle bg = new Rectangle( new Pixel(0), new Pixel(0), new Percent(100), new Percent( 100 ) );
		bg.fill.color = new SVGColor(UIManager.getColor("Button.shadow"));
		svg.add( bg );
		
		/* Draw grid */
		GregorianCalendar grid = new GregorianCalendar();
		grid.setTimeInMillis( start.getTimeInMillis() );
		
		/* Create the date for February to get the pixel width of a month */
		GregorianCalendar january = new GregorianCalendar();
		january.setTimeInMillis( start.getTimeInMillis() );
		january.add( Calendar.MONTH, 1 );
				
		while (grid.getTimeInMillis() < end.getTimeInMillis()){
			/* Get the pixel position for the workout */
			int x = toPixels( grid );

			/* Draw the vertical lines */
			if (grid.get( Calendar.MONTH ) == Calendar.JANUARY){
				svg.add( new Line( new Unit(x), new Unit(0), new Unit(x), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(0.5f) ) );	
			} else {
				svg.add( new Line( new Unit(x), new Unit(0), new Unit(x), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(0.1f) ) );
			}
			
			/* Go to next month */
			grid.add( Calendar.MONTH, 1 );
		}
		
		/* Draw each separator line  */
		for (int y=0; y<13; y++){
			int rowLocation = y*statusHeight;
			svg.add( new Line( new Pixel(0), new Pixel(rowLocation), new Percent(100), new Pixel( rowLocation ), SVGColor.black, new Pixel(1), new Opacity(0.1f) ) );
			
			/* Draw the origo line aka first status */
			svg.add( new Line( new Pixel(0), new Pixel(rowLocation+statusHeight/2), new Percent(100), new Pixel( rowLocation+statusHeight/2 ), SVGColor.white, new Pixel(1), new Opacity(0.2f) ) );
		}
		
		/* Create a HashMap of all graphs */
		HashMap<String, Polyline> map = new HashMap<String, Polyline>();
		int index = 0;
		for (String name : Status.TYPES){
			/* Create a poly line object which will contain the graph */
			Polyline poly = new Polyline();
//			poly.fill.setDefinitionID( gradient.id, svg );
			poly.fill.color = SVGColor.white;
				
			/**
			 * 		gradient.add( new Stop( new Unit(0), new SVGColor("#ffe43e") ) );
		gradient.add( new Stop( new Percent(100), new SVGColor("#ff6900") ) );
			 */
				
			poly.stroke.width = new Pixel( 2 );
			poly.stroke.lineJoin = LineJoin.ROUND;
			poly.description = new Description( index + "");
			map.put( name, poly );
			svg.add( poly );
			index++;
		}
		
		/* Calculate MIN and MAX statuses */
		journal.sortStatuses();
		Status first = journal.getStatuses().get( 0 );
		Status max = new Status();
		Status min = new Status();
		for (Status s : journal.getStatuses()){
			for (String name : Status.TYPES){
				float v = s.getValue( name );
				
				float minV = min.getValue( name );
				if (v < minV){
					min.setValue(name, minV );
				}
				
				float maxV = max.getValue( name );
				if (v > maxV){
					max.setValue(name, maxV );
				}
				
			}
		}
		
		/* Calculate average values */
		Status avg = new Status();
		for (String name : Status.TYPES){
			float minV = max.getValue(name);
			float maxV = max.getValue(name);
			avg.setValue( name, (maxV-minV) / 2 );
		}
		
		/* Create the graph */
		for (Status s : journal.getStatuses()){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis( s.getMilliseconds() );
			/* Create coordinates in time for each status */
			int px = toPixels( cal );
			for (String name : Status.TYPES){
				/* Get value for status */
				Number y = s.getValue( name );
				
				/* Get the very first recorded value */
				float firstY = first.getValue(name);
				
				/* Get the graph index */
				int graphIndex = Integer.parseInt( map.get(name).description.getText() );
				
				/* Calculate origo vertical position */
				float origoY = graphIndex*statusHeight + statusHeight/2;
				
				/* Get difference from first time to this one in percent */
				float deltaY = 100*(y.floatValue() - firstY) / firstY;
				
				/* Calculate y =  */
				float py = origoY - (  ( (statusHeight/2) * deltaY) / 100 );
				
//				if (name.equalsIgnoreCase( "bodyFat" )){
//					System.out.println( "y=" + y + " firstY=" + firstY + " deltaY=" + deltaY + " py=" + py );
//				}
				
				/* Add coordinate */
				map.get( name ).add( new Coordinate( px, py ) );
			}
		}
		
		return svg;
	}

	
	/**
	 * Creates an SVG compatible graph for the specified exercise
	 * 
	 * @param exercise
	 * @param height
	 * @return
	 */
	public SVG toSVG( Exercise exercise, int height ){
		/* Create start and stop dates based the workouts in the journal */
		this.start = new GregorianCalendar( journal.getStart().get( Calendar.YEAR ), 0, 1 );
		this.end = new GregorianCalendar( journal.getEnd().get( Calendar.YEAR ), 0, 1 );
		end.add( Calendar.YEAR, 1 );
		
		SVG svg = new SVG();
		
		/* Setup scale of SVG */	
		svg.width = new Pixel( toPixels(end) );
		svg.height = new Pixel( height );
		svg.height = new Percent( 100 );
		
		/* Create a gradient */
		LinearGradient gradient = new LinearGradient( new Pixel(0), new Pixel(0), new Pixel(0), new Pixel(height) );
		gradient.id = new SVGID( "gradient" );
		gradient.spreadMethod = SpreadMethod.REPEAT;
		gradient.setSVG( svg );
		gradient.add( new Stop( new Unit(0), new SVGColor("#ffe43e") ) );
		gradient.add( new Stop( new Percent(100), new SVGColor("#ff6900") ) );
		svg.addDefinition( gradient );
		
		/* Create a gradient */
		LinearGradient intensityGradient = new LinearGradient( new Pixel(0), new Pixel(0), new Pixel(0), new Pixel(height) );
		intensityGradient.id = new SVGID( "intensityGradient" );
		intensityGradient.spreadMethod = SpreadMethod.REPEAT;
		intensityGradient.setSVG( svg );
		intensityGradient.add( new Stop( new Unit(0), SVGColor.red ) );
		intensityGradient.add( new Stop( new Percent(100), SVGColor.blue ) );
		svg.addDefinition( intensityGradient );
		
		
		/* Setup a rectangle which will fill the entire canvas */
		Rectangle bg = new Rectangle( new Percent(0), new Unit(0), new Percent(100), new Percent(100) );
		bg.fill.color = SVGColor.aliceblue;
		bg.fill.setDefinitionID( gradient.id, svg );
		svg.add( bg );

		
		/* Draw grid */
		GregorianCalendar grid = new GregorianCalendar();
		grid.setTimeInMillis( start.getTimeInMillis() );
		
		/* Create the date for february to get the pixel width of a month */
		GregorianCalendar january = new GregorianCalendar();
		january.setTimeInMillis( start.getTimeInMillis() );
		january.add( Calendar.MONTH, 1 );
		int monthPixels = toPixels( january );
			
		while (grid.getTimeInMillis() < end.getTimeInMillis()){
			/* Get the pixel position for the workout */
			int x = toPixels( grid );

			/* Draw the vertical lines */
			if (grid.get( Calendar.MONTH ) == Calendar.JANUARY){
				svg.add( new Line( new Pixel(x-1), new Pixel(0), new Pixel(x-1), new Percent(100), SVGColor.black, new Pixel(3), new Opacity(0.5f) ) );	
			} else {
				svg.add( new Line( new Pixel(x), new Pixel(0), new Unit(x), new Percent(100), SVGColor.black, new Pixel(1), new Opacity(0.1f) ) );
			}
			
			/* Draw the name of the month */
			String monthName = getMonth( grid.get( Calendar.MONTH ) );
			svg.add( new Text( new Pixel( x + (monthPixels/2) ), new Pixel(25), monthName, TextAnchor.MIDDLE, new Font(new Pixel(10) ), SVGColor.black, new Opacity(0.8f)  ) );
			
			/* Draw the year number */
			if (grid.get( Calendar.MONTH ) == Calendar.JULY){
				String yearName = grid.get( Calendar.YEAR ) + "";
				svg.add( new Text( new Pixel(x ), new Pixel(100), yearName, TextAnchor.MIDDLE, new Font(new Pixel(50) ), SVGColor.black, new Opacity(0.2f)   ) );
			}
			
			/* Go to next month */
			grid.add( Calendar.MONTH, 1 );
		}

		
		/* Create a polyline object which will contain the graph */
		Polyline p = new Polyline();
		p.fill.color = SVGColor.white;
		p.stroke.width = new Pixel( 4 );
		p.stroke.lineJoin = LineJoin.ROUND;
		
//		float max = 73;
		
		/* Create the graph */
		for (Workout w : journal.getWorkouts()){
			Set set = w.getMaximumWeightSet( exercise );
			if (set == null){
				/* No set was found for this exercise */
			} else {
				/* Found the heaviest set */ 
				
				/* Create coordinates in time for workout */
				int x = toPixels( w.getCalendar() );
				Number y = height - set.getWeight();
				
				/* Create graph for workout */
				p.add( new Coordinate( x, y.floatValue() ) );
				
				Circle circle = new Circle( new Pixel(x), new Pixel( y.intValue() ), new Pixel(4), SVGColor.aliceblue );
				circle.description = new Description( formatDate( w.getCalendar() ) + "|" + w.getNotes()  );
				circle.cursor = Cursor.POINTER;
				circle.fill.color = SVGColor.red;
				svg.add( circle );
				
				/* Add weight text for the set */
				String weight = (int)set.getWeight()+"";
				Text text = new Text( new Pixel( x ), new Pixel(y.intValue()-25), weight, TextAnchor.MIDDLE, new Font(new Pixel(9) ), SVGColor.black, new Opacity(0.8f)  ); 
				
				svg.add( text );
				
				/* Create graph for intensity */
//				float intensityPercent = (float) (( set.getWeight() / max ) *100);
//				if (intensityPercent > 70){
//					svg.add( new Line( x, y.intValue(), x, (int) (y.intValue()-intensityPercent/5), SVGColor.red, 3 ) );
//				} else {
//					svg.add( new Line( x, y.intValue(), x, (int) (y.intValue()+intensityPercent/5), SVGColor.blue, 3 ) );
//				}
				
			}
		}
		
		svg.add( p );
		
		Group tooltip = new Group();
		tooltip.setID( new SVGID("tooltip") );
		tooltip.visibility = Visibility.HIDDEN;
		Rectangle tooltipBg = new Rectangle( new Pixel(0), new Pixel(0), new Pixel(200), new Pixel(100) );
		tooltipBg.setID( new SVGID("tooltipBg") );
		tooltipBg.fill.opacity = new Opacity( (float) 0.7 );
		tooltipBg.rx = new Pixel(10);
		tooltipBg.ry = new Pixel(10);
		tooltipBg.stroke.width = new Pixel(1);
		tooltipBg.stroke.color = SVGColor.black;
		
		tooltip.add( tooltipBg );
		Text tooltipDate = new Text( new Pixel(100), new Pixel(5), "???DATE???" );
		tooltipDate.setID( new SVGID("tooltipDate") );
		tooltipDate.anchor = TextAnchor.MIDDLE;
		tooltipDate.fill.color = SVGColor.white;
		tooltipDate.font.size = new Pixel( 14 );
		tooltip.add( tooltipDate );
		
		Text tooltipText = new Text( new Pixel(20), new Pixel(30), "???TEXT???" );
		tooltipText.setID( new SVGID("tooltipText") );
		tooltipText.font.size = new Pixel( 11 );
		tooltipText.fill.color = SVGColor.gray;
		tooltipText.width = new Pixel( 200 );
		tooltipText.height = new Pixel( 70 );
		tooltipText.anchor = TextAnchor.MIDDLE;
		tooltip.add( tooltipText );
		
		Circle tooltipCircle = new Circle( new Pixel(0), new Pixel(0), new Pixel(5) );
		tooltipCircle.setID( new SVGID("tooltipCircle") );
		tooltipCircle.stroke.color = SVGColor.black;
		tooltipCircle.stroke.width = new Pixel( 2 );
		tooltipCircle.fill.opacity = Opacity.TRANSPARENT;
		tooltip.add( tooltipCircle );

		svg.add( tooltip );
		
		return svg;
	}
	
	public String formatDate( GregorianCalendar calendar ){
//		w.getCalendar().get( Calendar.DAY_OF_MONTH ) + ". ";
//		return "15. april 2008";
		return formatter.format( calendar.getTime()  );
	}
	
	public int toPixels( GregorianCalendar time ){
		return (int) ((time.getTimeInMillis() - start.getTimeInMillis()) / millisPrPixel);
	}
	
	public static void main( String [] args ){
		GregorianCalendar cal = new GregorianCalendar(2007,0,1);
		for (int x=0; x<6; x++){
			System.out.println( cal.getTimeInMillis() );
			cal.add( GregorianCalendar.WEEK_OF_YEAR, 1 );
		}
		
	}

}