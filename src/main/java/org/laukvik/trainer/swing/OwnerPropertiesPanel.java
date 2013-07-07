package org.laukvik.trainer.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import org.laukvik.swing.locale.LocaleComboBox;

public class OwnerPropertiesPanel extends JPanel {
    
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Creates new form OwnerPropertiesPanel 
	 **/
    public OwnerPropertiesPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        ownerPanel = new javax.swing.JPanel();
        ownerTextField = new javax.swing.JTextField();
        birthdayPanel = new javax.swing.JPanel();
        datePicker = new DatePicker();
        sexPanel = new javax.swing.JPanel();
        maleRadio = new javax.swing.JRadioButton("Male");
        femaleRadio = new javax.swing.JRadioButton("Female");
        languagePanel = new javax.swing.JPanel();
        languageComboBox = new LocaleComboBox( JournalHelper.listAvailableLocale() );
        unitsPanel = new javax.swing.JPanel();
        weightLabel = new javax.swing.JLabel();
        distanceComboBox = new DistanceComboBox();
        distanceLabel = new javax.swing.JLabel();
        weightComboBox = new WeightComboBox();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        ownerPanel.setLayout(new java.awt.BorderLayout());
        ownerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Owner"));
        ownerPanel.add(ownerTextField, java.awt.BorderLayout.CENTER);

        add(ownerPanel);

        birthdayPanel.setLayout(new java.awt.BorderLayout());
        birthdayPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Birthday"));
        birthdayPanel.add(datePicker, java.awt.BorderLayout.CENTER);

        add(birthdayPanel);

        sexPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sex"));
        maleRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        maleRadio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sexPanel.add(maleRadio);

        femaleRadio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        femaleRadio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sexPanel.add(femaleRadio);
        
        maleRadio.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						femaleRadio.setSelected( false );
					}
				}
		);
		femaleRadio.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						maleRadio.setSelected( false );
					}
				}
		);

        add(sexPanel);

        languagePanel.setLayout(new java.awt.BorderLayout());
        languagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Language"));
        languagePanel.add(languageComboBox, java.awt.BorderLayout.CENTER);

        add(languagePanel);

        unitsPanel.setLayout(new java.awt.GridBagLayout());

        unitsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Units"));
        weightLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        weightLabel.setLabelFor(weightComboBox);
        weightLabel.setText("Weight");
        unitsPanel.add(weightLabel, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        unitsPanel.add(distanceComboBox, gridBagConstraints);

        distanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        distanceLabel.setLabelFor(distanceComboBox);
        distanceLabel.setText("Distance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        unitsPanel.add(distanceLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        unitsPanel.add(weightComboBox, gridBagConstraints);

        add(unitsPanel);


    }// </editor-fold>                        
    
    
    // Variables declaration - do not modify                   
    public javax.swing.JPanel birthdayPanel;
    public DistanceComboBox distanceComboBox;
    public javax.swing.JLabel distanceLabel;
    public javax.swing.JRadioButton maleRadio;
    public javax.swing.JRadioButton femaleRadio;
    public DatePicker datePicker;
    public LocaleComboBox languageComboBox;
    public javax.swing.JPanel languagePanel;
    public javax.swing.JPanel ownerPanel;
    public javax.swing.JTextField ownerTextField;
    public javax.swing.JPanel sexPanel;
    public javax.swing.JPanel unitsPanel;
    public WeightComboBox weightComboBox;
    public javax.swing.JLabel weightLabel;
    // End of variables declaration                   
    
}