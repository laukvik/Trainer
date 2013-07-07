package org.laukvik.trainer.exercise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.actions.AddExerciseAction;
import org.laukvik.trainer.actions.RemoveExerciseAction;
import org.laukvik.trainer.actions.ShareExerciseAction;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.muscle.MuscleInformationPanel;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;

public class ExerciseEditorDialog extends JDialog implements ActionListener, TreeSelectionListener, LocaleListener {

	private static final long serialVersionUID = 1L;
	private JButton addButton, removeButton, shareButton;
	private JToolBar toolbar;
	private Journal journal;
	private JPanel buttonBar;
	private JButton okButton;
	private ExerciseDetailsPanel2 detailsPanel;
	private ExerciseTree tree;
	private MuscleInformationPanel musclePanel;
	private JSplitPane split;
	private JournalPanel journalPanel;

	public ExerciseEditorDialog(){
		super();
		setLocale( JournalHelper.getDefaultLocale() );
		setSize( 810, 560 );
		initComponents();
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		setTitle( JournalHelper.getLanguage( getLocale(), "exercises") );
		okButton.setText( JournalHelper.getLanguage( getLocale(), "ok") );
		addButton.setText( JournalHelper.getLanguage( getLocale(), "exercise.add") );
		addButton.setToolTipText( JournalHelper.getLanguage( getLocale(), "exercise.add.tooltip") );
		removeButton.setText( JournalHelper.getLanguage( getLocale(), "exercise.delete") );
		removeButton.setToolTipText( JournalHelper.getLanguage( getLocale(), "exercise.delete.tooltip") );
		
		shareButton.setText( JournalHelper.getLanguage( getLocale(), "exercise.share") );
		shareButton.setToolTipText( JournalHelper.getLanguage( getLocale(), "exercise.share.tooltip") );
		
		detailsPanel.localeChanged( locale );
		
	}

	public void initComponents(){
		setTitle( JournalHelper.getLanguage( getLocale(), "exercises") );
		setModal( true );
		setLayout( new BorderLayout() );
		musclePanel = new MuscleInformationPanel();
		toolbar = new JToolBar();
		toolbar.setFloatable( false );
		addButton = new JButton( new AddExerciseAction(this) );
		addButton.setEnabled( false );
		removeButton = new JButton( new RemoveExerciseAction(this) );
		removeButton.setEnabled( false );
		
		shareButton = new JButton( new ShareExerciseAction(this) );
		shareButton.setEnabled( false );

		toolbar.add( addButton );
		toolbar.add( removeButton );
		toolbar.add( shareButton );
		buttonBar = new JPanel();
		buttonBar.setAlignmentX( SwingConstants.RIGHT );
		buttonBar.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
				
		okButton = new JButton( "   " + JournalHelper.getLanguage( getLocale(), "ok") + "   " );
		okButton.addActionListener( this );

		buttonBar.add( okButton );
		
		add( toolbar, BorderLayout.NORTH  );
		
		detailsPanel = new ExerciseDetailsPanel2( this );
		detailsPanel.setEnabled( false );
		
		tree = new ExerciseTree();
		tree.getSelectionModel().addTreeSelectionListener( this );

		split = new JSplitPane();
		split.setDividerLocation( 250 );
		split.setResizeWeight( 0 );
		split.setEnabled( false );
		split.setMinimumSize( new Dimension(750,430) );
		split.setDividerSize( 0 );
		split.setBorder( null );
		split.setLeftComponent( new JScrollPane( tree ) );
		split.getLeftComponent().setMinimumSize( new Dimension( 250,200) );
//		split.setRightComponent( detailsPanel );
		split.setRightComponent( new JScrollPane(detailsPanel) );
//		split.getRightComponent().setMinimumSize( new Dimension(465,430) );
//		split.getRightComponent().setMaximumSize( new Dimension(465,430) );
//		split.getRightComponent().setPreferredSize( new Dimension(465,430) );
//		split.getRightComponent().setSize( new Dimension(465,430) );
		add( split );
		
		add( buttonBar, BorderLayout.SOUTH  );
		
	}
	
	
	public void setJournal(Journal journal) {
		this.journal = journal;
		tree.setJournal( journal );
		journal.addExerciseListener( tree );
	}
	
	
	public void showPanel( JournalPanel journalPanel ){
		this.journalPanel = journalPanel;
		setLocationRelativeTo( journalPanel.getRootPane() );
		setVisible(true);
	}
	
	public void dispose() {
		super.dispose();
		journalPanel.handleExercisesHide();
	}
	
	public void exerciseChanged( Exercise exercise ){
		journal.fireExerciseChanged(exercise);
	}
	
	public void exerciseMuscleChanged( Exercise exercise, Muscle from, Muscle to ){
		journal.fireExerciseChangedMuscle( exercise, from, to );
	}
	
	public void log( Object message ){
		System.out.println( ExerciseEditorDialog.class.getName() + ": " + message );
	}
	
	/**
	 * Adds an empty exercise
	 * 
	 */
	public void add(){
		Muscle muscle = getSelectedMuscle(); 
		log( "ExerciseEditorPanel: " + muscle + " id=" + muscle.getMuscleID() );
		if (muscle == null){
			
		} else {
			TreePath path = tree.getSelectionPath();
			Exercise exercise = new Exercise( Muscle.getInstance( muscle.getMuscleID() ), journal.getAuthorID() );
			exercise.setAuthor( journal.getOwner() );
			exercise.setLocale( Locale.ENGLISH );
			journal.addExercise( exercise );
			tree.getModel().valueForPathChanged( path, exercise );
			TreePath path2 = path.pathByAddingChild( exercise );
			tree.clearSelection();
			tree.addSelectionPath( path2 );
			
			log( "ExerciseEditorPanel: " + muscle + " id=" + muscle.getMuscleID() );
		}
	}
	
	/**
	 * Removes the selected exercise
	 *  
	 */
	public void remove(){	
		Exercise exercise = getSelectedExercise();
		Vector <Workout> workouts= journal.getWorkouts( exercise );		
		int v = JOptionPane.showConfirmDialog(  this , JournalHelper.getLanguage( getLocale(), "exercise.confirmdelete", workouts.size() ), JournalManager.getAppName(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, JournalHelper.getIcon( "exercises.png" )  );
		if (v == JOptionPane.OK_OPTION){
			journal.removeExercises( exercise );			
			journal.fireExerciseRemoved( exercise, exercise.getMuscle() );
			removeButton.setEnabled( false );
			detailsPanel.emptyValues();
		}

	}

	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}

	/*
	 * Fires when the user clicks on the tree 
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	public void valueChanged(TreeSelectionEvent e) {
		if (tree.isSelectionEmpty()){
			/* Nothing is selected */
			detailsPanel.setEnabled( false );
		} else {
			/* The user clicked on a node */
			Object o = getSelectedExercise();
			boolean isExerciseSelected = (o instanceof Exercise);
			if (isExerciseSelected){
				detailsPanel.setExercise( (Exercise) o );
			} else {
				detailsPanel.setExercise( null );
			}
			/* The user clicked on an exercise */
			if (isExerciseSelected){
				Exercise x = (Exercise) getSelectedExercise();
				int indices =journal.getExercises( x.getMuscle() ).indexOf( x ); 
				tree.setChildIndice( indices );
			}

			boolean isMuscleSelected = (tree.getLastSelectedPathComponent() instanceof Muscle);
			/* The user clicked on a muscle */
			if (isMuscleSelected){
				musclePanel.setMuscle( (Muscle) tree.getLastSelectedPathComponent(), journal );
				split.setRightComponent( musclePanel );
		
			} else {
				split.setRightComponent( detailsPanel );
			}
			
			detailsPanel.setEnabled( isExerciseSelected );
			removeButton.setEnabled( isExerciseSelected );
			addButton.setEnabled( isMuscleSelected );
			shareButton.setEnabled( isExerciseSelected );
		}
	}

	

	public Exercise getSelectedExercise(){
		Object o = tree.getLastSelectedPathComponent();
		if (o instanceof Exercise){
			return (Exercise) o;
		} else {
			return null;
		}
	}
	
	public Muscle getSelectedMuscle(){
		Object o = tree.getLastSelectedPathComponent();
		if (o instanceof Muscle){
			return (Muscle) o;
		} else {
			return null;
		}
	}

}