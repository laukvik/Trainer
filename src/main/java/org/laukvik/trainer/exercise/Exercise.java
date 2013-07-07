package org.laukvik.trainer.exercise;

import java.net.URL;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalManager;

public class Exercise {

	private long UID;
	private String name;
	private String notes;
	private Journal journal;
	private Muscle muscle;
	private int grid = 10;
	private ImageIcon startIcon, stopIcon , smallIcon;
	private boolean enabled = true;
	private long authorID;
	private URL originalIconURL;
	private String author;
	private URL authorURL;
	
	private URL youTubeURL;
	private boolean machine;
	private Locale locale;
	private Vector <Muscle> muscles = new Vector<Muscle>();
	private Vector <ExercisePhoto> photos = new Vector<ExercisePhoto>();
	
	public Exercise( long UID, String name, String notes, Muscle muscle, long authorID ){
		this.UID = UID;
		this.name = name;
		this.notes = notes;
		this.muscle = muscle;
		this.authorID = authorID;
	}
	
	public Exercise( String name, String notes, Muscle muscle, long authorID ){
		this( System.currentTimeMillis(), name, notes, muscle, authorID );
	}

	public Exercise( Muscle muscle, long authorID ) {
		this( "Select exercise...", "", muscle, authorID );
	}
	
	public boolean hasMuscle( Muscle muscle ){
		return muscles.indexOf( muscle ) > -1;
	}
	
	public void addMuscle( Muscle muscle ){
		if (hasMuscle( muscle )){
			/* Already exist */ 
		} else {
			muscles.add( muscle );
		}
	}
	
	public void removeMuscle( Muscle muscle ){
		muscles.remove( muscle );
	}
	
	public Muscle [] listMuscles(){
		Muscle [] ms = new Muscle[ muscles.size() ];
		muscles.toArray( ms );
		return ms;
	}
	
	
	
	
	public void addPhoto( ExercisePhoto exercisePhoto ){
		if (photos.indexOf( exercisePhoto ) > -1){
			/* Already exist */ 
		} else {
			photos.add( exercisePhoto );
		}
	}
	
	public void removeMuscle( ExercisePhoto exercisePhoto ){
		photos.remove( exercisePhoto );
	}
	
	public ExercisePhoto [] listPhotos(){
		ExercisePhoto [] ms = new ExercisePhoto[ photos.size() ];
		photos.toArray( ms );
		return ms;
	}
	
	
	
	/* Returns a global unique identifier for an exercise */
	public long getUID() {
		return UID;
	}
	
	public long getAuthorID() { return authorID; }


	
	public void setMuscle( Muscle muscle ) {
		this.muscle = muscle;
	}
	
	public Muscle getMuscle() {
		return muscle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	
	public Journal getJournal() {
		return journal;
	}
	
	public int getGrid() {
		return grid;
	}
	
	public String toString() {
		return getName();
	}

	public boolean sameAs( Exercise exercise ) {
		return this.UID == exercise.UID;
	}

	public ImageIcon getIcon() {
		return startIcon;
	}
	
	public void setIcon( ImageIcon icon, URL url ){
		setIcon( icon );
		setOriginalIcon( url );
	}

	public void setIcon( ImageIcon icon ){
		this.startIcon = icon;
		if (icon != null){
			try {
				setSmallIcon( JournalManager.getSmallIcon( getIcon())  );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setOriginalIcon( null );
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (journal != null){
			if (enabled){
				journal.fireExerciseEnabled( this );
			} else {
				journal.fireExerciseDisabled( this );
			}
			
		}
	}
	
	public void log( Object message ){
//		System.out.println( this.getClass().getName() + ": "+ message );
	}
	
	public void setOriginalIcon(URL url ) {
		this.originalIconURL = url;
		log( "OriginalURL: " + url );
	}
	
	public URL getOriginalIcon() {
		return originalIconURL;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public ImageIcon getStopIcon() {
		return stopIcon;
	}

	public ImageIcon getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(ImageIcon smallIcon) {
		this.smallIcon = smallIcon;
	}
	
	/**
	 * Sets the full name of the author
	 * 
	 * @param author
	 */
	public void setAuthor(String author) { 	this.author = author; }
	public String getAuthor() { return author; }
	
	/**
	 * Sets the URL of the authors homepage
	 * 
	 * @param authorURL
	 */
	public void setAuthorURL(URL authorURL) { this.authorURL = authorURL; }
	public URL getAuthorURL() { return authorURL;	}
	
	public void setLocale(Locale locale) { 	this.locale = locale; }
	public Locale getLocale() { return locale; }
	 
	public void setMachine(boolean machine) { this.machine = machine; }
	 
	public boolean isMachine() {
		return machine;
	}
	 
	public void setYouTubeURL(URL youTubeURL) {
		this.youTubeURL = youTubeURL;
	}
	 
	public URL getYouTubeURL() {
		return youTubeURL;
	}

}