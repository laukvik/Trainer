package org.laukvik.trainer.exercise;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Locale;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.swing.JournalHelper;

public class ExerciseComboBox extends JComboBox implements LocaleListener{

	private static final long serialVersionUID = 1L;
	private ExerciseComboBoxRenderer renderer;

	public ExerciseComboBox(){
		super();
		setLocale( JournalHelper.getDefaultLocale() );
		setFont( new Font( getFont().getName(), Font.PLAIN, 11 ) );
		setMaximumRowCount( 10 );
		this.renderer = new ExerciseComboBoxRenderer();
		setRenderer( renderer );
		setPreferredSize( new Dimension(300, ExercisePanel.REQUIRED_HEIGHT ) );
//		setMinimumSize( new Dimension(10, ExercisePanel.REQUIRED_HEIGHT ) );
	}
	
	public void setJournal( Journal journal ){
		setModel( new ExerciseComboBoxModel( journal ) );
	}
	
	public void setModel(ComboBoxModel model) {
		if (model instanceof ExerciseComboBoxModel){
			super.setModel(model);
		} else {
			model = new ExerciseComboBoxModel();
			super.setModel(model);
		}
	}

	public void firePopupMenuWillBecomeInvisible() {
		super.firePopupMenuWillBecomeInvisible();
		setSelectedIndex( getSelectedIndex() );
	}

	public void localeChanged(Locale locale) {
		setLocale( locale );
	}

}