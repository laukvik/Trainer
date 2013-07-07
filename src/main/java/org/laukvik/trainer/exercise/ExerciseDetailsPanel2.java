package org.laukvik.trainer.exercise;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.laukvik.swing.locale.LanguageComboBoxRenderer;
import org.laukvik.swing.locale.LanguageSorter;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.muscle.MuscleCheckBox;

public class ExerciseDetailsPanel2 extends ExerciseEditorPanel implements LocaleListener{

	private static final long serialVersionUID = 1L;
	private Exercise exercise = null;
	private boolean isUpdating;
	
	public ExerciseDetailsPanel2( ExerciseEditorDialog exerciseEditorDialog ){
    	super();
    	if (exerciseEditorDialog == null){
    		throw new IllegalArgumentException("ExerciseEditorPanel cant be null!");
    	}
    	photosPanel.setLayout( new ModifiedFlowLayout( ModifiedFlowLayout.LEFT ) );
    	
    	/* Remove all example items from netbeans */
    	languageComboBox.removeAllItems();
    	languageComboBox.setMaximumRowCount( 20 );
		languageComboBox.setRenderer( new LanguageComboBoxRenderer() );
		
		String [] languages = Locale.getISOLanguages();
		Locale [] locales = new Locale[ languages.length ];
		for (int x=0; x<languages.length; x++){
			locales[ x ] = new Locale( languages[x] );
//			System.out.println( locales[ x ].getDisplayName() );
		}
		
		LanguageSorter sorter = new LanguageSorter( exerciseEditorDialog.getLocale() );
		Arrays.sort( locales, sorter );
		
		languageComboBox.addItem( "" );	
		for (Locale l : locales){
			languageComboBox.addItem( l );	
		}
    	
		/* Remove all example items from netbeans */
		musclesPanel.removeAll();
    	Muscle [] muscles = org.laukvik.trainer.anatomy.Human.BODY.listAllMuscles();
    	for (Muscle m : muscles){
    		musclesPanel.add( new MuscleCheckBox( m  ) );
    	}
    	
    	/* 
    	 * 
    	 * 
    	 * Add listeners 
    	 * 
    	 * 
    	 **/
    	/* Overview */
    	overviewAuthorHomepageButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					CrossPlatformUtilities.openBrowser( new URL( authorHomepageTextField.getText() ) );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}} 
    	);
    	overviewYouTubeButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					CrossPlatformUtilities.openBrowser( new URL( youTubeTextField.getText() ) );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}} 
    	);
    	
    	/* Info */
    	visibleCheckbox.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if (!isUpdating && exercise != null){
					exercise.setEnabled( visibleCheckbox.isSelected() );
				}	
			}} 
    	);
    	nameTextField.addCaretListener( new CaretListener(){
			public void caretUpdate(CaretEvent evt) {
				if (!isUpdating && exercise != null){
					exercise.setName( nameTextField.getText() );
				}
			}}
    	);
    	descriptionTextarea.addCaretListener( new CaretListener(){
			public void caretUpdate(CaretEvent evt) {
				if (!isUpdating && exercise != null){
					exercise.setNotes( descriptionTextarea.getText() );
				}
			}}
    	);
    	languageComboBox.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				if (!isUpdating && exercise != null){
					exercise.setLocale( (Locale) languageComboBox.getSelectedItem() );
				}
			}} 
    	);
    	
    	/* Author */
    	authorNameTextField.addCaretListener( new CaretListener(){
			public void caretUpdate(CaretEvent evt) {
				if (!isUpdating && exercise != null){
					exercise.setAuthor( authorNameTextField.getText() );
				}
			}}
    	);
    	authorHomepageTextField.addCaretListener( new CaretListener(){
			public void caretUpdate(CaretEvent evt) {
				if (!isUpdating && exercise != null){
					try {
						exercise.setAuthorURL( new URL( authorHomepageTextField.getText() ) );
						authorHomePageButton.setEnabled( true );
					} catch (MalformedURLException e) {
						exercise.setAuthorURL( null );
						authorHomePageButton.setEnabled( false );
					}
					overviewAuthorHomepageButton.setEnabled( authorHomePageButton.isEnabled() );
					overviewAuthorHomepageButton.setVisible( authorHomePageButton.isEnabled() );
				}
			}}
    	);
    	
    	youTubeTextField.addCaretListener( new CaretListener(){
			public void caretUpdate(CaretEvent evt) {
				if (!isUpdating && exercise != null){
					try {
						exercise.setYouTubeURL( new URL( youTubeTextField.getText() ) );
						youTubeButton.setEnabled( true );
					} catch (MalformedURLException e) {
						exercise.setYouTubeURL( null );
						youTubeButton.setEnabled( false );
					}
					overviewYouTubeButton.setEnabled( youTubeButton.isEnabled() );
					overviewYouTubeButton.setVisible( youTubeButton.isEnabled() );
				}
			}}
    	);
    	
    	authorHomePageButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					CrossPlatformUtilities.openBrowser( new URL( authorHomepageTextField.getText() ) );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}} 
    	);
    	
    	youTubeButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					CrossPlatformUtilities.openBrowser( new URL( youTubeTextField.getText() ) );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}} 
    	);

    	/* Muscles - its already fixed in custom combobox MuscleComboBox */
    	
    	/* Photos */
    	new DropTarget( photosPanel, new DropTargetListener(){

			public void dragEnter(DropTargetDragEvent dtde) {
				if (exercise == null){
					dtde.rejectDrag();
				} else {
					dtde.acceptDrag( DnDConstants.ACTION_COPY );	
				}
				
				
			}

			public void dragExit(DropTargetEvent dte) {
			}

			public void dragOver(DropTargetDragEvent dtde) {
			}

			public void drop(DropTargetDropEvent dtde) {
				if (exercise == null){
					dtde.rejectDrop();
					return;
				}
				dtde.acceptDrop( dtde.getDropAction() );
				
				if (dtde.isDataFlavorSupported( DataFlavor.javaFileListFlavor )){
					try {
						Transferable t = dtde.getTransferable();
						@SuppressWarnings("unchecked")
						List<File> fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
						File [] files = (File[]) fileList.toArray();
						for (File file : files){
							/* Create a new photo object */
							ExercisePhoto photo = new ExercisePhoto( exercise, file.toURL(), file.getName(), null );
							/* Add it to exercise (library) */
							exercise.addPhoto( photo );

							/* Copy file into library */
							JournalManager.copy( file, JournalManager.getExercisePhotoFile( photo ) );
							/* Create a thumbnail for it too */
							JournalManager.createThumbnail( photo, file );

							/* Add it to GUI */
							photosPanel.add( new ExerciseThumbnail( photo ) );
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

			public void dropActionChanged(DropTargetDragEvent dtde) {
			}} );
    	
	}

	public void setExercise( Exercise exercise ) {
		this.isUpdating = true;
		this.exercise = exercise;
		if (exercise == null){
			/* Overview */
			overviewNameLabel.setText( null );
			overviewDescriptionTextArea.setText( null );
			overviewAuthorHomepageButton.setVisible( false );
			overviewYouTubeButton.setVisible( false );
			visibleCheckbox.setSelected( false );
			/* Info */
			nameTextField.setText( null );
			descriptionTextarea.setText( null );
			languageComboBox.setSelectedIndex( 0 );
			/* Author */
			authorNameTextField.setText( null );
			authorHomepageTextField.setText( null );
			authorHomePageButton.setEnabled( false );
			youTubeTextField.setText( null );
			youTubeButton.setEnabled( false );
			/* Pictures */
	    	photosPanel.removeAll(); // Remove all existing 
			/* Muscles */
			for (Component component : musclesPanel.getComponents()){
				/* If we find a MuscleCheckBox */
				if (component instanceof MuscleCheckBox){
					MuscleCheckBox checkBox = (MuscleCheckBox) component;
					checkBox.setExercise( null );
					checkBox.setEnabled( false );
				}
			}
		} else { 
			/* Overview */
			overviewNameLabel.setText( exercise.getName() );
			overviewDescriptionTextArea.setText( exercise.getNotes() );
			overviewAuthorHomepageButton.setVisible( exercise.getAuthorURL() != null );
			overviewYouTubeButton.setVisible( exercise.getYouTubeURL() != null );
			visibleCheckbox.setSelected( exercise.isEnabled() );

			overviewStartIcon.setIcon( exercise.getIcon() );
			overviewStopIcon.setIcon( exercise.getStopIcon() );
			/* Info */
			nameTextField.setText( exercise.getName() );
			descriptionTextarea.setText( exercise.getNotes() );
			if (exercise.getLocale() == null){
				languageComboBox.setSelectedIndex( 0 );
			} else {
				boolean foundMatch = false;
				for (int x=1; x<languageComboBox.getItemCount(); x++){
					Locale l = (Locale)languageComboBox.getItemAt(x);
					if (l.getLanguage().equalsIgnoreCase( exercise.getLocale().getLanguage() )){
						languageComboBox.setSelectedIndex( x );
						foundMatch = true;
					}
				}
				if (!foundMatch){
					languageComboBox.setSelectedIndex( 0 );
				}
				
			}
			
			
			/* Author */
			authorNameTextField.setText( exercise.getAuthor() );
			authorHomepageTextField.setText(  exercise.getAuthorURL() == null ? "" :  exercise.getAuthorURL().toExternalForm() );
			authorHomePageButton.setEnabled( authorHomepageTextField.getText().length() > 0 );
			youTubeTextField.setText(  exercise.getYouTubeURL() == null ? "" :  exercise.getYouTubeURL().toExternalForm()  );
			youTubeButton.setEnabled( youTubeTextField.getText().length() > 0 );
			/* Pictures */
	    	photosPanel.removeAll(); // Remove all existing 
	    	for (ExercisePhoto photo : exercise.listPhotos()){
	    		photosPanel.add( new ExerciseThumbnail( photo ) );
	    	}
	    	
			/* Muscles */
			for (Component component : musclesPanel.getComponents()){
				/* If we find a MuscleCheckBox */
				if (component instanceof MuscleCheckBox){
					MuscleCheckBox checkBox = (MuscleCheckBox) component;
					checkBox.setExercise( exercise );
					checkBox.setEnabled( !exercise.getMuscle().sameAs( checkBox.getMuscle() ) );
				}
			}
		}
		this.isUpdating = false;
	}
	
	public void localeChanged(Locale locale) {
		languageComboBox.setLocale( locale );
		
//		String [] languages = Locale.getISOLanguages();
//		Locale [] locales = new Locale[ languages.length ];
//		for (int x=0; x<languages.length; x++){
//			locales[ x ] = new Locale( languages[x] );
//		}
//		
//		LanguageSorter sorter = new LanguageSorter( locale );
//		Arrays.sort( locales, sorter );
//		languageComboBox.removeAll();
//		languageComboBox.addItem( "" );	
//		for (Locale l : locales){
//			languageComboBox.addItem( l );	
//		}
	}

	public void emptyValues(){
	}
	
}