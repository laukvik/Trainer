package org.laukvik.trainer.journal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.laukvik.trainer.actions.CopyAction;
import org.laukvik.trainer.actions.CutAction;
import org.laukvik.trainer.actions.DeleteAction;
import org.laukvik.trainer.actions.PasteAction;
import org.laukvik.trainer.aerobic.AerobicTable;
import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.exercise.ExerciseEditorDialog;
import org.laukvik.trainer.exercise.ExercisesTabbedPane;
import org.laukvik.trainer.help.HelpViewer;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetSelectionListener;
import org.laukvik.trainer.set.SetTransferable;
import org.laukvik.trainer.set.SetsTable;
import org.laukvik.trainer.shop.ShopDialog;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.swing.NotesEditor;
import org.laukvik.trainer.swing.OwnerPropertiesDialog;
import org.laukvik.trainer.swing.StatusBar;
import org.laukvik.trainer.swing.status.BodyStatusDialog;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;
import org.laukvik.trainer.workout.WorkoutTable;
import org.laukvik.trainer.workout.WorkoutTableModel;
import org.laukvik.trainer.workout.WorkoutTransferable;

/**
 * 
 * 
 *
 */
public class JournalPanel extends JPanel implements VersionEnabled, ListSelectionListener, FocusListener, CalendarListener, JournalListener, SetSelectionListener, WorkoutSelectionListener, ActionListener, ClipboardOwner, LocaleListener {

	private static final long serialVersionUID = 1L;
	public final static Version VERSION = JournalManager.VERSION;
	private WorkoutTableModel workoutTableModel;
	private WorkoutTable workoutTable;
	private JScrollPane tableScroll;
	private ExercisesTabbedPane exerciseTabbedPane;
	private Journal journal;
	private JournalToolbar journalToolbar;
	private SetsTable setsTable;
	private StatusBar statusBar;
	private ExerciseEditorDialog exerciseEditorDialog;
	/* Components for details */
	private JPanel detailsPanel;
	private DatePicker datePicker;
	private NotesEditor notesEditor;
	private AerobicTable aerobicTable;
	private ShopDialog shopDialog;
	private HelpViewer helpViewer;
	/**/
	private JournalMenu journalMenu;
		
	public JournalPanel( Journal journal  ){
		super( new BorderLayout() );
		setLocale( journal.getLocale() );
		this.journal = journal;
		this.journalMenu = new JournalMenu( this );
		initComponents();

		journal.addWorkoutSelectionListener( this );
		journal.addSetSelectionListener( this ); 
		workoutTable.getActionMap().put( CutAction.COMMAND_KEY, new CutAction(this) );
		workoutTable.getActionMap().put( CopyAction.COMMAND_KEY, new CopyAction(this) );
		workoutTable.getActionMap().put( PasteAction.COMMAND_KEY, new PasteAction(this) );
		workoutTable.getActionMap().put( DeleteAction.COMMAND_KEY, new DeleteAction(this) );
		
		setsTable.getActionMap().put( CutAction.COMMAND_KEY, new CutAction(this) );
		setsTable.getActionMap().put( CopyAction.COMMAND_KEY, new CopyAction(this) );
		setsTable.getActionMap().put( PasteAction.COMMAND_KEY, new PasteAction(this) );
		setsTable.getActionMap().put( DeleteAction.COMMAND_KEY, new DeleteAction(this) );
	}
	
	public JournalMenu getJournalMenu() {
		return journalMenu;
	}

	public Version getVersion() {
		return VERSION;
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		
		journalToolbar.localeChanged( locale );
		workoutTable.localeChanged( locale );
		exerciseTabbedPane.localeChanged( locale );
		setsTable.localeChanged( locale );
		datePicker.setLocale( locale );
		exerciseEditorDialog.localeChanged( locale );
		aerobicTable.localeChanged( locale );
		
		LocaleListener ll = (LocaleListener) journalMenu;
		ll.localeChanged( locale );
	}
	
	public void about(){
		JOptionPane.showMessageDialog( this,
				JournalHelper.getLanguage( getLocale(), "about.copyright", new Object[]{ "Morten Laukvik", "2007" }) + "    ",
			    JournalManager.getAppName(),
			    JOptionPane.INFORMATION_MESSAGE,
			    JournalHelper.getIcon("trainer.png")
		);
	}
	
	public void openShop(){
		shopDialog = new ShopDialog( this );
		shopDialog.openDialog();
	}
	
	public void initComponents(){
		journalToolbar = new JournalToolbar( this );
		journalToolbar.bodyPartSelected( Human.BODY.getArm() );
		workoutTableModel = new WorkoutTableModel( journal );
		workoutTable = new WorkoutTable( workoutTableModel );
		workoutTable.setLocale( getLocale() );
		tableScroll = new JScrollPane( workoutTable );
		tableScroll.setPreferredSize( new Dimension(200,2000) );
		workoutTable.getSelectionModel().addListSelectionListener( this );
		JSplitPane splitTabAndTable = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		splitTabAndTable.setEnabled( false );
		splitTabAndTable.setBorder( null );
		splitTabAndTable.setDividerSize( 0 );
		exerciseTabbedPane = new ExercisesTabbedPane();
		exerciseTabbedPane.setMinimumSize( new Dimension(1,320) );
		exerciseTabbedPane.setPreferredSize( new Dimension(1000,320) );
		exerciseTabbedPane.setLocale( getLocale() );
		datePicker = new DatePicker( getJournal().getLocale(), DatePicker.HEADER_FIRST_LETTER );
		datePicker.setMaximumSize( new Dimension(200,150) );
		datePicker.setSize( new Dimension(200,150) );
		datePicker.setMinimumSize( new Dimension(200,150) );
		datePicker.setPreferredSize( new Dimension(200,150) );
		datePicker.addCalendarListener( this );
		setsTable = new SetsTable();
		notesEditor = new NotesEditor();
		
		splitTabAndTable.setTopComponent( exerciseTabbedPane );
		splitTabAndTable.setBottomComponent( new JScrollPane(setsTable) );
		workoutTable.addFocusListener( this );
		datePicker.setEnabled( false );
		statusBar = new StatusBar();

		
		setLayout( new BorderLayout() );
		exerciseEditorDialog = new ExerciseEditorDialog();
		
		add( tableScroll, BorderLayout.WEST );

		JScrollPane notesScroll = new JScrollPane(notesEditor);
		aerobicTable = new AerobicTable();
		aerobicTable.setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 10) );

		/* Create the details panel */
		detailsPanel = new JPanel();
		BoxLayout bl = new BoxLayout( detailsPanel, BoxLayout.Y_AXIS );
		detailsPanel.setLayout( bl );
		detailsPanel.setBorder( new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 0, 10)) );
		detailsPanel.setPreferredSize( new Dimension(200,1000) );
		detailsPanel.setMinimumSize( new Dimension(200,200) );
		detailsPanel.add( datePicker  );
		detailsPanel.add( aerobicTable.getTableHeader() );
		detailsPanel.add( aerobicTable );
		detailsPanel.add( notesScroll );

		add( detailsPanel, BorderLayout.EAST );

		add( splitTabAndTable, BorderLayout.CENTER );
		add( statusBar, BorderLayout.SOUTH );
	}
	
	/**
	 * Sets the panel for details visible or hidden
	 * 
	 * @param isVisible
	 */
	public void setDetailsVisible( boolean isVisible ){
		detailsPanel.setVisible( isVisible );
	}
	
	public boolean isDetailsVisible(){
		return detailsPanel.isVisible();
	}
	
	public void alert( String message ){
		JOptionPane.showMessageDialog( this,
				message,
			    JournalManager.getAppName(),
			    JOptionPane.INFORMATION_MESSAGE,
			    JournalHelper.getIcon("trainer.png")
		);
	}
	
	public String getLanguage( String key ){
		return JournalHelper.getLanguage( getLocale(), key );
	}
	
	public void addWorkout( Workout workout ){
//		getJournal().fireWo
		getJournal().addWorkout( workout );
//		workoutTable.clearSelection();
//		workoutTable.getSelectionModel().addSelectionInterval( 0,0 );
		workoutTable.grabFocus();
		getJournal().fireWorkoutSelected( workout );
	}

	
	public Journal getJournal() {
		return journal;
	}
	
	public void loadJournal( Journal journal ){
		this.journal = journal;
		journal.addJournalListener( this );
		journal.addWorkoutSelectionListener( this );
		journal.addSetSelectionListener( this );
		journal.addJournalListener( exerciseTabbedPane );
		journal.addExerciseListener( exerciseTabbedPane );
		journal.addSetListener( exerciseTabbedPane );
		journal.addSetSelectionListener( exerciseTabbedPane );
		journal.addWorkoutListener( exerciseTabbedPane );
		
		journal.addJournalListener( workoutTable );
		journal.addExerciseListener( workoutTable );
		journal.addWorkoutListener( workoutTable );
		journal.addSetListener( workoutTable );
		
		journal.addWorkoutSelectionListener( journalMenu );
		journal.addSetSelectionListener( journalMenu );
		
		workoutTableModel.setJournal( journal );
		journal.addWorkoutSelectionListener( notesEditor );
		
		journal.addExerciseListener( setsTable );
		journal.addSetListener( setsTable );
		journal.addWorkoutSelectionListener( setsTable );

		journal.addWorkoutSelectionListener( journalToolbar );
		
		journal.addWorkoutSelectionListener( statusBar );
		journal.addSetSelectionListener( statusBar );
		
		if (journal.getWorkouts().size() > 0){
			workoutTable.addRowSelectionInterval( 0, 0 );
		}
		exerciseEditorDialog.setJournal( journal );
		
		checkForUpdate();
	}

	public void setJournal(Journal journal) {
		journal.removeExerciseListener( exerciseTabbedPane );
		this.journal = journal;
		workoutTableModel.setJournal( journal );
		journal.addExerciseListener( exerciseTabbedPane );
	}
	
	public JournalToolbar getToolbar() {
		return journalToolbar;
	}

	/**
	 * Fires when the selection in the workout table changes
	 */
	public void valueChanged( ListSelectionEvent evt ) {
		if (workoutTable.getSelectedRowCount() == 0){
			/* No workouts */
			datePicker.setEnabled( false );
			getJournal().fireNoWorkoutSelected();
			
		} else if (workoutTable.getSelectedRowCount() == 1){
			/* One workout */
			if (workoutTable.getSelectedRow() < journal.getWorkouts().size()){
				Workout workout = journal.getWorkouts().get( workoutTable.getSelectedRow() );
				getJournal().fireWorkoutSelected( workout );
			}
			datePicker.setEnabled( true );
			notesEditor.setEnabled( true );
			
		} else {
			/* Two or more */
			datePicker.setEnabled( false );
			int [] rows = workoutTable.getSelectedRows();
			getJournal().fireWorkoutsSelected( workoutTableModel.getRows( rows ) );
		}
	}
	
	public void log( Object message ){
		System.out.println( "JournalPanel: " + message );
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
	}

	/**
	 * Fires when a date is selected
	 * 
	 */
	public void dateChanged( GregorianCalendar date ) {
		workoutTable.getSelectionModel().removeListSelectionListener( this );
		if (selectedWorkouts != null & selectedWorkouts.length > 0){
			Workout w = selectedWorkouts[ 0 ];
			w.setDate( date );
			@SuppressWarnings("unused")
			int rowIndex = workoutTable.getSelectedRow();
			getJournal().sortWorkouts();
			getJournal().fireWorkoutDateChanged( w );
			getJournal().fireWorkoutOrderChanged();
			int index = getJournal().getWorkouts().indexOf( w );
			workoutTable.addRowSelectionInterval( index, index );
		}
		workoutTable.getSelectionModel().addListSelectionListener( this );
	}

	/**
	 * Opens up the editing of exercises
	 * 
	 */
	public void handleOpenExerciseEditorDialog() { 
		journal.removeExerciseListener( setsTable );
		exerciseEditorDialog.showPanel( this );
	}
	
	public void handleExercisesHide() {
		journal.addExerciseListener( setsTable );
	}
	
	/**
	 * Opens up the preference window
	 * 
	 */
	public void preferences(){
//		JournalOwnerDialog dialog = new JournalOwnerDialog( getJournal(), this );
//		journal.addLocaleListener( dialog );
//		dialog.openDialog();
//		journal.removeLocaleListener( dialog );
//		getJournal().fireJournalChanged();
		
		OwnerPropertiesDialog dialog = new OwnerPropertiesDialog();
		journal.addLocaleListener( dialog );
		dialog.setLocale( getLocale() );
		int returnValue = dialog.open( getJournal(), this.getRootPane() );
		if (returnValue == OwnerPropertiesDialog.ACCEPT){
			/* */
			
		} else {
			/* Don't do anything on cancel */
		}
		getJournal().fireJournalChanged();
		journal.removeLocaleListener( dialog );
		
	}
	
	public void selectWorkoutByIndex( int index ){
		workoutTable.clearSelection();
		workoutTable.addRowSelectionInterval( index, index );
		workoutTable.scrollToRow( index );
	}

	public void selectWorkout(Workout work) {
		int index = getJournal().getWorkouts().indexOf( work );
		selectWorkoutByIndex( index );
	}

	/**
	 * Checks if there is a newer version of this software available
	 * 
	 * @param silent set to false if you don't want a dialog box when there's no updates available
	 */
	public void checkForUpdate() {	
		if (journal.getExercises().size() == 0){
			
			VersionChecker checker = new VersionChecker( this.getVersion(), this.getRootPane(), getLocale() );
			if (checker.hasNewVersion()){
				
			} else {
				help();
				
			}
				
		}
		checkShopNews();
	}
	
	public void checkShopNews(){
		int count = JournalManager.getNewShopItems( new Date() );
		if (count > 0){
			journalToolbar.setShopNews( count + "" );
		}
	}

	public void journalChanged(Journal journal) {
	}

	public void journalLoaded( Journal journal ) {

	}

	public void ownerChanged(Journal journal) {
	}

	
	/*********************************************************************************/
	public void noSetsSelected() {
		this.selectedSets = null;
	}

	public void setSelectedSet(Set set) {
		this.selectedSets =  new Set [] { set };
	}

	public void setsSelectedSets(Set[] sets) {
		this.selectedSets =  sets;
	}

	public void noWorkoutSelected() {
		this.selectedWorkouts = null;
		aerobicTable.setEnabled( false );
	}

	public void workoutSelected(Workout workout) {
		this.selectedWorkouts =  new Workout [] { workout };
		datePicker.setDate( workout.getCalendar() );
		aerobicTable.setWorkout( workout );
		aerobicTable.setEnabled( true );
	}
	
	public Workout[] getSelectedWorkouts() {
		return selectedWorkouts;
	}
	
	public Set[] getSelectedSets() {
		return selectedSets;
	}

	public void workoutsSelected(Workout[] workouts) {
		this.selectedWorkouts = workouts;
		aerobicTable.setEnabled( false );
	}
	
	private Workout [] selectedWorkouts = null;
	private Set [] selectedSets = null;

	public void actionPerformed(ActionEvent e) {
	}
	
	
	/**
	 * Cuts the selected item(s) in either workouts or sets
	 */
	public void cut(){
		copy();
		delete();
	}

	public void copy() {
		log( "Copying: " );
		if (workoutTable.isFocusOwner() && selectedWorkouts != null && selectedWorkouts.length > 0){
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents( new WorkoutTransferable( selectedWorkouts ), this );
			
		} else if (setsTable.isFocusOwner() && selectedSets != null && selectedSets.length > 0){
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents( new SetTransferable( selectedSets ), this );
		}
	}

	public void paste() {
		log( "Pasting: " );
		if (Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable( SetTransferable.SETS_FLAVOR )){
			try {
				Set[] sets = (Set[]) Toolkit.getDefaultToolkit().getSystemClipboard().getData( SetTransferable.SETS_FLAVOR );
				log( "Pasting: " + sets.length );
				Set [] dups = getJournal().duplicate( sets );
				if (selectedWorkouts != null && selectedWorkouts.length == 1){
					Workout work = selectedWorkouts[ 0 ];
					for (Set s : dups){
						work.add( s );
						getJournal().fireSetAdded( s );
					}
					getJournal().sortWorkouts();
					getJournal().fireWorkoutOrderChanged();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable( WorkoutTransferable.WORKOUT_FLAVOR )){
			try {
				Workout[] workouts = (Workout[]) Toolkit.getDefaultToolkit().getSystemClipboard().getData( WorkoutTransferable.WORKOUT_FLAVOR );
				log( "Pasting: " + workouts.length );
				Workout [] dups = getJournal().duplicate( workouts );
				for (Workout w : dups){
					getJournal().addWorkout( w );
					getJournal().fireWorkoutAdded( w );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

	public void delete() {
		log("delete");
		/* Delete selected Workouts */
		if (workoutTable.isFocusOwner()){
			if (selectedWorkouts == null || selectedWorkouts.length == 0){
				log("No workouts to cut...");
			} else {
				
				
//					SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy", new Locale("no","no"));
//					return format.format( date.getTime() ) + " (" + getSets().size() + ")";
				
				
				String message = null;
				if (selectedWorkouts.length == 1){
					message = JournalHelper.getLanguage( getLocale(), "workout.confirmdelete" );
				} else {
					message = JournalHelper.getLanguage( getLocale(), "workouts.confirmdelete",selectedWorkouts.length);
				}
				message += "\n";
				int x = 0;
				for (Workout w : selectedWorkouts){
					if (x < 10){
						message += "\n" + w.toString( getLocale() );	
					}
					x++;
				}
				message += "\n";
				if (x>10){
					message += "\n+++";
				}
				/**
				 * showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon) 
          Brings up a dialog with a specified icon, where the number of choices is determined by the optionType parameter.
				 */
				int n = JOptionPane.showConfirmDialog(
						    this,
						    message,
						    JournalManager.getAppName(),
						    JOptionPane.YES_NO_OPTION , 
						    JOptionPane.QUESTION_MESSAGE,
						    JournalHelper.getIcon( "journal.png" )
					    );
				
				if (n == JOptionPane.YES_OPTION){
					log("Cutting workouts before: " + journal.getWorkouts().size() );
					for (Workout w : selectedWorkouts){
						journal.getWorkouts().remove( w );
					}
					journal.fireWorkoutRemoved( selectedWorkouts );
					log("Cutting workouts after: " + journal.getWorkouts().size() );
				}
				

			}
		} 
		if (setsTable.isFocusOwner()){
			if (selectedSets == null || selectedSets.length == 0){
				log("No sets to cut...");
			} else {
				log("Cutting sets: " + selectedSets.length );
				Workout w = selectedSets[ 0 ].getWorkout();
				for (Set s : selectedSets){
					log("Cutting set: " + s.getText() );
					w.remove( s );
				}
				journal.fireSetsRemoved( selectedSets );
				journal.fireWorkoutChanged( w );
			}
		}
	}

	public void handleBodyStatusOpen() {
		Status status = new Status();
		if (getJournal().getStatuses().size() > 0){
			status = getJournal().getStatuses().get( getJournal().getStatuses().size()-1 );
		}
		BodyStatusDialog dialog = new BodyStatusDialog( this, status, getLocale(), journal );
		dialog.getAlignmentX();
		if (dialog.getStatus() == null){
			/* Don't add when pressing cancel */
		} else {
			journal.addStatus( dialog.getStatus() );	
		}	
	}
	
	public void setSelectedPart(Part part) {
		exerciseTabbedPane.setPart( part );
	}

	public void help() {
		if (helpViewer == null){
			helpViewer = new HelpViewer( this.getRootPane(), getLocale() );
		} else {
			/* Already open */
			helpViewer.setLocationRelativeTo( this.getRootPane() );
			helpViewer.setVisible( true );
			
		}
	}

}