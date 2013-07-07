package org.laukvik.trainer.muscle;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.j3d.Geometry;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.exercise.TransparencySlider;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.swing.JournalHelper;

public class MuscleInformationPanel extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private Muscle muscle;
	private SVGEditablePanel panel;
	private TransparencySlider slider;
	private JPanel sliderPanel;
	
	public MuscleInformationPanel(){
		initComponents();
	}
	
	public void initComponents(){
		setLocale( JournalHelper.getDefaultLocale() );
		setLayout( new BorderLayout() );
		panel = new SVGEditablePanel();
		JScrollPane scroll = new JScrollPane( panel );
		add( scroll );
		
		slider = new TransparencySlider(JournalHelper.getLanguage( getLocale(), "transparent.muscle"),JournalHelper.getLanguage( getLocale(), "transparent.skin"));
		slider.setOrientation( TransparencySlider.VERTICAL );
		slider.addChangeListener( this );
		slider.setEnabled( true );
		slider.setMaximumSize( new Dimension(64,150) );

		
		sliderPanel = new JPanel();
		sliderPanel.setLayout( new BoxLayout(sliderPanel, BoxLayout.X_AXIS ) );
		sliderPanel.add( slider   );
		
		add( sliderPanel, BorderLayout.EAST );
	}

	public Muscle getMuscle() {
		return muscle;
	}

	public void setMuscle( Muscle muscle, Journal journal ) {
		this.muscle = muscle;
		String filename = muscle.getLatin().toLowerCase().replaceAll( " ", "_" ) + (journal.isMale() ? "_m" : "_f");
		String path = "java:///org/laukvik/trainer/svg/" + filename + ".svg";
		try {
			panel.loadSVG( new SVGSource(path) );
			setSkinValue( slider.getValue() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SVGEditablePanel getSvgPanel() {
		return panel;
	}

	public void stateChanged(ChangeEvent e) {
		setSkinValue( slider.getValue() );
	}
	
	public void setSliderValue( Element element ){
		Geometry skin = (Geometry) panel.getSVG().getElementById("skin");
		skin.opacity = new Opacity( (slider.getValue() / 100f) );
	}
	
	public void setSkinValue( int value ){
		Geometry skin = (Geometry) panel.getSVG().getElementById("skin");
		if (skin == null){
			
		} else {
			skin.opacity = new Opacity( (value / 100f) );
			panel.repaint();
		}
	}

}