package org.laukvik.trainer.journal;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.trainer.actions.AboutAction;
import org.laukvik.trainer.actions.CopyAction;
import org.laukvik.trainer.actions.CutAction;
import org.laukvik.trainer.actions.DeleteAction;
import org.laukvik.trainer.actions.HelpAction;
import org.laukvik.trainer.actions.PasteAction;
import org.laukvik.trainer.actions.PreferencesAction;
import org.laukvik.trainer.set.Set;
import org.laukvik.trainer.set.SetSelectionListener;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.workout.Workout;
import org.laukvik.trainer.workout.WorkoutSelectionListener;

public class JournalMenu extends JMenuBar implements LocaleListener, WorkoutSelectionListener, SetSelectionListener  {

	private static final long serialVersionUID = 1L;
	private JMenu fileMenu, editMenu, helpMenu; 
	private JournalMenuItem cutItem, copyItem, pasteItem, deleteItem, aboutItem, preferenceItem, helpItem;
	
	public JournalMenu( JournalPanel journalPanel ){
		super();
		setLocale( JournalHelper.getDefaultLocale() );
		/* FILE MENU */
		fileMenu = new JMenu( JournalHelper.getLanguage( getLocale(), "file") );
		add( fileMenu );
		
		/* EDIT MENU */
		editMenu = new JMenu( JournalHelper.getLanguage( getLocale(), "edit") );
		/* CREATE SUBMENUS */
		cutItem = new JournalMenuItem( new CutAction(journalPanel) );
		copyItem = new JournalMenuItem( new CopyAction(journalPanel) );
		pasteItem = new JournalMenuItem( new PasteAction(journalPanel) );
		deleteItem = new JournalMenuItem( new DeleteAction(journalPanel) );
		preferenceItem = new JournalMenuItem( new PreferencesAction(journalPanel) );
		/* ATTACH SUBMENU */
		JournalMenuItem [] editMenuItems = { cutItem, copyItem, pasteItem, deleteItem, preferenceItem };
		for (JournalMenuItem item : editMenuItems){
			if (item == preferenceItem && CrossPlatformUtilities.isMacOSX()){
				
			} else {
				item.addActionListener( journalPanel );
				editMenu.add( item );	
			}
		}
		add( editMenu );
		
		/* HELP MENU */
		helpMenu = new JMenu( JournalHelper.getLanguage( getLocale(), "help") );
		/* CREATE SUBMENUS */
		aboutItem = new JournalMenuItem( new AboutAction(journalPanel) );
		helpItem = new JournalMenuItem( new HelpAction(journalPanel) );
		helpItem.setIcon( null );
		/* ATTACH SUBMENU */
		JournalMenuItem [] helpMenuItems = { helpItem, aboutItem };
		for (JournalMenuItem item : helpMenuItems){
			item.addActionListener( journalPanel );
			if (item == aboutItem){
				
			}
			helpMenu.add( item );
		}
		add( helpMenu );
		
		setVisible(true);
	}

	public void localeChanged( Locale locale ) {
		setLocale( locale );
		editMenu.setText( JournalHelper.getLanguage( getLocale(), "edit") );
		for (Component c : editMenu.getMenuComponents()){
			if (c instanceof LocaleListener){
				((LocaleListener) c).localeChanged(locale);
			}
			if (c instanceof JournalMenuItem){
				JournalMenuItem item = (JournalMenuItem) c;
				item.setLocale( locale );
			}
		}
				
		helpMenu.setText( JournalHelper.getLanguage( getLocale(), "help") );
		for (Component c : helpMenu.getMenuComponents()){
			if (c instanceof LocaleListener){
				((LocaleListener) c).localeChanged(locale);
			}
			if (c instanceof JournalMenuItem){
				JournalMenuItem item = (JournalMenuItem) c;
				item.setLocale( locale );
			}
		}
	}
	
	public void setEditEnabled( boolean enabled ){
		cutItem.setEnabled( enabled );
		copyItem.setEnabled( enabled );
		pasteItem.setEnabled( enabled );
		deleteItem.setEnabled( enabled );
	};

	public void noWorkoutSelected() {
		setEditEnabled( false );
	}

	public void workoutSelected(Workout workout) {
		setEditEnabled( true );
	}

	public void workoutsSelected(Workout[] workouts) {
		setEditEnabled( true );
	}

	public void noSetsSelected() {
	}

	public void setSelectedSet(Set set) {
	}

	public void setsSelectedSets(Set[] sets) {
	}

}