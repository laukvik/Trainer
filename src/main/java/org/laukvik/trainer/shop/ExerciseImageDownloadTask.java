package org.laukvik.trainer.shop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.shop.multi.Task;

public class ExerciseImageDownloadTask extends Task {
	
	Exercise exercise;
	int totalRead = 0;
	int toBeRead = 0;

	public ExerciseImageDownloadTask( Exercise exercise ){
		super( exercise.getName() );
		this.exercise = exercise;
	}
	
	public Exercise getExercise() {
		return exercise;
	}

	public void run() {
		try {
			File file = File.createTempFile("org.laukvik.exercise", ".jpg");
			download( exercise.getOriginalIcon(), file );
//			ImageIO.setUseCache( true );
//			BufferedImage image = ImageIO.read( file );
			exercise.setIcon( JournalManager.getNormalIcon( file.toURL() ) );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		setStatus( COMPLETED );
	}
	
	public void download( URL url, File file ) {
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			conn = url.openConnection();
			in = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;
			while ((numRead = in.read(buffer)) != -1 && !isAborted()) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}
	}
	
}
