package org.laukvik.trainer.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import org.laukvik.trainer.exercise.ExerciseEditorDialog;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.swing.JournalHelper;

public class ShareExerciseAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	public final static String COMMAND_KEY = "shareExercise";
	private ExerciseEditorDialog exerciseEditorPanel;

	public ShareExerciseAction( ExerciseEditorDialog exerciseEditorPanel ) {
		this.exerciseEditorPanel = exerciseEditorPanel;
		putValue( Action.NAME, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.share" ) );
		putValue( Action.ACTION_COMMAND_KEY, COMMAND_KEY );
		putValue( Action.SMALL_ICON, JournalHelper.getIcon( "tb_shop.png" ) );
		putValue( Action.SHORT_DESCRIPTION, JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.share.tooltip" ) );
	}

	public void actionPerformed( ActionEvent evt ) {
		if (confirm( JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.share.confirm" ) )){
			share();	
		}
	}
	
	public void share(){
		try {
			JournalManager mgr = new JournalManager();
			mgr.publish( exerciseEditorPanel.getSelectedExercise() );
			alert( JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.share.successful" ) );
		} catch (Exception e) {
//			e.printStackTrace();
			error( JournalHelper.getLanguage( exerciseEditorPanel.getLocale(), "exercise.share.failed" ), e );
		}
	}
	
	public boolean confirm( String message ){
		return JOptionPane.showConfirmDialog( 
				exerciseEditorPanel,
				message,
			    "",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    JournalHelper.getIcon("shop.png")
		) == JOptionPane.OK_OPTION;
	}
	
	public void alert( String message ){
		JOptionPane.showMessageDialog( exerciseEditorPanel,
				message,
			    "",
			    JOptionPane.INFORMATION_MESSAGE,
			    JournalHelper.getIcon("trainer.png")
		);
	}
	
	public void error( String message, Exception e ){
		JOptionPane.showMessageDialog( exerciseEditorPanel,
				message,
			    "",
			    JOptionPane.INFORMATION_MESSAGE,
			    JournalHelper.getIcon("trainer.png")
		);
	}
	

}