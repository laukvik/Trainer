package org.laukvik.trainer.journal;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JToolBar;

import org.laukvik.trainer.actions.AddSetAction;
import org.laukvik.trainer.actions.AddWorkoutAction;
import org.laukvik.trainer.actions.DetailsAction;
import org.laukvik.trainer.actions.EditExercisesAction;
import org.laukvik.trainer.actions.HelpAction;
import org.laukvik.trainer.actions.MeasurementsAction;
import org.laukvik.trainer.actions.PreferencesAction;
import org.laukvik.trainer.actions.ShopAction;
import org.laukvik.trainer.actions.ShowBodyPartAction;
import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.Part;
import org.laukvik.trainer.swing.JournalButton;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

public class JournalToolbar extends JToolBar implements WorkoutSelectionListener, LocaleListener{

	private static final long serialVersionUID = 1L;
	private JournalButton addSetButton, addWorkoutButton, preferencesButton, measurementsButton, editExercisesButton, detailsButton, shopButton, helpButton;
	private AddSetAction addSetAction;
	private JournalButton armButton, waistButton, chestButton, backButton, legsButton, shoulderButton, buttocksButton;

	public JournalToolbar( JournalPanel journalPanel ) {
		super();
		setFloatable( false );
		addWorkoutButton = new JournalButton( new AddWorkoutAction(journalPanel) );
		add( addWorkoutButton );
		addSetAction = new AddSetAction( journalPanel );
		addSetButton = new JournalButton( addSetAction );
		addSetButton.setEnabled( false );
		add( addSetButton );
		
		addSeparator();
		
		shopButton = new JournalButton( new ShopAction( journalPanel ) );
		add( shopButton );
		
		addSeparator();

		preferencesButton = new JournalButton( new PreferencesAction(journalPanel) );
		measurementsButton = new JournalButton( new MeasurementsAction(journalPanel) );
		editExercisesButton = new JournalButton( new EditExercisesAction(journalPanel) );
		
		add( preferencesButton );
		add( measurementsButton );
		add( editExercisesButton );

		addSeparator();

		
		armButton = new JournalButton( new ShowBodyPartAction( Human.BODY.ARM,journalPanel,this) ); 
		waistButton = new JournalButton( new ShowBodyPartAction( Human.BODY.WAIST,journalPanel,this) );  
		chestButton = new JournalButton( new ShowBodyPartAction( Human.BODY.CHEST,journalPanel,this) );  
		backButton = new JournalButton( new ShowBodyPartAction( Human.BODY.BACK,journalPanel,this) ); 
		legsButton = new JournalButton( new ShowBodyPartAction( Human.BODY.LEG,journalPanel,this) );  
		shoulderButton = new JournalButton( new ShowBodyPartAction( Human.BODY.SHOULDERS,journalPanel,this) );  
		buttocksButton = new JournalButton( new ShowBodyPartAction( Human.BODY.BUTTOCKS,journalPanel,this) ); 
		
		add( armButton );
		add( waistButton );
		add( chestButton );
		add( backButton );
		add( legsButton );
		add( shoulderButton );
		add( buttocksButton );
	
		addSeparator();
		
		detailsButton = new JournalButton( new DetailsAction(journalPanel) );
		 
		add( detailsButton );
		detailsButton.setSelected(true);
		
		addSeparator();
		
		helpButton = new JournalButton( new HelpAction(journalPanel) );
		 
		add( helpButton );
		
		

	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
		addWorkoutButton.localeChanged( locale );
		addSetButton.localeChanged( locale );
		
		shopButton.localeChanged( locale );
		
		preferencesButton.localeChanged( locale );
		measurementsButton.localeChanged( locale );
		editExercisesButton.localeChanged( locale );
		detailsButton.localeChanged( locale );
		
		helpButton.localeChanged( locale );
		
		armButton.localeChanged( locale );
		waistButton.localeChanged( locale );
		chestButton.localeChanged( locale );
		backButton.localeChanged( locale );
		legsButton.localeChanged( locale );
		shoulderButton.localeChanged( locale );
		buttocksButton.localeChanged( locale );
	}
	
	public void bodyPartSelected( Part part ){
		for (Component c : getComponents()){
			if (c instanceof JournalButton){
				JournalButton jb = (JournalButton) c;
				if (jb.getAction() instanceof ShowBodyPartAction){
					ShowBodyPartAction sbpa = (ShowBodyPartAction) jb.getAction();
					if (sbpa.getPart().sameAs( part )){
						jb.setEnabled( false );
						jb.setSelected( true );
					} else {
						jb.setEnabled( true );
						jb.setSelected( false );
					}
				}
			}
		}
	}
	
	public void workoutSelected(Workout workout) {
		if (workout.getJournal().getExercises().size() == 0){
			addSetButton.setEnabled( false );
			addSetAction.setWorkout( null );
		} else {
			addSetButton.setEnabled( true );
			addSetAction.setWorkout( workout );
		}
	}

	public void workoutsSelected(Workout[] workouts) {
		addSetButton.setEnabled( false );
		addSetAction.setWorkout( null );
	}

	public void noWorkoutSelected() {
		addSetButton.setEnabled( false );
		addSetAction.setWorkout( null );
	}

	public void setShopNews( String newsBadge ) {
		ShopAction a = (ShopAction) shopButton.getAction();
		a.setBadge( newsBadge );
//		shopButton.setIcon( null );
//		shopButton.setAction( a );
		

	}
	
}