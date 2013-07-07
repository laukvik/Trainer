package org.laukvik.trainer.swing;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class NumberTextField extends JFormattedTextField implements CaretListener {

	private static final long serialVersionUID = 1L;
	private float number;
	
	public NumberTextField( NumberFormat format ){
		super( format );
		addCaretListener( this );
	}
	
	public NumberTextField( NumberFormat format, String tooltip ){
		super( format );
		addCaretListener( this );
		setToolTipText( tooltip );
	}
	
	public NumberTextField(NumberFormat format, String tooltip, Rectangle rectangle, float f ) {
		super( format );
		addCaretListener( this );
		setToolTipText( tooltip );
		setBounds( rectangle );
		setValue( f );
	}

	public void setNumber(float number) {
		this.number = number;
	}
	
	public float getNumber(){
		try{
			this.number = Float.parseFloat( getValue().toString() );
//			System.out.println( getValue().toString() + "=" + number );
		} catch(Exception ex){
		}
		return number;
	}

	public void caretUpdate(CaretEvent e) {
		try{
			Float.parseFloat( getValue().toString() );
		} catch(Exception ex){
			Toolkit.getDefaultToolkit().beep();
		}
	}

}