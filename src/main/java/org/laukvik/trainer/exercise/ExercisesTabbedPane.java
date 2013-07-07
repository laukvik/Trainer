package org.laukvik.trainer.exercise;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Locale;
import java.util.Vector;

import javax.media.j3d.Geometry;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import org.laukvik.svg.Visibility;
import org.laukvik.svg.shape.Group;
import org.laukvik.svg.shape.MouseFocusListener;
import org.laukvik.svg.shape.SVG;
import org.laukvik.swing.locale.LocaleListener;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalExporter;
import org.laukvik.trainer.journal.JournalListener;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetListener;
import org.laukvik.trainer.set.SetSelectionListener;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutListener;
import org.w3c.dom.Text;

public class ExercisesTabbedPane extends JTabbedPane implements JournalListener, ExerciseListener, SetListener, SetSelectionListener, WorkoutListener, MouseFocusListener, LocaleListener{

	private static final long serialVersionUID = 1L;
	private Journal journal = new Journal();
	private Part part;
	private Vector <Exercise> exercises = new Vector<Exercise>();
	private Vector <ExerciseTab> tabs = new Vector<ExerciseTab>();
	private SVG svg;

	public ExercisesTabbedPane(){
		setLocale( JournalHelper.getDefaultLocale() );
		Font font = new Font( getFont().getName(), Font.PLAIN, 10 );
		setFont( font );	
		setTabLayoutPolicy( JTabbedPane.WRAP_TAB_LAYOUT );
		setTabPlacement( JTabbedPane.TOP );
		setBorder( null );
	}
	
	public void paint(Graphics g) {
//		System.out.println( getHeight()+ " " + getPreferredSize().height );
		if (exercises.size() == 0){
			Font font = new Font( getFont().getName(), Font.PLAIN, 16 );
			g.setFont( font );
			String partName = JournalHelper.getLanguage( journal.getLocale(), part.getName().toLowerCase().replaceAll(" ", "_") );
			String msg = JournalHelper.getLanguage( getLocale(), "exercises.notavailable", partName );
			int msgWidth = g.getFontMetrics().stringWidth( msg );
			int x = (getWidth()- msgWidth) / 2;
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.drawString( msg, x, getHeight() / 2 );
		} else {
			super.paint(g);	
		}
	}
	
	/**
	 * Change to use exercises for a different bodypart
	 * 
	 * @param part
	 */
	public void setPart( Part part ) {
		tabs.removeAllElements();
		exercises.removeAllElements();
		removeAll();
		this.part = part;
		setExercises( journal.getEnabledExercises( part.listAllMuscles() ) );
	}
	
	public Part getPart() {
		return part;
	}
	
	public void setExercises( Vector<Exercise> exercises ) {
		this.exercises = exercises;
		for (Exercise exercise : exercises){
			ExerciseTab tab = new ExerciseTab( exercise );
			tabs.add( tab );
			JScrollPane scroll = new JScrollPane( tab );
			scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
			
			add( scroll, exercise.getName() );
		}
		if (exercises.size() == 0){
			/* When no exercises - the component isnt repainted. Add
			 * repaint to see to "No exercises created yet". */
			repaint();	
		}
	}

	public void setSelectedIndex( int index ) {
		updateExercise( index );
		super.setSelectedIndex( index );
	}
	
	public void updateExercise( int index ){
		JournalExporter exporter = new JournalExporter( journal );
		if (index > -1){
			log( "updateExercise: " + index );
			Exercise exercise = exercises.get( index );
			ExerciseTab tab = tabs.get( index );
			
			svg = exporter.toSVG( exercise, 250 );
			for (Geometry g: svg.getItems()){
				if (g instanceof Circle){
					g.addMouseFocusListener( this );
				}
			}
			tab.setSVG( svg );
			setTitleAt( index, exercise.getName() );
		}
	}
	
	public void updateExercise( Exercise exercise ){
		updateExercise( exercises.indexOf( exercise ) );
	}
	
	public void updateSet( Set set ){
		updateWorkout( set.getWorkout() );
	}
	
	public void exerciseChanged( Exercise exercise ) {
		updateExercise( exercise );
		log( "exerciseChanged" );
	}
	
	public void updateWorkout( Workout workout ){
		if (getSelectedIndex() < 0){
			
		} else {
			Exercise exercise = exercises.get( getSelectedIndex() );
//			for (Set set : workout.getSets()){
//				if (set.getExercise().sameAs( exercise )){
////					exerciseChanged( exercise );
//				}
//			}	
			exerciseChanged( exercise );
			JScrollPane scroll = (JScrollPane) getComponent( getSelectedIndex() );
			scroll.setViewportView( tabs.get( getSelectedIndex() ) );
		}
	}


	public void journalChanged(Journal journal) {
	}

	public void journalLoaded(Journal journal) {
		this.journal = journal;
		setPart( Human.BODY.getArm() );
	}

	public void ownerChanged(Journal journal) {
	}

	public void exerciseAdded(Exercise exercise) {
		System.out.println( "exerciseAdded: " + exercise );
		ExerciseTab tab = new ExerciseTab( exercise );
		tabs.add( tab );
		exercises.add( exercise );
		add( new JScrollPane( tab ), exercise.getName() );
	}
	
	public void exerciseChangedMuscle(Exercise exercise, Muscle fromMuscle, Muscle toMusle) {
	}

	public void exerciseRemoved( Exercise exercise, Muscle muscle ) {
		int index = exercises.indexOf( exercise );
		if (index > -1){
			exercises.remove( index );
			tabs.remove( index );
			remove( index );
		}
	}

	public void setAdded(Set set) {
	}

	public void setChanged(Set set) {
		updateSet( set );
	}

	public void setRemoved(Set set) {
		updateSet( set );
	}

	public void setsRemoved(Set[] set) {
	}

	public void noSetsSelected() {
	}

	public void setSelectedSet(Set set) {
	}

	public void setsSelectedSets(Set[] sets) {
	}
	
	public void log( Object message ){
//		System.out.println( ExercisesTabbedPane.class.getName() + ": " + message );
	}

	public void workoutAdded(Workout workout) {
		updateWorkout( workout );
	}

	public void workoutChanged(Workout workout) {
		updateWorkout( workout );
	}

	public void workoutDateChanged(Workout workout) {
		updateWorkout( workout );
	}

	public void workoutNotesChanged(Workout workout) {
		updateWorkout( workout );
	}

	public void workoutOrderChanged() {
	}

	public void workoutRemoved(Workout workout) {
		updateWorkout( workout );
	}

	public void workoutsRemoved(Workout[] workouts) {
		for (Workout workout : workouts){
			updateWorkout( workout );
		}
	}

	public void mouseOver(Geometry geometry) {
		if (geometry.description == null || geometry.description.getText().length() == 0){
		} else {
			
			String text = geometry.description.getText();
//			System.out.println( text );
			Text dateText = (Text) svg.getElementById("tooltipDate");
			dateText.text = text.substring( 0, text.indexOf("|") );
			Text comments = (Text) svg.getElementById("tooltipText");
			comments.text = text.substring( text.indexOf("|")+1 );
			Rectangle rect = (Rectangle) svg.getElementById("tooltipBg");
			Circle circle = (Circle) svg.getElementById("tooltipCircle");			
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
			dateText.y = new Pixel( p.y+10 );
			comments.x = new Pixel( p.x+100 );
			comments.y = new Pixel( p.y+30 );
			circle.x = geometry.x;
			circle.y = geometry.y;
			setTooltipVisible( true );
		}
	}

	public void mouseOut(Geometry geometry) {
		setTooltipVisible( false );
	}
	
	public void setTooltipVisible( boolean visible ){
		Group group = (Group) svg.getElementById("tooltip");
		group.visibility = new Visibility( visible );
		getSelectedComponent().repaint();
	}

	public void exerciseDisabled(Exercise exercise) {
		exerciseRemoved( exercise, null );
		updateExercise( getSelectedIndex() );
	}

	public void exerciseEnabled(Exercise exercise) {
		exerciseAdded(exercise);
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		updateExercise( getSelectedIndex() );
		if (getSelectedComponent() != null){
			getSelectedComponent().getParent().repaint();
		}
		
	}

    public void mouseOut(org.laukvik.svg.shape.Geometry geometry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseOver(org.laukvik.svg.shape.Geometry geometry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}