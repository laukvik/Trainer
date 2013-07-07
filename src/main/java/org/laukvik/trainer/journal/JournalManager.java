package org.laukvik.trainer.journal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.trainer.aerobic.Aerobic;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.exercise.ExercisePhoto;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.units.Distance;
import org.laukvik.trainer.units.Weight;
import org.laukvik.trainer.workout.Workout;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JournalManager {
	
	public final static String JOURNAL_FILENAME = "Trainer.preferences";
	private Vector<JournalReadWriteListener> listeners;
	private final static String SHOP_XML = "file:///Users/morten/Projects/Research/src/org/laukvik/trainer/shop/TrainerShop.xml";
	private final static String SHOP_HTML = "file:///Users/morten/Projects/Research/src/org/laukvik/trainer/shop/TrainerShop.html";
	private final static String PUBLISH_EXERCISE_URL = "http://morten.laukvik.no/images/upload.html";
	public final static Version VERSION = new Version( "Trainer", 0, 2, "file:///Users/morten/Projects/Research/src/org/laukvik/trainer/shop/TrainerVersion.xml" );
	private final static String HELP_URL = "file:///Users/morten/Projects/Research/src/org/laukvik/trainer/help/index.html";
	
	public JournalManager() {
		listeners = new Vector<JournalReadWriteListener>();
	}
	
	public void addReadWriteListener( JournalReadWriteListener listener ){
		listeners.add( listener );
	}
	
	public void removeReadWriteListener( JournalReadWriteListener listener ){
		listeners.remove( listener );
	}
	
	public Journal readShop() throws SAXException, IOException, ParserConfigurationException{
		URL url = new URL( SHOP_XML );
		return read( url.openStream() );
	}
	
	public void fireStatusChanged( String message ){
		for (JournalReadWriteListener l : listeners){
			l.statusChanged( message );
		}
	}
	
	public void fireProgressChanged( int percent ){
		for (JournalReadWriteListener l : listeners){
			l.progressChanged( percent );
		}
	}
	
	public void fireProgressMinMax( int min, int max ){
		for (JournalReadWriteListener l : listeners){
			l.setMinMax(min, max);
		}
	}
	
	public static String getAppName() {
		return JournalPanel.VERSION.toString();
	}
	
	/**
	 * Returns the file location where the Journal File is stored
	 * 
	 * @return
	 */
	public static File getJournalFile(){
		return new File( getTrainerFolder(), JOURNAL_FILENAME );
	}
	
	/**
	 * Returns the folder for which the exercise is
	 * 
	 * @param exercise
	 * @return
	 */
	public static File getExerciseFolder( Exercise exercise ){
		if (exercise == null){
			throw new IllegalArgumentException( "Exercise not specified" );
		}
		File file = new File( getTrainerFolder(), exercise.getUID() + "" );
		if (!file.exists()){
			file.mkdir();
		}
		return file;
	}
	
	/**
	 * Returns the file for the specified photo 
	 * 
	 * @param exercise
	 * @param photo
	 * @return
	 */
	public static File getExercisePhotoFile( ExercisePhoto photo ){
		return new File( getExerciseFolder( photo.getExercise() ), photo.getPhotoID() + ".jpg" );
	}
	
	public static File getExerciseThumbnailFile( ExercisePhoto photo ){
		return new File( getExerciseFolder( photo.getExercise() ), photo.getPhotoID() + "_thumb.jpg" );
	}
	
	public static File getExcercise( Exercise exercise ){
		return new File( getTrainerFolder(), exercise.getUID() + "" );
	}
	
	public static void importExercisePhoto( ExercisePhoto photo ){
		File libraryFile = JournalManager.getExercisePhotoFile( photo );
//		photo.getURL().toExternalForm()
	}
	
    // Copies src file to dst file.
    // If the dst file does not exist, it is created
    public static void copy( File src, File dst ) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
	
	public static File getTrainerFolder(){
		
		File lib =  new File( CrossPlatformUtilities.getLibraryFolder(), "Trainer" );
		System.out.println( "LibraryFolder: " + CrossPlatformUtilities.getLibraryFolder() );
		if (!lib.exists()){
			lib.mkdir();
		}
		return lib;
	}	

	
	public void log( Object msg ){
//		System.out.println( msg );
	}
	
	public void createEmpty(){
		try {
			save( new Journal() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Journal read( InputStream is ) throws SAXException, IOException, ParserConfigurationException{
		log( "Reading..." );
		Journal j = new Journal();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse( is );
        doc.getDocumentElement().normalize();

        /* Get journal */
        Element journalElement = doc.getDocumentElement();
        journalElement.normalize();
        j.setBirthday( new Date( Long.parseLong( journalElement.getAttribute("birthday") ) ) );
        j.setOwner( journalElement.getAttribute("owner") );
        j.setSex( Integer.parseInt( journalElement.getAttribute("sex") ) );
        j.setDistance( Distance.parse( journalElement.getAttribute("distanceunit") ) );
        j.setWeight( Weight.parse( journalElement.getAttribute("weightunit") ) );
        try{
        	j.setLastUsed( new Date( Long.parseLong( journalElement.getAttribute("lastUsed") )  ) );
        } catch(Exception e){
        	
        }
        try{
        	j.setLastChanged( new Date( Long.parseLong( journalElement.getAttribute("lastChanged") )  ) );
        } catch(Exception e){
        	
        }   
        
        try{
        	j.setAuthorID( Long.parseLong( journalElement.getAttribute("authorID") ) );
        } catch(Exception e){
        	
        }
        String [] langAndCountry = journalElement.getAttribute("locale").split("_");
        
        j.setLocale( new Locale( langAndCountry[0], langAndCountry[1] ) );
//        j.setLocale( new Locale( journalElement.getAttribute("locale") ) );

        Node exercisesNode = null;
        Node workoutsNode = null;
        Node statusNode = null;
        
        NodeList journalNodeList = journalElement.getChildNodes();
        for (int x=0; x<journalNodeList.getLength(); x++){
        	Node n = journalNodeList.item( x );
        	if (n.getNodeName().equalsIgnoreCase("exercises")){
        		exercisesNode = n;
        	} else if (n.getNodeName().equalsIgnoreCase("workouts")){
        		workoutsNode = n;
        	} else if (n.getNodeName().equalsIgnoreCase("statuses")){
        		statusNode = n;
        	}
        }
        

        
        /* Get the two main elements so we can calculate progress */
        int exerciseCount = 0;
        int workoutCount = 0;
        int statusCount = 0;
        NodeList exerciseNodeList = null;
        NodeList workoutNodeList = null;
        NodeList statusNodeList = null;
        
        if (exercisesNode == null){
        	exerciseCount = 0;
        } else {
        	exerciseNodeList = exercisesNode.getChildNodes();
        	exerciseCount = exerciseNodeList.getLength();
        }
        
        if (workoutsNode == null){
        	workoutCount = 0;
        } else {
        	workoutNodeList = workoutsNode.getChildNodes();
        	workoutCount = workoutNodeList.getLength();
        }
        
        if (statusNode == null){
        	statusCount = 0;
        } else {
        	statusNodeList = statusNode.getChildNodes();
        	statusCount = statusNodeList.getLength();
        }
        /**/
        

        
        int itemsToRead = exerciseCount + workoutCount + statusCount;
        int itemsRead = 0;
        fireProgressMinMax( 0, itemsToRead );
        
        /* Get exercises */
        for (int x=0; x<exerciseCount; x++){
        	log( "Reading exercise " + x + "/" + exerciseNodeList.getLength() );
        	fireProgressChanged( itemsRead );
        	Node n = exerciseNodeList.item( x );
        	if (n.getNodeName().equalsIgnoreCase("exercise")){
        		String name = n.getAttributes().getNamedItem("name").getNodeValue();
        		String notes = n.getAttributes().getNamedItem("notes").getNodeValue();
        		String uidS = n.getAttributes().getNamedItem("uid").getNodeValue();
        		long uid = Long.parseLong( uidS );
        		
        		String muscleString = n.getAttributes().getNamedItem("muscleid").getNodeValue();
        		int muscleID = Integer.parseInt( muscleString );
        		
        		long authorID = 0;
        		
        		if (n.getAttributes().getNamedItem("authorID") != null){
        			authorID = Long.parseLong( n.getAttributes().getNamedItem("authorID").getNodeValue() );
        		}
        		
        		Exercise ex = new Exercise( uid, name, notes, Muscle.getInstance(muscleID), authorID );
        		try{
        			ex.setEnabled( n.getAttributes().getNamedItem("enabled").getNodeValue().equalsIgnoreCase("true") );
        		} catch(Exception e){	
        		}
        		/* Author name*/
        		Node authorNode = n.getAttributes().getNamedItem("author");
        		if (authorNode != null){
        			ex.setAuthor( authorNode.getNodeValue() );
        		}
        		/* Author URL */
        		Node authorUrlNode = n.getAttributes().getNamedItem("authorURL");
        		if (authorUrlNode != null){
        			try{
        				ex.setAuthorURL( new URL( authorUrlNode.getNodeValue() ) );
        			} catch(Exception e){
        			}
        		}
        		/* YouTube URL */
        		Node youTubeUrlNode = n.getAttributes().getNamedItem("youTubeURL");
        		if (youTubeUrlNode != null){
        			try{
        				ex.setYouTubeURL( new URL( youTubeUrlNode.getNodeValue() ) );
        			} catch(Exception e){

        			}
        		}
        		
        		/* Look for muscleIDs */
        		Node muscleCSVnode = n.getAttributes().getNamedItem("extraMuscleID");
        		if (muscleCSVnode != null){
        			String [] csv = muscleCSVnode.getNodeValue().split( "," );
        			for (String id : csv){
        				ex.addMuscle( Muscle.getInstance( Integer.parseInt( id ) ) );
        			}
        			
        		}
        		
        		/* Author URL */
        		Node localeNode = n.getAttributes().getNamedItem("locale");
        		if (localeNode != null){
        			try{
        				ex.setLocale( new Locale( localeNode.getNodeValue() )  );
        			} catch(Exception e){
        			}
        		}
        		
        		/* Look for photos */
        		


        		/* Look for icon */
				NodeList imagelist = n.getChildNodes();
				for (int i=0; i<imagelist.getLength(); i++){
					Node node = imagelist.item(i);
					if (node.getNodeName().equalsIgnoreCase("data")){
						try{
							log( "Reading image for exercise " + x + "/" + exerciseNodeList.getLength() );
			    			String base64 = node.getTextContent();
			    			log( "Found image node: base64 length is " + base64.length() + " bytes." );
			    			byte [] bytes = stringToBinary( base64 );
			    			ex.setIcon( new ImageIcon(bytes) );
			    			log( "base64: read " + bytes.length + " bytes." );
			    			
						} catch(Exception e){
							log( "Failed to read image for exercise " + x + "/" + exerciseNodeList.getLength() );
							e.printStackTrace();
						}
					} else if (node.getNodeName().equalsIgnoreCase("photo")){
						
						Node url = node.getAttributes().getNamedItem( "url" );
						Node comments = node.getAttributes().getNamedItem( "comments" );
						Node copyright = node.getAttributes().getNamedItem( "copyright" );
						Node photoID = node.getAttributes().getNamedItem( "photoID" );
						
						try{
							ExercisePhoto photo = new ExercisePhoto( ex, Long.parseLong( photoID.getNodeValue() ), new URL( url.getNodeValue() ), comments.getNodeValue(), copyright.getNodeValue() );
							ex.addPhoto( photo );
						} catch (Exception e){
							e.printStackTrace();
						}
						
						
					} else {
						log( "Node is not an image..." );
					}
				}
				
        		try{
        			String iconURL = n.getAttributes().getNamedItem("iconURL").getNodeValue();
        			ex.setOriginalIcon( new URL(iconURL) );
//        			System.err.println( "Found URL: " + iconURL );
        		} catch(Exception e){
        			
        		}

        		j.addExercise( ex );
        	}
        	itemsRead++;
        }
        
        /* Get workouts */
        log( "Reading workouts..." );
        
        for (int x=0; x<workoutCount; x++){
        	log( "Reading workout " + x + "/" + workoutNodeList.getLength() );
        	fireProgressChanged( itemsRead );
        	Node workoutNode = workoutNodeList.item( x );
        	if (workoutNode.getNodeName().equalsIgnoreCase("workout")){
        		/* Get single workout */
        		String notes = workoutNode.getAttributes().getNamedItem("notes").getNodeValue();
        		String timeS = workoutNode.getAttributes().getNamedItem("time").getNodeValue();
        		long time = Long.parseLong( timeS );
        		Workout work = new Workout( time, notes );
//        		log( "\tWorkout: " + work.getDate() + " " + work.getNotes() );
        		
        		NodeList setNodeList = workoutNode.getChildNodes();
        		for (int y=0; y<setNodeList.getLength(); y++){
        			Node setNode = setNodeList.item( y );
        			if (setNode.getNodeName().equalsIgnoreCase("set")){
        				String uidS = setNode.getAttributes().getNamedItem("uid").getNodeValue();
        				String weightS = setNode.getAttributes().getNamedItem("weight").getNodeValue();
        				String repsS = setNode.getAttributes().getNamedItem("reps").getNodeValue();
        				

        				
        				long uid 		= Long.parseLong( uidS );
        				double weight	= Double.parseDouble( weightS );
        				int reps		= Integer.parseInt( repsS );
        				
        				Set set = new Set( uid, weight, reps );
        				
        				Node commentsNode = setNode.getAttributes().getNamedItem("comments");
        				if (commentsNode != null){
        					set.setComments( commentsNode.getNodeValue() );
        				}
        				
//        				log( "\t\t" + set.getWeight() + " x " + set.getReps() );
        				work.add( set );
        			}
        		}
        		

        		for (Aerobic a : work.listAerobics()){
        			
        			Node duration = workoutNode.getAttributes().getNamedItem( a.toString().toLowerCase() + "duration" );
        			if (duration != null){
        				a.setDuration( Long.parseLong( duration.getNodeValue() ) );
        			}
        			
        			Node distance = workoutNode.getAttributes().getNamedItem( a.toString().toLowerCase() + "distance" );
        			if (distance != null){
        				a.setDistance( Float.parseFloat( distance.getNodeValue() ) );
        			}
        			
        			Node heartRate = workoutNode.getAttributes().getNamedItem( a.toString().toLowerCase() + "heartRate" );
        			if (heartRate != null){
        				a.setHeartRate( Integer.parseInt( heartRate.getNodeValue()) );
        			}
        			
        		}

        		
        		/* Add each workout to journal */
        		j.addWorkout( work );
        	}
        	itemsRead++;
        }
        
        
        
        
        
        /* Get workouts */
        log( "Reading statuses..." );
        
        for (int x=0; x<statusCount; x++){
        	Node n = statusNodeList.item( x );
        	log( "Reading status " + x + "/" + statusNodeList.getLength() + " node=" + n.getNodeName() );
        	fireProgressChanged( itemsRead );
        	
        	if (n.getNodeName().equalsIgnoreCase("status")){
        		try{
        			long time = Long.parseLong( n.getAttributes().getNamedItem("time").getNodeValue() );        		
	        		Status status = new Status( time );
	        		for (String key : Status.TYPES){
	        			status.setValue( key, Float.parseFloat( n.getAttributes().getNamedItem( key ).getNodeValue() ) );
	        		}
	        		j.addStatus( status );
        		} catch(Exception e){
        			e.printStackTrace();
        		}
        	}
        	itemsRead++;
        }
        j.sortStatuses();
        log( "Finished" );
		return j;
	}
	
	private static byte [] getBytes( Document document ) throws Exception{
		DOMSource domSource = new DOMSource( document );
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write( domSource, out );
		return out.toByteArray();
	}
	
	private static void write( DOMSource source, OutputStream out ) throws Exception{
		StreamResult streamResult = new StreamResult(out);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer;
		serializer = tf.newTransformer();
		serializer.setOutputProperty( OutputKeys.ENCODING, "ISO-8859-1" );
		serializer.setOutputProperty( OutputKeys.INDENT, "yes" );
		serializer.transform( source, streamResult );
	}

	private static void write( File file, Document document ) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter( file ));
			out.write( new String( getBytes(document) ) );
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
	public void save( Journal journal ) throws Exception {
		/**/
		write( JournalManager.getJournalFile(), toXML( journal ) );
	}

	public Document toXML( Journal journal ) throws Exception {
		log( "Saving..." );
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.newDocument();
		Element journalElement = doc.createElement("journal");
		doc.appendChild( journalElement );
		
		/* Create attributes */
		journalElement.setAttribute( "owner", journal.getOwner() );
		journalElement.setAttribute( "birthday", journal.getBirthday().getTime() + "" );
		journalElement.setAttribute( "sex", journal.getSex() + "" );
		journalElement.setAttribute( "locale", journal.getLocale().toString() );
		journalElement.setAttribute( "distanceunit", journal.getDistance().toString() );
		journalElement.setAttribute( "weightunit", journal.getWeight().toString() );
		journalElement.setAttribute( "authorID", journal.getAuthorID() + "" );
		journalElement.setAttribute( "lastUsed", new Date().getTime()+"" );
//		journalElement.setAttribute( "lastChanged", journal.getLastChanged().getTime()+"" );
		
		/**/
		int itemsToSave = journal.getExercises().size() + journal.getWorkouts().size() + journal.getStatuses().size();
		int savedItems = 0;
		fireProgressMinMax( 0, itemsToSave );
		/* 
		 * 
		 * Add exercises 
		 * 
		 * */
		Element exercisesElement = doc.createElement("exercises");
		int exercisesWritten = 0;
		for(Exercise exercise : journal.getExercises()){
			log( "Saving exercise " + (1+exercisesWritten) + "/" + journal.getExercises().size() );
			fireProgressChanged( savedItems );
			Element entryElement = doc.createElement("exercise");
			entryElement.setAttribute( "name", exercise.getName() );
			entryElement.setAttribute( "uid", exercise.getUID() + "" );
			entryElement.setAttribute( "notes", exercise.getNotes() + "" );
			entryElement.setAttribute( "grid", exercise.getGrid() + "" );
			entryElement.setAttribute( "muscleid", exercise.getMuscle().getMuscleID() + "" );
			entryElement.setAttribute( "enabled", exercise.isEnabled() + "" );
			entryElement.setAttribute( "authorID", exercise.getAuthorID() + "" );
			
			if (exercise.getAuthor() != null && exercise.getAuthor().length() > 0){
				entryElement.setAttribute( "author", exercise.getAuthor() + "" );	
			}
			
			if (exercise.getAuthorURL() != null){
				entryElement.setAttribute( "authorURL", exercise.getAuthorURL().toExternalForm() );
			}
			
			if (exercise.getYouTubeURL() != null){
				entryElement.setAttribute( "youTubeURL", exercise.getYouTubeURL().toExternalForm() );
			}
			
			if (exercise.getLocale() != null){
				entryElement.setAttribute( "locale", exercise.getLocale().getLanguage() );
			}
			
			Muscle [] muscles = exercise.listMuscles();
			if (muscles.length == 0){
				/* No extra muscles needed */
			} else {
				/* */
				String muscleCSV = "";
				for (Muscle m : muscles){
					if (muscleCSV.length() == 0){
						muscleCSV += m.getMuscleID();
					} else {
						muscleCSV += "," + m.getMuscleID();
					}
				}
				entryElement.setAttribute( "extraMuscleID", muscleCSV );
			}

			exercisesElement.appendChild( entryElement );
			
			/* Add photos */
			ExercisePhoto [] photos = exercise.listPhotos();
			if (photos.length == 0){
				/* No photos */
			} else {
				for (ExercisePhoto photo : photos){
					Element photoElement = doc.createElement("photo");
					photoElement.setAttribute( "url", photo.getURL().toExternalForm() );
					photoElement.setAttribute( "comments", photo.getComments() );
					photoElement.setAttribute( "copyright", photo.getCopyright() );
					photoElement.setAttribute( "photoID", photo.getPhotoID() + "" );
					entryElement.appendChild( photoElement );
				}				
			}

			
			try{
				if (exercise.getIcon() == null){
					/* Do nothing */
				} else {
					Element iconDataElement = doc.createElement("data");
					log( "Saving image for exercise " + exercisesWritten + "/" + journal.getExercises().size() );
					String data = toBase64( exercise.getIcon() );
					log( "Encoded data: " + data.length() );
					iconDataElement.setTextContent(data);
					entryElement.appendChild( iconDataElement );
					
					if (exercise.getOriginalIcon() != null){
						entryElement.setAttribute( "iconURL", exercise.getOriginalIcon() + "" );
					}
				}

			} catch(Exception e){
				e.printStackTrace();
			}
			
			exercisesWritten++;
			savedItems++;
		}
		journalElement.appendChild( exercisesElement );
		
		
		
		
		/* 
		 * 
		 * Add workouts 
		 * 
		 * */
		int workoutsWritten = 0;
		Element workoutsElement = doc.createElement("workouts");
		for(Workout work : journal.getWorkouts()){
			log( "Saving workout " + (workoutsWritten+1) + "/" + journal.getWorkouts().size() );
			fireProgressChanged( savedItems );
			Element workElement = doc.createElement("workout");
			workElement.setAttribute( "time", work.getMillis() + "" );
			workElement.setAttribute( "notes", work.getNotes() + "" );
			/* Add sets */
			for(Set set : work.getSets()){ // <set uid="1" weight="32.5" reps="3"/>
				
				if (set.getExercise() == null){
					
				} else {
					Element setElement = doc.createElement("set");
					setElement.setAttribute( "uid", set.getExercise().getUID() + "" );
					setElement.setAttribute( "weight", set.getWeight() + "" );
					setElement.setAttribute( "reps", set.getReps() + "" );
					if (set.getComments() != null && set.getComments().length() > 0){
						setElement.setAttribute( "comments", set.getComments() );
					}
					
					/* Add set to workout */
					workElement.appendChild( setElement );
				}

			}
			
			/* Aerobic */
			for (Aerobic a : work.listAerobics()){
				if (a.isEmpty()){
					/* Don't write unnecessary XML if its empty */
				} else {
					workElement.setAttribute( a.toString().toLowerCase() + "distance",  a.getDistance() + "" );
					workElement.setAttribute( a.toString().toLowerCase() + "duration",  a.getDuration() + "" );	
					workElement.setAttribute( a.toString().toLowerCase() + "heartRate",  a.getHeartRate() + "" );	
				}
			}
			
			workoutsElement.appendChild( workElement );
			workoutsWritten++;
			savedItems++;
		}
		journalElement.appendChild( workoutsElement );
		
		
		
		/* 
		 * 
		 * Add statuses 
		 * 
		 * */
		Element statusesElement = doc.createElement("statuses");
		int statusesWritten = 0;
		for(Status status : journal.getStatuses()){
			log( "Saving status " + (1+statusesWritten) + "/" + journal.getStatuses().size() );
			/* Create new node */
			Element statusElement = doc.createElement("status");
			
			statusElement.setAttribute( Status.TIME, status.getMilliseconds() + "" );
			statusElement.setAttribute( Status.WEIGHT, status.weight + "" );
			statusElement.setAttribute( Status.BODYFAT, status.bodyfat + "" );
			statusElement.setAttribute( Status.NECK, status.neck + "" );
			statusElement.setAttribute( Status.CHEST, status.chest + "" );
			statusElement.setAttribute( Status.WAIST, status.waist + "" );
			
			statusElement.setAttribute( Status.LEFTCALF, status.leftCalf + "" );
			statusElement.setAttribute( Status.LEFTFOREARM, status.leftForeArm + "" );
			statusElement.setAttribute( Status.LEFTTHIGH, status.leftThigh + "" );
			statusElement.setAttribute( Status.LEFTUPPERARM, status.leftUpperArm + "" );
			
			statusElement.setAttribute( Status.RIGHTCALF, status.rightCalf + "" );
			statusElement.setAttribute( Status.RIGHTFOREARM, status.rightForeArm + "" );
			statusElement.setAttribute( Status.RIGHTTHIGH, status.rightThigh + "" );
			statusElement.setAttribute( Status.RIGHTUPPERARM, status.rightUpperArm + "" );
			
			/* Add node to status node */
			statusesElement.appendChild( statusElement );
			/* Fire progress bar listeners */
			fireProgressChanged( savedItems );
			/* Update counters */
			statusesWritten++;
			savedItems++;
		}
		
		journalElement.appendChild( statusesElement );

//		/**/
//		write( JournalManager.getJournalFile(), doc );
		return doc;
	}
	
	public static String binaryToString( byte [] bytes ){
		return new sun.misc.BASE64Encoder().encode( bytes );
	}
	
	public static byte [] stringToBinary( String base64 ) throws IOException{
	    return new sun.misc.BASE64Decoder().decodeBuffer( base64 );
	}
	
	public static ImageIcon toIcon( String base64 ){
		try {
			return new ImageIcon( stringToBinary(base64) );
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String toBase64( ImageIcon icon ) throws IOException{
		return binaryToString( toBytes(icon) );
	}
	
	public static byte [] toBytes( ImageIcon icon) throws IOException{
		return PhotoManager.toBytes( PhotoManager.getBufferedImage( icon.getImage() ) );
	}
	
	public static ImageIcon getNormalIcon( URL url ) throws InterruptedException{
		return getSmallIcon( new ImageIcon( url ), 100, 100 );
	}
	
	public static ImageIcon getSmallIcon( ImageIcon icon ) throws InterruptedException{
		return getSmallIcon( icon, 50, 50 );
	}
	
	public static Dimension getSize( Dimension from, Dimension to ){
		Dimension size = new Dimension();
		boolean portrait = from.height > from.width;
		if (portrait){
			size.height = to.height;
			float factor = (float) from.height / (float) to.height;
			size.width = (int) ( (float)from.width / factor);
		} else {
			size.width = to.width;
			float factor = (float)from.width / (float)to.width;
			size.height = (int) ((float)from.height / factor);
		}
		return size;
	}
	
	/**
	 * Resizes an icon to 50 by 50
	 * @param icon
	 * @return
	 * @throws InterruptedException
	 */
	public static ImageIcon getSmallIcon( ImageIcon icon, int width, int height ) throws InterruptedException{
		Dimension size = getSize( new Dimension(icon.getIconWidth(),icon.getIconHeight()), new Dimension( width, height ) );
		/* Scale the image. MUST use MediaTracker to wait to finish!!!!!!!!! */
		Image scaled = icon.getImage().getScaledInstance( size.width, size.height, Image.SCALE_AREA_AVERAGING );
		
		/* Create a MediaTracker to wait images being loaded */
		@SuppressWarnings("serial")
		MediaTracker tracker = new MediaTracker( new Component(){} );
		tracker.addImage( scaled, 0 );
		tracker.waitForAll();
		
		return new ImageIcon( scaled );
	}

	public static URL getShopURL( Journal journal ){
		try {
//			return new URL( "http://morten.laukvik.no/downloads/shop.htm?authorID=" + journal.getAuthorID() );
			return new URL( SHOP_HTML + "?authorID=" + journal.getAuthorID() );
		} catch (MalformedURLException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param selectedExercise
	 */
	public void publish( Exercise exercise ) throws Exception{
		Journal journal = new Journal();
		journal.setAuthorID( exercise.getAuthorID() );
		journal.setOwner( exercise.getJournal().getOwner() );
		journal.addExercise( exercise );
		Document doc = toXML( journal );
		byte [] bytes = getBytes( doc );
		String base64 = binaryToString( bytes );
		URL url = new URL( PUBLISH_EXERCISE_URL );
		publish( url, base64, 0 );
		
	}
	
	public void publish( URL url, String data, long authorID ) throws IOException{
	    URLConnection   urlConn;
	    DataOutputStream    printout;
	    DataInputStream     input;
	    // URL connection channel.
	    urlConn = url.openConnection();
	    // Let the run-time system (RTS) know that we want input.
	    urlConn.setDoInput (true);
	    // Let the RTS know that we want to do output.
	    urlConn.setDoOutput (true);
	    // No caching, we want the real thing.
	    urlConn.setUseCaches (false);
	    // Specify the content type.
	    urlConn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
	    // Send POST output.
	    printout = new DataOutputStream (urlConn.getOutputStream());
	    String content = "base64=" + URLEncoder.encode( data, "application/x-www-form-urlencoded" );
	    printout.writeBytes( content );
	    printout.flush();
	    printout.close();
	    // Get response data.
	    input = new DataInputStream (urlConn.getInputStream());
	    String str;
	    while (null != ((str = input.readLine()))){
	    	System.out.println (str);
	    }
	    input.close ();
	}


	public static URL getHelpURL() {
		try {
			return new URL( HELP_URL );
		} catch (MalformedURLException e) {
			return null;
		}
	}

	/**
	 * Returns the amount of new exercises available in the shop
	 * 
	 * TODO - Make this dynamic
	 * 
	 * @param date
	 * @return
	 */
	public static int getNewShopItems(Date date) {
		return 0;
	}

	/**
	 * Creates a smaller copy of the specified image file and places it
	 * according the the photo object
	 * 
	 * @param photo
	 * @param file
	 * @throws Exception 
	 */
	public static void createThumbnail( ExercisePhoto photo, File file ) throws Exception{
		ImageIcon icon = new ImageIcon( file.toURL() );
		ImageIcon thumbIcon = getSmallIcon( icon, 100, 100 );
		File thumbnailFile = JournalManager.getExerciseThumbnailFile( photo );
		FileOutputStream out = new FileOutputStream( thumbnailFile );
		out.write( PhotoManager.toBytes( thumbIcon ) );
		out.close();
	}
	
	public static void main(String [] args){
		
		Locale no = new Locale( "no" );
		
		Locale l = new Locale( no.getLanguage() );
		System.out.println( "Country: " + l.getCountry() );
		System.out.println( "DisplayCountry: " + l.getDisplayCountry() );
		System.out.println( "DisplayLanguage: " + l.getDisplayLanguage() );
		System.out.println( "DisplayName: " + l.getDisplayName() );
		System.out.println( "DisplayVariant: " + l.getDisplayVariant() );
		System.out.println( "ISO3Language: " + l.getISO3Language() );
		System.out.println( "Language: " + l.getLanguage() );
	}
}