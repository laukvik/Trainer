package org.laukvik.trainer.exercise;

import java.net.URL;

public class ExercisePhoto {

	private long photoID;
	private String comments;
	private URL url;
	private String copyright;
	private Exercise exercise;
	
	/**
	 * Reconstructs a photo from database or similar
	 * 
	 * @param exercise
	 * @param photoID
	 * @param url
	 * @param comments
	 * @param copyright
	 */
	public ExercisePhoto( Exercise exercise, long photoID, URL url, String comments, String copyright ){
		this.exercise = exercise;
		this.photoID = photoID;
		this.url = url;
		this.comments = comments;
		this.copyright = copyright;
	}
	
	/**
	 * Creates a new photo from scratch. photoID will be created automatically
	 * 
	 * @param exercise
	 * @param url
	 * @param comments
	 * @param copyright
	 */
	public ExercisePhoto( Exercise exercise, URL url, String comments, String copyright ){
		this.exercise = exercise;
		this.photoID = System.currentTimeMillis();
		this.url = url;
		this.comments = comments;
		this.copyright = copyright;
	}
	
	public Exercise getExercise() {
		return exercise;
	}
	
	public String getComments() {
		return comments;
	}
	
	public String getCopyright() {
		return copyright;
	}
	
	public URL getURL(){
		return url;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	
	public void setURL(URL url) {
		this.url = url;
	}
	
	public long getPhotoID() {
		return photoID;
	}
	
}