package org.laukvik.trainer.swing.status;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.laukvik.svg.*;
import org.laukvik.svg.swing.*;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalExporter;
import org.laukvik.trainer.journal.Status;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.swing.NumberTextField;

/**
 *
 * @author  morten
 */
public class BodyStatusDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel buttonPanel;
	private SVGPanel panel;
	private JButton okButton, cancelButton;
	private Status status;

	 //Formats to format and parse numbers
    private NumberFormat format;
    
    private int w = 50;
    private int h = 28;
    private int w2 = 120;
	
    public BodyStatusDialog( Component owner, Status status, Locale locale, Journal journal ) {
    	setLocale( locale );
    	setTitle( JournalHelper.getLanguage( getLocale(), "measures") );
    	this.status = status;
    	
    	format = NumberFormat.getNumberInstance();
    	format.setMinimumFractionDigits( 1 );
    	format.setMaximumFractionDigits( 2 );
    	format.setMinimumIntegerDigits( 1 );
    	format.setMaximumIntegerDigits( 3 );
    	
    	
    	
        initComponents();
        String filename = "measures_" + (journal.isMale() ? "m" : "f");
        panel.loadSVG( new SVGSource( "java:///org/laukvik/trainer/svg/" + filename + ".svg" ) );
        JournalExporter exporter = new JournalExporter( journal );
        stats.setSVG( exporter.statusToSVG() );
        
        JScrollPane scroll = (JScrollPane) split.getRightComponent();
//        scroll.setBorder( null );
        scroll.setColumnHeaderView( new SVGPanel( exporter.horisontalStatusScrollbar() ) );
        scroll.setRowHeaderView( new SVGPanel( exporter.verticalStatusScrollbar() ) );
        scroll.setCorner( StatusScrollPane.UPPER_LEFT_CORNER, new SVGPanel(exporter.emptyUpperHorisontalStatusScrollbar()) );
        scroll.setCorner( StatusScrollPane.UPPER_RIGHT_CORNER, new SVGPanel(exporter.emptyUpperHorisontalStatusScrollbar()) );
        
        scroll.setCorner( StatusScrollPane.LOWER_LEFT_CORNER, new SVGPanel(exporter.emptyLowerHorisontalStatusScrollbar()) );
        scroll.setCorner( StatusScrollPane.LOWER_RIGHT_CORNER, new SVGPanel(exporter.emptyLowerHorisontalStatusScrollbar()) );
        
        setVisible( true );        
    }


	public void actionPerformed(ActionEvent e) {
//		System.out.println( "BodyStatusDialog: " + e.getSource() );
		if (e.getSource() == okButton){
			status = new Status();
		} else {
			status = null;
		}
		dispose();
	}
	
	public Status getStatus(){
		return status;
	}
    
    private void initComponents() {
    	setLayout( new BorderLayout() );
    	setResizable( true );
//    	if (Toolkit.getDefaultToolkit().getScreenSize().height < 900){
//    		setSize( Toolkit.getDefaultToolkit().getScreenSize() );
//    	} else {
    		setSize( 980, 750 ); 
//    	}
    	setModal( true );
    	setLocationRelativeTo( null );
    	
    	panel = new SVGPanel();
    	panel.setSize( new Dimension( 470, 800 ) );
    	panel.setLayout( null );
    	panel.setMinimumSize( new Dimension( 500,500 ) );
    	
        upperArmLeft = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "upperarm"), new Rectangle(50,160,w,h), status.leftUpperArm );
        upperArmRight = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "upperarm"), new Rectangle(360,160,w,h), status.rightUpperArm );
        
        lowerArmLeft = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "lowerarm"), new Rectangle(20,250,w,h), status.leftForeArm );
        lowerArmRight = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "lowerarm"), new Rectangle(390,250,w,h), status.rightForeArm );

        calfLeft = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "calf"), new Rectangle(90,580,w,h), status.leftCalf );
        calfRight = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "calf"), new Rectangle(330,580,w,h), status.rightCalf );
        
        waist = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "waist"), new Rectangle(210,250,w,h), status.waist );
        chest = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "chest"), new Rectangle(210,150,w,h), status.chest );
        neck = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "neck"), new Rectangle(207,60,w,h), status.neck );
        
        thighLeft = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "thigh"), new Rectangle(80,450,w,h), status.leftThigh );
        thighRight = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "thigh"), new Rectangle(340,450,w,h), status.rightThigh );
        
        
        
        bodyFat = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "bodyfat"), new Rectangle(50,30,w,h), status.bodyfat );
        weight = new NumberTextField( format, JournalHelper.getLanguage( getLocale(), "weight"), new Rectangle(360,30,w,h), status.weight );

        panel.add(lowerArmRight);panel.add(thighRight);panel.add(calfRight);panel.add(bodyFat);panel.add(weight);
        panel.add(upperArmLeft);panel.add(lowerArmLeft);panel.add(thighLeft);panel.add(calfLeft);
        panel.add(waist);panel.add(neck);panel.add(upperArmRight);panel.add(chest);
        
        for (Component c : panel.getComponents()){
        	if (c instanceof NumberTextField){
        		NumberTextField field = (NumberTextField) c;
            	JLabel label = new JLabel( field.getToolTipText(), JLabel.CENTER );
            	label.setBounds( field.getX()-((w2-w)/2), (int) (field.getY()-h*1), w2, h );
            	panel.add( label );
            	field.setToolTipText( null );
        	}
        }
       
        stats = new SVGPanel();
        
        split = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, new JScrollPane( panel ), new StatusScrollPane(stats) );
        split.setDividerLocation( 465 );
        split.setEnabled( false );
        split.setDividerSize( 0 );
        split.setBorder( null );
        stats.setBorder( null );
        
        add( split );
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        buttonPanel = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
        buttonPanel.add( okButton );
        buttonPanel.add( cancelButton );
        
        okButton.addActionListener( this );
        cancelButton.addActionListener( this );
        
        add( buttonPanel, BorderLayout.SOUTH );

    }

    public void dispose() {
    	super.dispose();
    	if (status == null){
    		
    	} else {
        	status.setValue( Status.BODYFAT,       bodyFat.getNumber() );
        	status.setValue( Status.CHEST,         chest.getNumber() );
        	status.setValue( Status.LEFTCALF,      calfLeft.getNumber() );
        	status.setValue( Status.LEFTFOREARM,   lowerArmLeft.getNumber() );
        	status.setValue( Status.LEFTTHIGH,     thighLeft.getNumber() );
        	status.setValue( Status.LEFTUPPERARM,  upperArmLeft.getNumber() );
        	status.setValue( Status.NECK,          neck.getNumber() );
        	status.setValue( Status.RIGHTCALF,     calfRight.getNumber() );
        	status.setValue( Status.RIGHTFOREARM,  lowerArmRight.getNumber() );
        	status.setValue( Status.RIGHTTHIGH,    thighRight.getNumber() );
        	status.setValue( Status.RIGHTUPPERARM, upperArmRight.getNumber() );
        	status.setValue( Status.WAIST,         waist.getNumber() );
        	status.setValue( Status.WEIGHT,        weight.getNumber() );
    	}
    }
    
    // Variables declaration - do not modify
    private NumberTextField calfLeft;
    private NumberTextField calfRight;
    private NumberTextField chest;
    private NumberTextField lowerArmLeft;
    private NumberTextField lowerArmRight;
    private NumberTextField neck;
    private NumberTextField thighLeft;
    private NumberTextField thighRight;
    private NumberTextField upperArmLeft;
    private NumberTextField upperArmRight;
    private NumberTextField waist;
    
    private NumberTextField bodyFat;
    private NumberTextField weight;
    
    private JSplitPane split;
    private SVGPanel stats;

}