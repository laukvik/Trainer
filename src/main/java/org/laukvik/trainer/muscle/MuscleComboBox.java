package org.laukvik.trainer.muscle;

import java.awt.Font;
import java.util.Locale;

import javax.swing.JComboBox;

import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.swing.JournalHelper;

public class MuscleComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	public MuscleComboBox( Locale locale ){
		super( JournalHelper.getMuscles( locale ) );
		setFont( new Font( getFont().getName(), Font.PLAIN, 11 ) );
		setMaximumRowCount( Human.BODY.listAllMuscles().length );
	}
	
}