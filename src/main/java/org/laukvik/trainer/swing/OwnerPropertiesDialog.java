package org.laukvik.trainer.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.trainer.journal.Journal;

public class OwnerPropertiesDialog extends JDialog implements LocaleListener{

	private static final long serialVersionUID = 1L;
	private OwnerPropertiesPanel panel;
    private javax.swing.JButton acceptButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private int returnValue = JOptionPane.ERROR;
    
    public final static int ERROR = JOptionPane.ERROR;
    public final static int ACCEPT = JOptionPane.OK_OPTION;
    public final static int CANCEL = JOptionPane.CANCEL_OPTION;
    
    private Journal journal;

	public OwnerPropertiesDialog() {
		initComponents();
	}
	
	public void localeChanged(Locale locale) {
		panel.ownerPanel.setBorder( BorderFactory.createTitledBorder( JournalHelper.getLanguage( getLocale(),"journal.owner") ) );
		panel.birthdayPanel.setBorder( BorderFactory.createTitledBorder( JournalHelper.getLanguage( getLocale(),"journal.birthday") ) );
		panel.sexPanel.setBorder( BorderFactory.createTitledBorder( JournalHelper.getLanguage( getLocale(),"sex") ) );
		panel.languagePanel.setBorder( BorderFactory.createTitledBorder( JournalHelper.getLanguage( getLocale(),"locale") ) );
		panel.unitsPanel.setBorder( BorderFactory.createTitledBorder( JournalHelper.getLanguage( getLocale(),"unit.types") ) );
		acceptButton.setText( JournalHelper.getLanguage( getLocale(),"ok") );
		cancelButton.setText( JournalHelper.getLanguage( getLocale(),"cancel") );
		panel.maleRadio.setText( JournalHelper.getLanguage( getLocale(),"sex.male" ) );
		panel.femaleRadio.setText( JournalHelper.getLanguage( getLocale(),"sex.female" ) );
		panel.weightLabel.setText( JournalHelper.getLanguage( getLocale(),"unit.weight" ) );
		panel.distanceLabel.setText( JournalHelper.getLanguage( getLocale(),"unit.distance" ) );
		panel.datePicker.setLocale( getLocale() );
	}

	public void initComponents(){
		setLocale( JournalHelper.getDefaultLocale() );
		setLayout( new BorderLayout() );
		this.panel = new OwnerPropertiesPanel();
		panel.setBorder( BorderFactory.createEmptyBorder( 10,10,10,10 ) );
		add( panel, BorderLayout.CENTER );
		
		buttonsPanel = new JPanel();
		
		acceptButton = new JButton("ok");
        acceptButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				acceptPressed();
			}}
        );
        
        buttonsPanel.add(acceptButton);

        cancelButton = new JButton("cancel");
        cancelButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cancelPressed();
			}}
        );
        buttonsPanel.add(cancelButton);

        add(buttonsPanel, BorderLayout.SOUTH );
	}
	
    public void acceptPressed(){
    	returnValue = JOptionPane.OK_OPTION;
    	journal.setOwner( panel.ownerTextField.getText() );
    	journal.setBirthday( panel.datePicker.getDate().getTime() );
    	journal.setDistance( panel.distanceComboBox.getSelectedDistance() );
    	journal.setWeight( panel.weightComboBox.getSelectedWeight() );
    	journal.setMale( panel.maleRadio.isSelected() );
    	journal.setLocale( panel.languageComboBox.getSelectedLocale() );
    	dispose();
    }
    
    public void cancelPressed(){
    	returnValue = JOptionPane.CANCEL_OPTION;
    	dispose();
    }
    
    public int returnValue(){
    	return returnValue;
    }
	
    public int open( Journal journal, JComponent component ){
		this.journal = journal;
		localeChanged( journal.getLocale() );
		panel.maleRadio.setSelected( journal.isMale() );
		panel.femaleRadio.setSelected( !panel.maleRadio.isSelected() );
		panel.ownerTextField.setText( journal.getOwner() );
		panel.datePicker.setDate( journal.getBirthday() );
		panel.ownerTextField.setText( journal.getOwner() );
		panel.languageComboBox.setSelectedLocale( journal.getLocale() );
		panel.distanceComboBox.setSelectedItem( journal.getDistance() );
		panel.weightComboBox.setSelectedItem( journal.getWeight() );
    	/**/
		setSize( 320, 500 );
		setResizable( false );
		setModal( true );
		setLocationRelativeTo( component );
		setVisible(true);
		return returnValue();
    }
	
}